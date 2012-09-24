(ns clojurizzles.views.pages
  (:require [clojurizzles.views.common :as common]
            [clojurizzles.views.form :as form])
  (:use [noir.core :only [defpage]]
        [hiccup.element :only [link-to]]
        [hiccup.form :only [submit-button form-to]]))

(defpage "/" []
         (common/layout
           [:p "Welcome to clojurizzles"]
           [:p (link-to "/rizzlesform" "Forward")]))

(defpage "/rizzlesform" {:as user}
    (common/layout
        [:h1 "A great form"]
        [:h2 "Please fill me in and submit!"]
        (form-to [:post "/rizzles"]
          (form/user-fields user)
          (submit-button "Go!"))))

(defn valid? [{:keys [answer]}]
  true)

(defn saidyes? [user]
  (if (= (user :answer) "true") true false))

(defn gavename? [user]
  (pos? (count (user :name))))

(defpage [:post "/rizzles"] {:as user}
  (common/layout
    (if (and (valid? user) (saidyes? user) (gavename? user))
      '([:h1 "Thanks for name, and saying yes"]
        [:h2 "Cats will like you forever"])
      '([:h1 "Oh noes"]
        [:h2 "Either you didn't put in your name, or didn't click yes"]))))
