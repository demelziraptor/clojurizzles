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

(defn valid? [{:keys [aname answer]}]
  true)

(defpage [:post "/rizzles"] {:as user}
  (common/layout
    (if (valid? user)
        [:h1 "Hurray, you said yes"])
      [:h2 "I appear anyway"]))


