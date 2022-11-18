# TDD-Workshop

Vor der Durchführung des Workshops bitte einmal auschecken, in die eigene IDE der Wahl importieren und bauen. Sollte der
Fokus des Workshops auf Java oder Kotlin liegen, das Projekt einmal mit Maven oder Gradle bauen und die Tests ausführen.
Im Fall eines Fokus auf Javascript oder Typescript mit npm oder yarn.

Im Folgenden die Aufgaben. Unter [System-Anforderungen](#System-Anforderungen) stehen die Anforderungen zum Bauen des
Repos

## Aufgaben Morse Kata:

Ziel der Kata ist es, über alle Aufgaben hinweg eine Anwendung zu entwickeln, mit der Morsecodes in Text und wieder
zurück übersetzt werden können. Aus didaktischen Gründen ist die Kata in mehrere Aufgaben unterteilt, welche
nacheinander bearbeitet werden sollen, jedoch ohne sich die darauf folgenden Aufgaben anzuschauen. Die Aufgabe kann mit
beliebigen Sprachen und Programmierparadigmen testgetrieben gelöst werden.

### Aufgabe1 - Morse Encoding - Gruppenübung

#### Takeaways

* Babysteps
* Pris "zuvieler" Tests
* Paradigmen und Sprachen egal

#### Aufgabenstellung:

* Gegeben: **Zeichenkette** wird übersetzt in **Morse-String**
    * Grundannahme: Niemand übergibt Zeichenketten, die nicht mindestens ein Wort mit der Länge von zwei Zeichen
      beinhalten, da die Übertragung anderenfalls nutzlos wäre
* Fertiges Dictionary: siehe [/src/main/resources/morse.dict](/src/main/resources/morse.dict)
* Unbekannte **Zeichen** werden zum **"Fragezeichen-Morsezeichen"** übersetzt
* Ein Morsecode baut sich wie folgt auf:
    * Ein "Zeichen" wird repräsentiert durch Punkte und Striche
    * Zwischen Buchstaben gibt es ein Leerzeichen (kleine Pause)
    * Zwischen Worten gibt es drei Leerzeichen (lange Pause)
    * Bsp.: "Der Mops" = "-.. . .-. -- --- .--. ..."

### Aufgabe2 - Morse Decoding - Einzel-/Kleingruppenübung

#### Takeaways

* Babysteps
* Pris "zuvieler" Tests
* Preis von Copy Paste bei Tests
* Paradigmen und Sprachen egal

#### Aufgabenstellung:

* Gegeben: **Morse-String** wird übersetzt in **Zeichenkette**
    * Grundannahme: Niemand übergibt Zeichenketten, die nicht mindestens ein Wort mit der Länge von zwei Zeichen
      beinhalten, da die Übertragung anderenfalls nutzlos wäre
* Fertiges Dictionary: siehe [/src/main/resources/morse.dict](/src/main/resources/morse.dict)
* Unbekannte **Morsezeichen** werden zum **"Fragezeichen"** übersetzt
* Ein Morsecode baut sich wie folgt auf:
    * Ein "Zeichen" wird repräsentiert durch Punkte und Striche
    * Zwischen Buchstaben gibt es ein Leerzeichen (kleine Pause)
    * Zwischen Worten gibt es drei Leerzeichen (lange Pause)
    * Bsp.: "-.. . .-. -- --- .--. ..." - "Der Mops"

### Aufgabe3 - CLI mit Konsolenausgabe - Gruppenübung

#### Takeaways

* Mocking
* Humble Object Pattern
* Shellscript-Test (Optional)

#### Aufgabenstellung:

* Domain wird in CLI-Anwendung umgesetzt
* Die Anwendung hat genau **einen** Parameter und erkennt selbstständig ob...
    * ...es sich um eine Reihe von Morsezeichen handelt
    * ...es sich um eine Reihe "normaler" Zeichenketten handelt
* Das Ergebnis der Übersetzung wird auf der Konsole ausgegeben

### Aufgabe4 - CLI mit Dateiausgabe - Einzel-/Kleingruppenübung

#### Takeaways

* Refactoring
* Umgang mit Abhängigkeiten zwischen Source-Code-Artefakten
* Umgang mit unklaren Anforderungen (frühes Hinterfragen)
* Three Amigos Principle

#### Aufgabenstellung:

* Domain wird in CLI-Anwendung umgesetzt
* **Der letzte Parameter enthält die zu übersetzende Zeichenkette** und erkennt ob...
    * ...es sich um eine Reihe von Morsezeichen handelt
    * ...es sich um eine Reihe "normaler" Zeichenketten handelt
* Das Ergebnis der Übersetzung wird **per default** auf der Konsole ausgegeben
* **Es ist nun möglich mit den Schaltern -i bzw. -o ein Input- oder Outputfile anzugeben**
    * **"morse.jar [-o=\<outFile>][-i=\<inFile>|\<MorseString>]"**
    * **Jedes File kann mehrere zu übersetzende Zeilen beinhalten**
    * **Es existiert nur entweder das inFile doer der MorseString**

### Aufgabe5 - Signalverarbeitung - Einzel-/Kleingruppenübung

#### Takeaways

* Refactoring
* Umgang mit Abhängigkeiten zwischen Source-Code-Artefakten

#### Aufgabenstellung

* Es kann eine Repräsentation von unterschiedlich lang angelegten "elektrischen Signalen", welche in Form von "Nullen" (
  kein Signal liegt an) und Einsen (Signal liegt an) zur Übersetzung übergeben werden
    * Hinweis 1: Elektrisch über Morse zu Zeichenkette
    * Hinweis 2: Es sind nur Signale mit sauberer Abtastung und gleichmäßigen Längen übergeben
* Es sind unterschiedliche Timings möglich (Hinweis: Fante mit Timing1 an)

|                   Signaltyp | Timing **Faktor** | Beispiel Timing = 1      | Beispiel Timing = 3                     |
|----------------------------:|:-----------------:|:-------------------------|:----------------------------------------|
|       Pause zwischen Worten |         7         | 0000000 <br/>*(Länge 7\*1=7)* | 000000000000000000000 <br/>*(Länge 7\*3=21)* |
|   Pause zwischen Buchstaben |         3         | 000     <br/>*(Länge 3\*1=3)* | 000000000             <br/>*(Länge 3\*3=9)*  |
| Pause zwischen Morsezeichen |         1         | 0       <br/>*(Länge 1\*1=1)* | 000                   <br/>*(Länge 1\*3=3)*  |
|                      Strich |         3         | 111     <br/>*(Länge 3\*1=3)* | 111111111             <br/>*(Länge 3\*3=9)*  |
|                       Punkt |         1         | 1       <br/>*(Länge 1\*1=1)* | 111                   <br/>*(Länge 1\*3=3)*  |

## System-Anforderungen

### JDK (JVM Sprachen)

Zum Bauen des Projektes ist mindestens ein korrekt eingerichtetes JDK in der Version 11 notwendig. In dem Fall könnte
alles mit Gradle gebaut werden.

### Node (Nur bei mit Node laufenden Sprachen)

Ferner ist eine Installation von Node (ab Verison 16) hilfreich, wenn mit Javascript oder Typescript gearbeitet wird.

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
