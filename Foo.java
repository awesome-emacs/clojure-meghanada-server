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
        Object result = foo.invoke("Hi", "there");
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
        
    }
}

