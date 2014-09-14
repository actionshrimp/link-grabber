(ns link-grabber.handlers.save-link
  (:require [compojure.core :as route]
            [clj-http.client :as http] 
            [clojurewerkz.urly.core :refer [url-like]]
            [net.cgrand.enlive-html :as html]
            ))

(defn find-search-elems [page]
  (let [selector [:form [:input (html/attr= :name "q")]]]
    (html/select page selector)))

(defn save-link [passed-url]
  (let [url (java.net.URL. (str (url-like passed-url)))
        page (html/html-resource url)
        updated (html/at page [:#lga :div :div :div] (html/html-content "BLAH"))
        ]
    (html/emit* (find-search-elems page))
  ))

(defn routes [] (route/GET "/save-link" [url] (save-link url)))
