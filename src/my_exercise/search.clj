(ns my-exercise.search
    "Handles the request to search for upcoming elections in a given area"
  (:require [hiccup.page :refer [html5]]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [clj-http.client :as client]
            [my-exercise.us-state :as us-state]))

(defn display-matching-elections [results]
    "Displays the matching elections."
    
    (if (= results "[]")
        (str "This address isn't supported at this time.")
        (do 
            (def mapped-results (clojure.edn/read-string results))
            (str mapped-results))))

(defn send-turbovote-request [route]
    "Sends the request to the TurboVote API."

    (def response (client/get route))
    (if (= (:status response) 200)
        (display-matching-elections (:body response))
        (str "Something went wrong. Please try again later.")))

(defn create-turbovote-request [params]
    "Creates the route for the request to the Democracy Works API."

    (def base-route "https://api.turbovote.org/elections/upcoming?district-divisions=")
    (def base-ocd-division "ocd-division/country:us/")
    (def state (clojure.string/lower-case (:state params)))
    (def city (clojure.string/lower-case (:city params)))
    (def state-division (clojure.string/join [base-ocd-division "state:" state]))
    (def place-division (clojure.string/join [state-division "/place:" city])) 
    (def route (clojure.string/join [base-route state-division "," place-division]))
    (send-turbovote-request route))

(defn validate-params [params]
    "Validates that each form field except street-2 was filled.
    
    Also checks if the zip string has length of 5 and is a number."
    (if (empty? (:street params))
        (str "Please type in a value for the street field.")
        (if (empty? (:city params))
            (str "Please type in a value for the city field.")
            (if (empty? (:state params))
                (str "Please type in a value for the state field.")
                (if (empty? (:zip params))
                    (str "Please type in a value for the zip field.")
                    (if-not (= (count (:zip params)) 5)
                        (str "Zip code is not valid.")
                        (create-turbovote-request params)))))))

(defn page [params]
    (html5
        (validate-params params)))