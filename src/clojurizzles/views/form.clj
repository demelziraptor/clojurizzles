(ns clojurizzles.views.form
  (:require [clojurizzles.views.common :as common])
  (:use [noir.core :only [defpartial]]
        [hiccup.form]))


(defpartial user-fields [{:keys [aname answer]}]
  (label "name" "Name: ")
  (text-field "name" aname)
  (label "answer" "Answer: ")
  (check-box "answer" answer))

