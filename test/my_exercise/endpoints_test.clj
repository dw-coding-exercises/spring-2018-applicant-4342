(ns my-exercise.endpoints-test
  (:require [clojure.test :refer :all]
            [my-exercise.core :refer :all]
            [ring.mock.request :as mock]))

(deftest test-endpoints
  (testing "get endpoint"
    (let [response (app (mock/request :get "/"))]
        (is (= (:status response) 200))
        (is (= (get-in response [:headers "Content-Type"]) "text/html; charset=utf-8")))))

; tried testing the post endpoint but couldn't figure out how to properly format the params to pass in the body
;(deftest test-endpoints
;  (testing "post /search endpoint"
;    (let [response (app (mock/request :post "/search")
;                        (mock/json-body {:params {:street "123 Alphabet St" :city "Newark" :state "NJ" :zip "12345"}))]
;        (is (= (:status response) 200))
;        (is (= (get-in response [:headers "Content-Type"]) "text/html; charset=utf-8")))))"
