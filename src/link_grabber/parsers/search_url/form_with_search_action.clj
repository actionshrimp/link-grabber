(ns link-grabber.parsers.search-url.form-with-search-action
  (:require [net.cgrand.enlive-html :as html]))

(defn find-forms [page]
  (-> page
      (html/select [[:form (html/attr-contains :action "search")]])))

(defn search-url-from [forms]
  (map #(str (get-in % [:attrs :action]) "?q={search-term}&this-is=wrong") forms))

(defn parse-search-url [page]
  (->> page
       (find-forms)
       (search-url-from)))
