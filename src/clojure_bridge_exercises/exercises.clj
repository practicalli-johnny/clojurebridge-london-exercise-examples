(ns clojure-bridge-exercises.exercises)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Introduction to Programming with Clojure


;; Some really basic concepts

;; Everything is a list in Clojure.  List stands for List Processing.  So what is a list and does it work

;; Really simplistic example - a shopping list

(shopping-in-tesco "eggs" "bacon" "cheese" "cerial" "milk")

;; We have a list of things we want to buy.  the first item in the list defines how we are going to get these things



;; EXERCISE: Basic arithmetic

;; - Take your height in feet and inches and convert it to inches using arithmetic in Clojure.
;; - Then convert that to centimeters. There are 2.54 centimeters in an inch.
;; - Lastly, ask two people near you for their height in centimeters. Find the average of your heights.

;; - Bonus: Convert that average back to feet and inches. The feet and the inches will be separate numbers. (quot x y) will give you the whole number part when dividing two numbers. (mod x y) will give you the remainder when dividing two numbers.

;; Define a persons height in feet and inches using a map and Clojure keywords for the keys
(def john {:feet 6 :inches 2})

;; Or to give this data more contenxt, an allow for further definitions of data for john, lets put the feet and inches data as the value for the height key
(def john {:height {:feet 6 :inches 2}})



(defn convert-feet-and-inches-to-feet
  "Convert feet and inches values to feet"
  [feet inches]
  (+ (* feet 12) inches))

(convert-feet-and-inches-to-feet (:feet (john :height))
                                 (:inches (john :height)))


(defn convert-inches-to-centimeters
  "Convert inches to centimeters"
  [inches]
  (* inches 2.54))

(convert-inches-to-centimeters 74)


;; This function could also be written using a local symbol for the conversion multiplier (a local symbol is like a local variable, but immutable of course because this is Clojure)
(defn convert-inches-to-centimeters-alternative
  "Convert inches to centimeters - using let to define a local symbol"
  [inches]
  (let [centimeter-multiplier 2.54]
    (* inches centimeter-multiplier)))

(convert-inches-to-centimeters-alternative 74)



;; The hight for two other people
(def Anna  {:height {:feet 5 :inches 10}})
(def Bruce {:height {:feet 6 :inches 8}})


;; Calculate the average hight for all three people, John, Anna, Bruce

;; Steps to implement:
;; convert each person to centimeters < just start with Centimeters
;; calculate the average
;; convert back to feet & inches

;; clojure-bridge-exercises.core> (def heights [10 20 30])
;; #'clojure-bridge-exercises.core/heights
;; clojure-bridge-exercises.core> (count heights)
;; 3
;; clojure-bridge-exercises.core> (apply + heights)
;; 60


(defn average-height-in-centimeters
  "Given peoples height in centimeters, return the average height in centimeters"
  [heights]
  (/ (apply + heights) (count heights)))

(average-height-in-centimeters [180 220 140])

;; If the average is not a whole nuber, we get an interesting result
(average-height-in-centimeters [180 220 160])

;; Lets tweak the values and get a decimal number
(average-height-in-centimeters [180 220 160.0])


;; clojure-bridge-exercises.core> (quot 15 7)
;; 2
;; clojure-bridge-exercises.core> (mod 15 7)
;; 1

(defn convert-centimeters-to-feet-and-inches
  "Convert values in centimeters to feet and inches"
  [value]
  (let [feet (quot (/ value 2.54) 12)
        inches (mod (/ value 2.54) 12)]
    {:feet feet :inches inches}))

(defn average-height-in-feet-given-centimeters
  "Calculate the average hight of three people"
  [people]
  (convert-centimeters-to-feet-and-inches (average-height-in-centimeters people)))

(average-height-in-feet-given-centimeters [160 180 200])


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Data structures

;; EXERCISE: Make a vector

;; Make a vector of the high temperatues for the next 7 days in the town where you live. Then use the nth function to get the high temperature for next Tuesday.


