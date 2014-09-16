(ns link-grabber.handlers.save-link
  (:require [compojure.core :as route]
            [clj-http.client :as http] 
            [clojurewerkz.urly.core :refer [url-like]]
            [net.cgrand.enlive-html :as html]))

(defn search-url [form-html]
  (let [path (:action form-html)
        inputs (html/select form-html [[:input]])
        ;params
        ]
    (str "search url placeholder")))

(defn find-search-elem [page]
  (let [selectors [[[:form (html/attr-contains :action "search")]]
                   [:form (html/has [[:input (html/attr= :name "q")]])]]]
    (->> selectors
         (html/select page)
         (mapcat)
         (first))))

(defn normalise-url [url]
  (->> url
       (url-like)
       (str)
       (java.net.URL.)))

(defn save-link [passed-url]
  (->> passed-url
       (normalise-url)
       (html/html-resource)
       (find-search-elem)
       (html/emit*)))

(defn routes [] (route/GET "/save-link" [url] (save-link url)))
