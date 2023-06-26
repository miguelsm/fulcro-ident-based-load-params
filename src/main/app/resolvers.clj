(ns app.resolvers
  (:require [com.wsscode.pathom.connect :as pc]
            [taoensso.timbre :as log]))

(def people-table
  {1 {:person/id 1 :person/name "Sally" :person/age 32}
   2 {:person/id 2 :person/name "Joe" :person/age 22}
   3 {:person/id 3 :person/name "Fred" :person/age 11}
   4 {:person/id 4 :person/name "Bobby" :person/age 55}})

(pc/defresolver ident-based [{{:keys [params]} :ast} {:person/keys [id]}]
  {::pc/input  #{:person/id}
   ::pc/output [:person/age :person/id :person/name]}
  (log/info "params:" params)
  (get people-table id))

(pc/defresolver server-property [{{:keys [params]} :ast} _]
  {::pc/output [{:people [:person/age :person/id :person/name]}]}
  (log/info "params:" params)
  {:people (vals people-table)})

(def resolvers [ident-based
                server-property])
