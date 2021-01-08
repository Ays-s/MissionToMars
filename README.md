# MissionToMars

## Java project
Marie-Amelie Defresne, Ayrwan Guillermo, Adrien Grivey

Second year JAVA project. SOIA / SNS - ENSTA Bretagne. 

### Instaltion with IntelliJ:
- open new project
- select the project folder

### Run application
To run the application with intelliJ IDE you have to modify one option in the configuation settings.
- Select 'ihmMain' as configuration
- click 'Edit confiuration'
- Modifye the VM options :
```
  "path\to\project\folder\MissionToMars\lib\javafx-sdk-11.0.2\lib"
 ```
 For exemple :
 ```
  "C:\Users\ayrwa\IdeaProjects\MissionToMars\lib\javafx-sdk-11.0.2\lib"
 ```
 - run 'ihmMain' (Maj + F10)

### Run the test:
- select testAll as launch configuration
- Run 'testAll' (Maj + F10) to run without coverage
- Run 'testAll' with Coverage to run with coverage


### Issue:
- java: package org.junit.jupiter.api does not exist -> add junit to compile scope in settings (Ctlr + Alt + Shift + s)
