(defproject link-grabber "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.8"]]
  :plugins [[lein-ring "0.8.11"]]
  :ring {:handler link-grabber.app/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]
                        [clj-http "1.0.0"]
                        [clojurewerkz/urly "1.0.0"]
                        [enlive "1.1.5"]
                        ]}})