(def weather-temperatures-forecast [5 7 13 12 11 12 9])

(nth weather-temperatures-forecast 1)

;; We could make this clearer with a nice little map to represent days of the week

(def days-of-the-week {:monday 0
                                :tuesday 1
                                :wednesday 2
                                :thursday 3
                                :friday 4
                                :saturday 5
                                :sunday 6})

(nth weather-temperatures-forecast (days-of-the-week :tuesday))

;; or even fancier with the threading macro

(->
 weather-temperatures-forecast
 (nth (days-of-the-week :tuesday)))


;; Making a longer range forcast by conjugating (joining) more temperatures to our forcast data structure

(conj weather-temperatures-forecast 13 15 19 20 22 21 24)

;; So what does the forcast data structure look like now?

weather-temperatures-forecast

;; Should we wish to keep this longer forcast, we can assign a name to it (makeing a clojure symbol)

(def long-range-weather-forecast
  (conj weather-temperatures-forecast 13 15 19 20 22 21 24))

;; Now we can access the full long range forecast
long-range-weather-forecast

;; Although underlying data structures cannont be changed in Clojure (they are immutable),
;; you can change names and point them to new data structures

;; So if we want it to look like we have updated our original forcast data, then we can point the original
;; name to the new data structure we are creating
(def weather-temperatures-forecast
  (conj weather-temperatures-forecast 13 15 19 20 22 21 24))

;; When we re-evaluate the weather-temperature-forecast it has all the new values
weather-temperatures-forecast


;;;;;;;;;
;; Functions

(defn total-bill
  "Given subtotal of bill, return total after tax."
  [subtotal]
  (* 1.08 subtotal))

(defn total-with-tip
  "Given subtotal, return total after tax and tip."
  [subtotal tip-percent]
  (* 1.08 subtotal (+ 1 tip-percent)))

(total-with-tip 12.50 0.18) ;=> 15.93
(total-with-tip 50 0.18)    ;=> 63.72

(total-with-tip 12.50 0.18) ;=> 15.93
(total-with-tip 50 0.18)    ;=> 63.72


;; EXERCISE: Find per-person share of bill among a group

;; Create a new function called share-per-person.

;; Modify our total-with-tip function, and call the new function share-per-person, that additionally takes in as an argument the number of people in the group for a bill. Have the function return the average bill amount per person.

(defn share-per-person
  "Share the cost of a bill evenly for everyone at the meal"
  [meal-cost people]
  (/ meal-cost people))


(defn total-with-tip-per-person
  "Given subtotal, return total after tax and tip per person (hard-coded)."
  [subtotal tip-percent]
  (share-per-person (* 1.08 subtotal (+ 1 tip-percent)) 2))

(total-with-tip-per-person 12.50 0.18)
(total-with-tip-per-person 12.50 0)



;; EXERCISE: Find the average

;; Create a function called average that takes a vector of bill amounts and returns the average of those amounts.

;; Hint: You will need to use the functions reduce and count.

;; Define a collection of the orders for a group of people at a resteraunt
(def dine-in-orders [12.50 20 21 16 18.40])

;; Define a collection of orders for a take-away for a party
(def take-out-orders [6.00 6.00 7.95 6.25])

;; Use the previously defined function, total-bill, to add tax to each of the order costs in the collection
(map total-bill dine-in-orders)  ;=> [13.5 21.6 22.68 17.28 19.872]
(map total-bill take-out-orders) ;=> [6.48 6.48 8.586 6.75]


;; Lets look at map and reduce functions

(reduce + dine-in-orders)

;; So we can easily get the total cost of the orders, the average per person is simply the total divided by the number of orders

;; Lets get the number of orders from the collection
(count dine-in-orders)

;; So joining these two together we can get the average
(/ (reduce + dine-in-orders) (count dine-in-orders))

;; Lets put this into a function so we have a name for this behaviour which we can call 

