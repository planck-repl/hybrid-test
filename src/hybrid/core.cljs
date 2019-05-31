(ns hybrid.core
  (:require
   [goog.object :as gobj]
   [planck.io :as io]))

(defn platform []
  (case (gobj/get (js/PLANCK_UNAME) "sysname")
    "Darwin" "macos"
    "Linux" "linux"))

(defn machine []
  (gobj/get (js/PLANCK_UNAME) "machine"))

(def ^:private so-path (:path (io/as-file (io/resource (:path (io/file (platform) (machine) "hybrid-test.so"))))))

(def ^:private libnative (js/PLANCK_DLOPEN so-path))

(def ^:private funky-sym (js/PLANCK_DLSYM libnative "funky"))

(defn funky 
  "Calculates sinh(1.0 / tgamma(x))"
  [x]
  (js/PLANCK_NATIVE_CALL funky-sym 3 #js [3] #js [x]))
