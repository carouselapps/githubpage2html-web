(ns githubpage2html-web.routes.home
  (:require [githubpage2html-web.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :refer [ok]]
            [clojure.java.io :as io]
            [githubpage2html-web.db.core :as db]
            [bouncer.core :as b]
            [bouncer.validators :as v]
            [ring.util.response :refer [redirect]]
            [githubpage2html.core :as g2html]))

(defn validate-page [params]
  (first
    (b/validate
      params
      :url v/required)))

(defn save-page! [{:keys [params]}]
  (if-let [errors (validate-page params)]
    (-> (redirect "/")
        (assoc :flash (assoc params :errors errors)))
    (do
      ((if (db/get-page (params :url)) db/update-page! db/save-page!)
        (assoc params :timestamp (java.util.Date.)
                      :content (g2html/gen-html (g2html/get-content (params :url)))))
      (redirect "/"))))

(defn delete-page! [id]
  (if-let [id (re-find  #"^\d+$" id)]
    (do
      (db/delete-page! (Integer/parseInt id))
      (redirect "/"))
    (redirect "/not-found")))


(defn home-page [{:keys [flash]}]
  (layout/render
    "home.html"
    (merge {:pages (db/get-pages)}
           (select-keys flash [:url :content :errors]))))

(defn about-page []
  (layout/render "about.html"))

(defn not-found []
  (layout/render "not-found.html"))

(defroutes home-routes
  (GET "/" request (home-page request))
  (GET "/about" [] (about-page))
  (GET "/not-found" [] (not-found))
  (POST "/" request (save-page! request))
  (GET "/delete/:id" [id] (delete-page! id)))

