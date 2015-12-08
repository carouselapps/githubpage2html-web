(ns githubpage2html-web.config
  (:require [selmer.parser :as parser]
            [taoensso.timbre :as timbre]
            [githubpage2html-web.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (timbre/info "\n-=[githubpage2html-web started successfully using the development profile]=-"))
   :middleware wrap-dev})
