(ns day-03
  (:require [clojure.string :as string]
            [common :refer [read-input read-lines]]))

(defn part-one [input]
  (let [odd-squares (map #(* % %) (range 1 1000 2))
        square-sizes (range 0 1000 2)
        centers     (map (fn [s size]
                           (map (fn [i]
                                  (- s
                                     (* i size)
                                     (/ size 2)))
                                (range 0 4)))
                         odd-squares
                         square-sizes)
        ring-number (->> odd-squares
                         (map inc)
                         (map-indexed vector)
                         (filter #(> (second %) input))
                         (ffirst))
        offset (->> (nth centers ring-number)
                    (map #(abs (- input %)))
                    (apply min))]
    (+ ring-number offset)))

(defn part-two [input]
  (let [dirs (->> [:right :up :left :down]
                  (repeat)
                  (flatten))
        step-sizes (->> (range)
                        (drop 1)
                        (map #(repeat 2 %))
                        (flatten))
        path (flatten (map (fn [dir step-size]
                             (repeat step-size dir))
                           dirs
                           step-sizes))
        xy->neighbors (fn [x y]
                        #{[(dec x) (dec y)]
                          [(dec x) y]
                          [(dec x) (inc y)]
                          [x (dec y)]
                          [x (inc y)]
                          [(inc x) (dec y)]
                          [(inc x) y]
                          [(inc x) (inc y)]})]
    (loop [[x y] [0 0]
           visited {[0 0] 1M}
           path path]
      (let [dir (first path)
            [new-x new-y] (case dir
                            :right [(inc x) y]
                            :up [x (inc y)]
                            :left [(dec x) y]
                            :down [x (dec y)])
            neighbors (xy->neighbors new-x new-y)
            value (->> visited
                       (filter (fn [[k v]]
                                 (contains? neighbors k)))
                       (map second)
                       (apply +))]
        (if (> value input)
          value
          (recur [new-x new-y]
                 (assoc visited [new-x new-y] value)
                 (rest path)))))))


(comment
  (time (part-two 3615270000000000000000000000000000000000000000000000000000000000000000000000M)))
