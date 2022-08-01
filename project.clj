(defproject debug-resource "2022-08-01-0908"
  :dependencies [[http-kit "2.5.3"]
                 [metosin/reitit-ring "0.5.15"]
                 [org.clojure/clojure "1.11.0"]
                 [ring/ring-jetty-adapter "1.9.5"]
                 [ring/ring-core "1.9.5"]
                 [ring/ring-devel "1.9.5"]]
  :repl-options {:init-ns debug-resource.core}
  :main ^:skip-aot debug-resource.core
  :source-paths ["src"]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
