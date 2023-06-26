(ns app.client
  (:require [com.fulcrologic.fulcro.application :as app]
            [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
            [com.fulcrologic.fulcro.dom :as dom]))

(defonce app (app/fulcro-app))

(defsc Root [this props]
  (dom/div {} "Hello world!"))

(defn ^:export init []
  (app/mount! app Root "app")
  (js/console.log "Loaded"))

(defn ^:export refresh []
  (app/mount! app Root "app")
  (comp/refresh-dynamic-queries! app)
  (js/console.log "Hot reload"))
