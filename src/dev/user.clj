(ns user
  (:require [app.parser]
            [app.server :as server]
            [clojure.tools.namespace.repl :as tools-ns :refer [set-refresh-dirs refresh]]
            [edn-query-language.core :as eql]
            [taoensso.timbre :as log]))

(set-refresh-dirs "src/dev" "src/main")

(defn start []
  (server/start))

(defn restart []
  (server/stop)
  (refresh :after 'user/start))

(comment
  (start)
  (restart)
  ,)

(comment
  (def server-property-query `[{(:people {:foo "bar"}) [:person/name]}])
  (eql/query->ast server-property-query) ;; => has `:params {:foo "bar"}`
  (app.parser/api-parser server-property-query) ;; => logs `params: {:foo "bar"}`

  (def ident-based-query `[{([:person/id 1] {:foo "bar"}) [:person/name]}])
  (eql/query->ast ident-based-query) ;; => has `:params {:foo "bar"}`
  (app.parser/api-parser ident-based-query) ;; logs `params: nil`
  ,)
