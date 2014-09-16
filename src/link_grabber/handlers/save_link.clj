(ns link-grabber.handlers.save-link
  (:require [compojure.core :as route]
            [clj-http.client :as http] 
            [clojurewerkz.urly.core :refer [url-like]]
            [net.cgrand.enlive-html :as html]
            [link-grabber.parsers.search-url :as search-url]))

(defn normalise-url [url]
  (->> url
       (url-like)
       (str)
       (java.net.URL.)))

(defn find-search-urls [page]
  (map #(% page) search-url/parsers))

(defn save-link [passed-url]
  (let [normalised-url (normalise-url passed-url)]
    (->> normalised-url
         (html/html-resource)
         (find-search-urls)
         (flatten)
         (map #(str "<p>" % "</p>")))))

(defn routes [] (route/GET "/save-link" [url] (save-link url)))
