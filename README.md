
# README #
### Chargers is an Android Application with login, fetching and listing of data.

### Features ###
* Show login screen if user is not logged in yet.
* If login was successful, save token and redirect user to stations list screen.
* Set access token as header for each back-end request
* If JWT invalid, redirect to login page.
* If user is already logged in, show station list screeen.
* Station list screen shows the list of 10 stations (ordered by distance to user current location, nearest first).
* Station list screen has two feature toggles: to hide / show "kW" label and distance info.

### Architecture, language, DI etc... ###
* This App is written in Kotlin.
* Clean architecture pronciples (just Use Cases skipped, because of simple business logic).
* MVVM on presentation layer.
* Reactive pattern is implemented with Kotlin Coroutines and LiveData / Flow.
* UiState with 2 approaches: Login screen -- combined with LiveData, Charges list screen -- Flow.
* Dependency injection is implemented with Koin.
* Local DB: ROOM.
* Navigation component.

### Summary of set up: ###
* This app is created with AndroidStudio using the Gradle build system.
* First download the source code.
* In Android Studio, use "Import Project" option. Next select the app directory that you downloaded from this repository. If prompted for a gradle configuration accept the default settings.
* Alternatively use the "gradlew build" command to build the project directly.

### Repo owner: Sergei Kasianov (lyydikoi@gmail.com)###
