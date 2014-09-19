(ns link-grabber.handlers.save-link
  (:require [compojure.core :as route]
            [clj-http.client :as http] 
            [clojurewerkz.urly.core :as urly]
            [net.cgrand.enlive-html :as html]
            [link-grabber.parsers.search-url :as search-url]))

(defn normalise-url [url]
  (->> url
       (urly/url-like)
       (str)))

(defn find-search-urls [base-url page]
  (->> (map #(% page) search-url/parsers)
       (flatten)
       (map #(urly/absolutize % base-url))))

(defn parse-response [http-res]
  (let [final-url (last (:trace-redirects http-res))
        parsed-page (html/html-snippet (:body http-res))]
    (->> (:body http-res)
         (html/html-snippet)
         (find-search-urls final-url))))

(defn save-link [passed-url]
  (->> passed-url
       (normalise-url)
       (http/get)
       (parse-response)
       (flatten)
       (map #(str "<p>" % "</p>"))))

(defn read-link [passed-url]
  (let [normalised-url (normalise-url passed-url)]
    (->> passed-url
         (normalise-url)
         (http/get)
         (:trace-redirects)
         (str))))

(defn routes [] (route/GET "/save-link" [url] (save-link url)))
