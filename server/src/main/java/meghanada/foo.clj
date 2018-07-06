(ns user
  (:gen-class
   :name "plain.someactivity.MyActivity"
   :prefix "some-"))

(defonce this-atom (atom nil))

(defn some-main [this]
  (reset! this-atom this))

(defn main [this]
  (reset! this-atom this))

(defn -main [this]
  (reset! this-atom this))


(defn foo [a b]
  (str a " " b))


(defn some-foo [a b]
  (str a " " b))

