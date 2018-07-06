
#!/bin/bash

javac -cp /Users/stevechan/.m2/repository/org/clojure/clojure/1.8.0/clojure-1.8.0.jar $1

java -classpath /Users/stevechan/.m2/repository/org/clojure/clojure/1.8.0/clojure-1.8.0.jar:.  "$(cut -d '.' -f 1 <<< $1)"


