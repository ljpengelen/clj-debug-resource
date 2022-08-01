(ns debug-resource.core
  (:require [clojure.java.browse :refer [browse-url]]
            [clojure.java.io :as io]
            [clojure.pprint :as pprint]
            [org.httpkit.server :as http-kit]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.response :refer [resource-response]])
  (:gen-class))

(defonce server (atom nil))

(defn start-http-kit! []
  (when-not @server
    (reset! server (http-kit/run-server #(resource-response "public/index.html") {:port 4001 :join? false}))))

(defn start-jetty! []
  (when-not @server
    (reset! server (run-jetty (fn [_] (resource-response "public/index.html")) {:port 4002 :join? false}))))

(defn stop-http-kit! []
  (when-let [running-server @server]
    (running-server)
    (reset! server nil)))

(defn stop-jetty! []
  (when-let [running-server @server]
    (.stop running-server)
    (reset! server nil)))

(def rr (constantly (resource-response "public/index.html")))

(io/resource "public/index.html")

(io/resource "file:/C:/code/debug-resource/resources/public/index.html")

(io/resource "file:/c:/code/debug-resource/target/uberjar/debug-resource-2022-08-01-0908.jar")

(defn -main [& _]
  (pprint/pprint (resource-response "public/index.html"))
  (pprint/pprint (resource-response "public/index.html"))
  (pprint/pprint (rr))
  (pprint/pprint (rr))
  (pprint/pprint ((constantly (resource-response "public/index.html"))))
  (pprint/pprint ((constantly (resource-response "public/index.html"))))
  (start-jetty!))

(comment
  (start-http-kit!)
  (start-jetty!)
  (browse-url "http://localhost:4001")
  (browse-url "http://localhost:4002")
  (stop-http-kit!)
  (stop-jetty!))
