(ns link-grabber.parsers.search-url.form-with-q-input
  (:require [net.cgrand.enlive-html :as html]))

(defn find-forms [page]
  (-> page
      (html/select [[ :form (html/has [[:input (html/attr= :name "q")]])]])))

(defn search-url-from [forms]
  (map #(str (get-in % [:attrs :action]) "?q={search-term}") forms))

(defn parse-search-url [page]
  (->> page
       (find-forms)
       (search-url-from)))