(defn total-bill-per-person
  "Given subtotal of bill, return total after tax."
  [individual-order-costs]
  (/ (reduce + dine-in-orders) (count dine-in-orders)))

(total-bill-per-person dine-in-orders)


;; But wait, we have forgotten to include sales tax 

(defn total-bill-per-person-with-tax
  "Given subtotal of bill, return total after tax."
  [individual-order-costs]
  (/ (reduce + (map total-bill dine-in-orders)) (count dine-in-orders)))

(total-bill-per-person dine-in-orders)

;; Oh no, we forgot the tip as well....

(defn total-bill-per-person-with-tax-and-tip
  "Given subtotal of bill, return total after tax."
  [individual-order-costs]
  (/ (reduce + (map total-with-tip dine-in-orders )) (count dine-in-orders)))

(total-bill-per-person dine-in-orders)

;;; This is not quite right, as we need to pass in the percentage of tip.... not sure how to do that, perhaps a partial function?

;; We can use the function apply instead of reduce.  These functions can seem similar in concept, so lets see what we can do with apply to help you understand the difference


;; Lets get the total cost of the dine-in orders
(apply + dine-in-orders)

;; Now if we divide by the number of orders in the collction, we can get the average
(/ (apply + dine-in-orders) (count dine-in-orders) )

;; This all looks the same, so lets look at the Clojure docs to try understand the difference



;; > Note: Clojure is strict when it comes to functions as arguments.  This means that a function pased as an argument to another function is evaluated before it is passed as the argument.  So a function always recieves values as arguments, because a function always evaluated to a value (even if that value is nil).


;;;;;;;;;;;;
;; simple values

"This is a simple string"
"Strings evaluate to themselves"
"Strings are immutable like other data structures in Clojure"
(str "Strings" "can" "be" "joined" "togehter" "using" "the" "str" "function")
(str "but str doesnt add" "spaces" "between strings")
(str "so add your own" " " "spaces")


;; Experimenting with joining strings
(def join-my-strings-with-spaces ["join" "us" "toether" "with" "spaces"])

;; (map str join-my-strings-with-spaces)

;; (apply str join-my-strings-with-spaces)

;; Using another function called interpose, we can place something between each string as we join them together
(apply str (interpose " " join-my-strings-with-spaces))

;; See https://clojuredocs.org/clojure.core/interpose


;; If you come from a Java world, you may want to use println to output a string.  Well you can

(println "This is my string, but caveat emptor, this is a side effect")

;; When you evaluate the above, you do not get a string in return... so what is going on?

;; Discuss evalation verses side effects here?


;; EXERCISE: Store the name of your hometown

"Ripon"

;; Write the name of your hometown as a string, and then assign that string to the name my-hometown.
;; EXERCISE: Make a function to format names

(def my-hometown "Ripon")

my-hometown

;; The str function can take any number of arguments, and it concatenates them together to make a string. Write a function called format-name that takes two arguments, first-name and last-name. This function should output the name like so: Last, First.

(defn format-name
  "Concatenate two strings together"
  [firstname lastname]
  (str firstname " " lastname))

(format-name "John" "Stevenson")

;; Adding in our interpose example from before...

(defn format-name
  "Concatenate two strings together"
  [firstname lastname]
  (interpose " " (str firstname lastname)))

(format-name "John" "Stevenson")

;; Thats not quite right.  If we change the arguments to a vector then we can 

(defn format-name
  "Concatenate two strings together"
  [strings-to-join]
  (apply str (interpose " " strings-to-join)))

(format-name ["John" "Stevenson"])



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; More functions


;; Note for existing devs:  Clojure does not use = for assignment, so you dont need hacks like == for assignement.
;; Clojure keeps it simple


;; Naming conventions: Predicates?
;; When a function is asking a question where the answer is either true or false, the naming convention is to add a ? to the end of the function name.  for example
;; (true? (= 1 1))
;; (false? (= 1 2))

;; (vector? [1 2 3])



