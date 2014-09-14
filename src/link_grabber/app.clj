(ns link-grabber.app
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [link-grabber.handlers.save-link :as save-link]
            ))

(defroutes app-routes
  (save-link/routes)
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
