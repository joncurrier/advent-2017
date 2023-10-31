(ns day-02
  (:require [clojure.string :as string]
            [common :refer [read-input read-lines]]))
            
(defn find-line-difference [line]
  (- (apply max line) (apply min line)))

(defn find-row-checksum-2 [line]
  (let [i-line (map-indexed vector line)]
    (first (for [[i1 x1] i-line
                 [i2 x2] i-line
                 :when (not= i1 i2)
                 :let [r (mod x1 x2)] 
                 :when (zero? r)]
             (/ x1 x2)))))

(defn find-row-checksum [line]
  (apply max 
         (for [x1 line 
               x2 line 
               :let [r (mod x1 x2)] 
               :when (zero? r)] 
           (/ x1 x2))))

(defn structure-input [lines]
  (->> lines 
       (map (fn [line] (->> (string/split line #"\t") 
                            (map #(Integer/parseInt %)))))))

(defn solve-part-1 [filename]
  (->> (read-lines filename)
       structure-input
       (map #(find-line-difference %))
       (apply +)))

(defn solve-part-2 [filename]
 (->> (read-lines filename)
     structure-input
     (map #(find-row-checksum-2 %))
     (apply +)))

;; (4 2)
;; 4 4 => 1
;; 4 2 => 2
;; 2 4 => X
;; 2 2 => 1

;; (5 3)
;; 5 5 => 1
;; 5 3 => X
;; 3 3 => 1
;; 3 5 => X

(comment 
  (solve-part-1 "day_02.txt") 
  (time (solve-part-2 "day_02.txt"))
)




(comment
  "Jake"
  
  (def JAKE "hi")


(println "from Jake")

  )





