package meghanada.session.subscribe;

import com.google.common.base.Stopwatch;
import com.google.common.eventbus.Subscribe;
import meghanada.analyze.CompileResult;
import meghanada.project.Project;
import meghanada.reflect.asm.CachedASMReflector;
import meghanada.session.Session;
import meghanada.session.SessionEventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Collection;

import static meghanada.config.Config.timeItF;

public class CacheEventSubscriber extends AbstractSubscriber {

    private static final Logger log = LogManager.getLogger(CacheEventSubscriber.class);

    public CacheEventSubscriber(final SessionEventBus sessionEventBus) {
        super(sessionEventBus);
        log.debug("subscribe cache");
    }

    @Subscribe
    public synchronized void on(final SessionEventBus.ClassCacheRequest request) {
        if (request.onlyOutputDir) {
            final CachedASMReflector reflector = CachedASMReflector.getInstance();
            reflector.updateClassIndexFromDirectory();
        } else {
            this.createFullIndex();
        }
    }

    private void createFullIndex() {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        final Session session = super.sessionEventBus.getSession();
        final Project project = session.getCurrentProject();
        final CachedASMReflector reflector = CachedASMReflector.getInstance();

        final Collection<File> dependentJars = session.getDependentJars();
        final int size = dependentJars.size();
        timeItF("create class index. read " + size + " jars. elapsed:{}", () -> {
            reflector.addClasspath(dependentJars);
            reflector.createClassIndexes();
        });

        timeItF("analyzed and compiled. elapsed:{}", () -> {
            try {
                final CompileResult compileResult = project.compileJava(false, true);
                if (!compileResult.isSuccess()) {
                    log.warn("Compile Error : {}", compileResult.getDiagnosticsSummary());
                }

                final CompileResult testCompileResult = project.compileTestJava(false, true);
                if (!testCompileResult.isSuccess()) {
                    log.warn("Test Compile Error : {}", testCompileResult.getDiagnosticsSummary());
                }

            } catch (Exception e) {
                log.catching(e);
            }
        });

        reflector.addClasspath(project.getOutputDirectory());
        reflector.addClasspath(project.getTestOutputDirectory());
        for (final Project dependency : project.getDependencyProjects()) {
            reflector.addClasspath(dependency.getOutputDirectory());
            reflector.addClasspath(dependency.getTestOutputDirectory());
        }
        reflector.updateClassIndexFromDirectory();
        log.info("create class index. size:{} total elapsed:{}", reflector.getGlobalClassIndex().size(), stopwatch.stop());
        log.info("Done indexing");
    }
}
