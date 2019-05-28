(ns hybrid.core
  (:require [planck.io :as io]))

(def ^:private so-path (:path (io/as-file (io/resource "libnative-macos-x86_64.so"))))

(def ^:private libnative (js/PLANCK_DLOPEN so-path))

(def ^:private funky-sym (js/PLANCK_DLSYM libnative "funky"))

(defn funky 
  "Calculates sinh(1.0 / tgamma(x))"
  [x]
  (js/PLANCK_NATIVE_CALL funky-sym 3 #js [3] #js [x]))
