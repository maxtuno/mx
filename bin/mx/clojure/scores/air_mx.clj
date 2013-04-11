(ns mx.clojure.scores.air-mx
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic.protocols]
        [clojure.core.logic :exclude [is] :as l])
  (:require [clojure.core.logic.fd :as fd])
  (:import [jm.music.data
            Score            
            Part
            Phrase
            Note])
  (:import [jm JMC])
  (:import [jm.util Write]))

(def midi-path "/Users/maxtuno/Desktop/mx/")

(mx.java.Extractor/exec (str midi-path "Air on the G String - Bach.mid") "air")
  
(use 'mx.clojure.scores.air-score)

(defn make-music
  []
  (let [score        (new Score  title tempo)                  
        piano-part-1 (new Part   "Piano 1" JMC/VIOLIN 0) 
        piano-part-2 (new Part   "Piano 2" JMC/VIOLIN 0) 
        piano-part-3 (new Part   "Piano 3" JMC/VIOLA 0) 
        piano-part-4 (new Part   "Piano 4" JMC/CELLO 0)        
        phrase-1     (new Phrase "Phrase 1" 0.0)
        phrase-2     (new Phrase "Phrase 2" 0.0)
        phrase-3     (new Phrase "Phrase 3" 0.0)
        phrase-4     (new Phrase "Phrase 4" 0.0)]            
    
    (.setTimeSignature score (first time-signature) (second time-signature))
    (.setKeySignature score key-signature)
    
    (doseq [note voice-1]
      (let [pitch    (first  note)
            rhythm   (second note)
            velocity (last   note)]              
        (.addNote phrase-1   (new Note pitch rhythm velocity)))) 
    
    (doseq [note voice-2]
      (let [pitch    (first  note)
            rhythm   (second note)
            velocity (last   note)]              
        (.addNote phrase-2   (new Note pitch rhythm velocity)))) 
    
     (doseq [note voice-3]
      (let [pitch    (first  note)
            rhythm   (second note)
            velocity (last   note)]              
        (.addNote phrase-3   (new Note pitch rhythm velocity)))) 
     
    (doseq [note voice-4]
      (let [pitch    (first  note)
            rhythm   (second note)
            velocity (last   note)]              
        (.addNote phrase-4   (new Note pitch rhythm velocity)))) 
    
    (.addPhrase piano-part-1 phrase-1)
    (.addPhrase piano-part-2 phrase-2)
    (.addPhrase piano-part-3 phrase-3)
    (.addPhrase piano-part-4 phrase-4)
    
    (.addPart   score piano-part-1)
    (.addPart   score piano-part-2)
    (.addPart   score piano-part-3)
    (.addPart   score piano-part-4)
    
    (Write/midi score (str midi-path title " " (new java.util.Date) ".mid"))))

(make-music)
