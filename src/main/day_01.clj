(ns day-01
  (:require [common :refer [read-input]]))

(defn parse-input [f]
  (map (comp parse-long str) (read-input f)))

(defn f [input]
  (->> (repeat input)
       (flatten)
       (rest)
       (map vector input)
       (filter (partial apply =))
       (map first)
       (apply +)))

(defn f2 [input]
  (->> (repeat input)
       (flatten)
       (drop (/ (count input) 2))
       (map vector input)
       (filter (partial apply =))
       (map first)
       (apply +)))

(comment
  (-> "day_01.txt"
      (parse-input)
      f2))
