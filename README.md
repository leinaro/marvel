# Marvel

This application shows a list with the Marvel characters and the details of each one.
Includes a search view to search characters by name. 

## Set up project

1. Clone project from github [here](https://github.com/leinaro/marvel)

2. Set up local.properties, this app uses [Marvel Comics API](https://developer.marvel.com/) to access information about Marvel's vast library of comics from what's coming up, to 70 years ago.
   - In order to run the project using the API, you need a developer account on [developer marvel](https://developer.marvel.com/account) to get the api keys.
   - Copy your private key and public key on your local.properties as follow:
   ```
   marvel.privateKey="private key"
   marvel.apiKey="public key"
   ```

## Code coverage

For code coverage the project use Jacoco and a gradle task, to check the coverage you can use the next command:
```
./gradlew :{module}:checkCoverage
```

Example: 
```
$ :data:checkCoverage

Code coverage file: $path/Marvel/data/build/reports/coverage/index.html
Coverage report: $path/Marvel/data/build/jacoco/reports/jacocoAndroidTestReport/jacocoAndroidTestReport.xml
```

when coverage passed

```
COVERAGE PASSED ********
INSTRUCTION --> 94.27% expected: 80.0%
LINE ---------> 97.73% expected: 80.0%
METHOD -------> 95.24% expected: 80.0%
CLASS --------> 100.00% expected: 80.0%
************************
```

or failed

```
COVERAGE FAILED *******
INSTRUCTION --> 49.63% expected: 80.0%
LINE ---------> 50.00% expected: 80.0%
METHOD -------> 35.00% expected: 80.0%
CLASS --------> 55.56% expected: 80.0%
************************
```
   
## Architecture

This project architecture is based on:
- [Guide to app architecture](https://developer.android.com/topic/architecture) 
- [Guide to Android app modularization](https://developer.android.com/topic/modularization)
- [Clean architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Android Modular Clean Architecture with Rorty App](https://developersancho.medium.com/android-modular-clean-architecture-with-rorty-app-5b4b08398492)

### Modules 
- [buildSrc module](buildSrc/README.md)
The project have three groups of layers:
- presentation layers
  - [app module](app/README.md)
  - [core module](core/README.md)
  - view/feature modules
    - [characters-list module](characters-list/README.md)
    - [character-search module](character-search/README.md)
    - [character-details module](character-details/README.md)
- domain layers
  - [domain module](domain/README.md)
- data layers
  - [data module](data/README.md)

## Key words

buildSrc, view model, jetpack compose, flow, hilt, retrofit, coil.


## About the developer

https://www.linkedin.com/in/ingenieraadela/