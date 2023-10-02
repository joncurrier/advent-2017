(ns common
  (:require [clojure.string :as string]
            [clojure.java.io :as io]))

(defn read-input [f]
  (-> (io/resource f)
      (slurp)
      (string/trim-newline)))

(defn read-lines [f]
  (-> (io/resource f)
      (slurp)
      (string/split-lines)
      (vec)))
