# TDD-Workshop

Im Masterbranch befindet sich die globale Konfiguration. Die tatsächlichen Show-Cases befinden sich in den entsprechend
benannten anderen Branches

## Anforderungen

### JDK (JVM Sprachen)

Zum Bauen des Projektes ist mindestens ein korrekt eingerichtetes JDK in der Version 11 notwendig. In dem Fall könnte
alles mit Gradle gebaut werden.

### Node (Nur bei mit Node laufenden Sprachen)

Ferner ist eine Installation von Node (ab Verison 12) hilfreich, wenn mit Javascript oder Typescript gearbeitet wird.

## Maven

Nicht zwingend erforderlich, da ein Wrapper mitgeliefert wird

## Gradle

Nicht zwingend erforderlich, da ein Wrapper mitgeliefert wird

## IDE

Hier kann die IDE der Wahl verwendet werden. Folgende Empfehlungen werden gemacht

### IntelliJ Ultimate

Der Roundhousekick der IDEs (in dem Kontext) kann für beliebige Sprachen verwendet werden und ist in dem Kontext die
Empfohlene IDE

### IntelliJ Community

Kann für Java und Kotlin out of the Box verwendet werden. Wird empfohlen, wenn nicht mit Node gearbeitet wird.

### Visual Studio Code

Kann für Nodesprachen wie Javascript oder Typescript out of the Box verwendet werden. Wird empfohlen, wenn nur mit Node
gearbeitet wird.

### Eclipse

Kann ohne Weiteres nur für Java verwendet werden. Es wird entsprechend nur in dem Kontext empfohlen und auch nur, wenn
das die bevorzugte IDE ist. Hierzu sollte vor dem Import in die IDE einmal das folgende Kommando ausgeführt werden um
Eclipse Spezifische Dateien zu generieren:

```.\gradlew.bat eclipse```

## Build Tools

### Maven (Java, Kotlin)

```.\mvnw.bat clean install```
bzw.
```mvnw clean install```

### Gradle (Java, Kotlin)

```.\gradlew.bat build```
bzw.
```gradlew build```

Durch einkommentieren von ```dependsOn("npm_run_test")``` in ```build.gradle.kts``` sollte ein Bauen aller hier
benannten Sprachen mit einem Kommando möglich sein.

### Node (Javascript und Typescript)

Hier kann wahlweise mit npm oder yarn gebaut werden

#### Yarn

```yarn```

#### NPM

```npm install```

### Maven (Java (*deprecated*))

Funktioniert nur in einigen wenigen alten Branches wie "den Minesweeper branches"

```.\mvnw.cmd clean install```
bzw.
```mvnw clean install``` 
