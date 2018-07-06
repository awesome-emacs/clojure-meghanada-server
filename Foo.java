// Foo.java

import clojure.lang.RT;
import clojure.lang.Var;

public class Foo {
    public static void main(String[] args) throws Exception {
        // Load the Clojure script -- as a side effect this initializes the runtime.
        RT.loadResourceScript("foo.clj");

        // Get a reference to the foo function.
        Var foo = RT.var("user", "foo");

        // Call it!
        Object result = foo.invoke("Hi", "there");
        System.out.println(result);
    }
}

