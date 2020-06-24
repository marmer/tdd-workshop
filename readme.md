# TDD-Workshop Showcases
Im Masterbranch befindet sich die globale Konfiguration. Die tatsächlichen Show-Cases befinden sich in den entsprechend benannten anderen Branches 


## Anforderungen
Zum Bauen des Projektes ist Mindestens ein korrekt eingerichtetes JDK in der Version 12 notwendig. In dem Fall könnte alles mit Gradle gebaut werden. 

Ferner ist eine Installation von Node (ab Verison 12) hilfreich, wenn mit Javascript oder Typescript gearbeitet wird.

## Bauen

### Gradle (Java, Kotlin, Typescript, Javascript)
```.\gradlew.bat build```

Durch auskommentieren von ```dependsOn("npm_run_test")``` in ```build.gradle.kts```  wird nur noch der Java und Kotlin Teil gebaut.

### Node (Javascript und Typescript)
Hier kann wahlweise mit npm oder yarn gebaut werden

#### Yarn
```yarn```

#### NPM
```npm install```

### Maven (Java (*deprecated*))
Funktioniert nur in einigen wenigen alten Branches wie "den Minesweeper branches"

```mvn install``` 
