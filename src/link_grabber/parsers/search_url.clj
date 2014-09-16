(ns link-grabber.parsers.search-url
  (:require [link-grabber.parsers.search-url.form-with-q-input :as form-with-q-input]
            [link-grabber.parsers.search-url.form-with-search-action :as form-with-search-action]))

(def parsers [form-with-q-input/parse-search-url
             form-with-search-action/parse-search-url])
