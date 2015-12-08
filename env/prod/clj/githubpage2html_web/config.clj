(ns githubpage2html-web.config
  (:require [taoensso.timbre :as timbre]))

(def defaults
  {:init
   (fn []
     (timbre/info "\n-=[githubpage2html-web started successfully]=-"))
   :middleware identity})
