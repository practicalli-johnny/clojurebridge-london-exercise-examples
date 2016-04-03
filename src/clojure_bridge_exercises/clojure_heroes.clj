(ns clojure-bridge-exercises.clojure-heroes)

(def feet 5.5)

(def inches (* 12 feet))
(def cm (* inches 2.54))


(def average (/ (+ 180 167.64 165) 3)) ;; average of denise kriszta jarkyn heights

(def average-in-inches (/ average 2.54))
(def average-in-feet (/ average-in-inches 12))

(def cm-in-foot (* 2.54 12))
(def whole-feet (quot average cm-in-foot))


;;;;;;;;;

(ns demo.core)

(def inches-in-a-foot 12)

(def my-height-in-inches
  (+ (* 6 inches-in-a-foot)
     4))

(def inches-in-a-cm 2.54)

(def my-height-in-cm
  (* inches-in-a-cm my-height-in-inches))

(def average-cm
  (/ (+ my-height-in-cm 164.59 158.75)
     3))

(def average-in-inches
  (/ average-cm inches-in-a-cm))

(def average-in-feet-and-inches
  [(quot average-in-inches 12)
   (mod average-in-inches 12)])



;; https://pub-name-generator.herokuapp.com/



(defn total-with-tip
"Given subtotal, return total after tax and tip."
[subtotal tip-pct number-of-people]
(/ (* 1.08 subtotal (+ 1 tip-pct)) number-of-people)
)

(defn share-per-person
[number-of-people]
)

(total-with-tip 12 0.18 1)



;;;;;;;;

(defn total-with-tip "Given subtotal, this is how much everyone better pay" [sub-total tip-pct number-of-people] (/ (* 1.08 sub-total (+ 1 tip-pct)) number-of-people) )


;;;;;;;;;

(defn share-per-person
  [subtotal tip-pot no-of-people]
  (/  (* 1.08 subtotal (+ 1 tip-pot)) no-of-people))


;;;;;;;;;

(defn divides-by-three? [x] (= (mod x 3) 0))

(filter divides-by-three? (take 100(range))
     )
;; (0 3 6 9 12 15 18 21 24 27 30 33 36 39 42 45 48 51 54 57 60 63 66 69 72 75 78 81 84 87 90 93 96 99)

;;;;;;;;;

;;; http://www.tutorialspoint.com/codingground.htm

;;;;;;;;;

(def amounts [10 12 15 26 30 14 24]) (defn calculate-average
  [list]
  (float
   (/ (reduce + list)
      (count list))))

;;;;;;;;;

(defn average  [bill-amounts]
  "Given a vector of bill amounts, return the average of those amounts"
  (/ (reduce + bill-amounts) (count bill-amounts))
  )



(defn average [elements]
(/ (reduce + elements ) (count elements))
)
(defn average [amounts]
  (/ (reduce + amounts) (count amounts)))




(defn average [numbers]
  (let [length (count numbers)
        sum (reduce + numbers)]
    (/ sum length)))



(defn average [coll] (/ (reduce + coll)(count coll)))


(fn [x]
  (+ x 2))

(map #(+ % 2) (range 10))

(fn [x y] (+ x y))

#(+ %1 %2)



(case (+ 3 4)
  9 ..
  7 ...
  ) ;; like switch

(cond
  (< 2 3) "2's less than 3"
  (= meaning-of-life 42) "Yep, that's true"
  (= input :something-else) 918) ;; like lots of else-ifs



(defn spread
  "Given a collection of numbers, return the difference between the largest and smallest number."
  [numbers]
  (let [largest (reduce max numbers)
        smallest (reduce min numbers)]
    (- largest smallest)))

(spread [10 7 3 -3 8]) ;=> 13


(defn average
  [numbers]
  (let [total (reduce + numbers)
        number-of-elems (count numbers)]
  (float (/ total number-of-elems))
  ))


(if-let [user-entity (get-user-by-id 24)]
  (process-user user-entity)
  (handle-not-found))

(when-let [user-entity (get-user-by-id 24)]
  (handle-user))


http://openweathermap.org/current

(def weather-response
  (client/get "http://api.openweathermap.org/data/2.5/weather?q=London,uk"))

(def weather-response
  (client/get "http://api.openweathermap.org/data/2.5/weather"
              {:query-params {:q "London,uk"}
               :as :json}))

(:body (client/get "https://api.github.com/zen"))

(let [{:keys [body status]} weather-response]
  (if (= 200 status)
    body
    "uh oh"))

(let [{{coord :coord} :body, status :status} weather-response]
  (if (= 200 status)
    coord
    "uh oh"))

(defn divides-by-fifteen?
  [x]
  (zero? (mod x 15)))

(defn divides-by-three?
  [x]
  (zero? (mod x 3)))

(defn divides-by-five?
  [x]
  (zero? (mod x 5)))

(defn play-fizzbuzz
  [number]
  (cond (divides-by-fifteen? number) "fizzbuzz"
      (divides-by-three? number) "fizz"
      (divides-by-five? number) "buzz"
      :else number)
  )

(map play-fizzbuzz (range 1 100))

(defn fizzbuzz [n]
  (map #(cond
          (zero? (mod % 15)) "FizzBuzz"
          (zero? (mod % 3)) "Fizz"
          (zero? (mod % 5)) "Buzz"
          :otherwise (str %))
       (range 1 n)))

(defn fizzbuzz [n]
  (map (fn [fizz buzz x]
         (let [fizz-buzz (str fizz buzz)]
           (if-not (s/blank? fizz-buzz)
             fizz-buzz
             (str x))))
       (cycle [nil nil "Fizz"])
       (cycle [nil nil nil nil "Buzz"])
       (range 1 n)))

(defn divides-by?
  [divisor number]
  (zero? (mod number divisor)))

(defn play-fizzbuzz
  [number]
  (cond (divides-by? 15 number) "fizzbuzz"
        (divides-by? 3 number ) "fizz"
        (divides-by? 5 number) "buzz"
        :else number))

(map play-fizzbuzz (range 1 100))



;; lein new phoenix-webapp my-app -- :reagent

