(ns severmain
  (:import
   [java.io File IOException]
   [java.util Objects]
   [meghanada.config Config]
   [meghanada.server Server]
   [meghanada.server.emacs EmacsServer]
   [meghanada.utils FileUtils]
   [org.apache.commons.cli CommandLine
    CommandLineParser
    DefaultParser
    HelpFormatter
    Option
    Options
    ParseException]
   [org.apache.logging.log4j Level
    LogManager
    Logger]
   [org.apache.logging.log4j.core LoggerContext]
   [org.apache.logging.log4j.core.appender FileAppender]
   [org.apache.logging.log4j.core.config Configuration
    LoggerConfig]
   [org.apache.logging.log4j.core.layout PatternLayout]))

;; (EmacsServer. "127.0.0.1" 58888 "./")
;;  => #object[meghanada.server.emacs.EmacsServer 0x28c76a3d "meghanada.server.emacs.EmacsServer@28c76a3d"]
