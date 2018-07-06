// Foo.java

import clojure.lang.RT;
import clojure.lang.Var;
import clojure.lang.IFn;

public class Foo {
    public static void main(String[] args) throws Exception {
        // Load the Clojure script -- as a side effect this initializes the runtime.
        RT.loadResourceScript("foo.clj");

        // Get a reference to the foo function.
        Var foo = RT.var("user", "foo");

        // Call it!
        String result = foo.invoke("Hi", "there111").toString();
        System.out.println(result);

        ///
        //RT.var("clojure.core","eval").invoke(RT.var("clojure.core","read-string").invoke("(ns cljuser) (defonce this-atom (atom nil)) #_(defn main [this] (reset! this-atom this))  (defn abc [a] (prn a)) "));
        // IFn fn = RT.var("cljuser","main");
        //fn.invoke(1111);
        // IFn fn = RT.var("cljuser","abc");
        //fn.invoke(1111);        
        //Exception in thread "main" java.lang.IllegalStateException: Can't change/establish root binding of: *ns* with set
        //          at clojure.lang.Var.set(Var.java:221)
        //          at clojure.lang.RT$1.invoke(RT.java:241)

        Object abc = RT.var("clojure.core","read-string").invoke(":port");
        System.out.println(abc); // => :port
        //Object abcd = RT.var("clojure.core","eval").invoke(RT.var("clojure.core","read-string").invoke("{:port 312312}"));
        Object abcd = RT.var("clojure.core","read-string").invoke("{:port 312312}");
        //System.out.println(abcd.invoke(abc) );
        //System.out.println(abc.invoke(abcd) );
        //System.out.println(abcd.invoke(abc) );
        System.out.println(RT.var("clojure.core","get").invoke(abcd, abc).toString()); // => 312312
        
    }
}


//    try {
//        RT.loadResourceScript("foo.clj");
//        Var foo = RT.var("user", "foo");
//        String result = foo.invoke("Hi", "there111").toString();
//        log.info("foo.clj +++++ ok");
//        log.info(result);
//    } catch (Exception e) {
//        log.info("foo.clj ------- error!!!");
//        log.info(e);
//    }
//

