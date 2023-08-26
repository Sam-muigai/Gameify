## Gameify
This is an android that fetches Games from [freeToGame](https://www.freetogame.com/api-doc) API.It allows one to check the recommendations based on categories.Also allows one to search for games.

## Demo

### Home Screen
<img src="screenshots/home.jpeg" width="250"/>

### Loading Screen
<img src="screenshots/loading.jpeg" width="250"/>

### Search Screen
<img src="screenshots/search.jpeg" width="250"/>

### Game Screen
<img src="screenshots/game.jpeg" width="250"/>

## Model-View-ViewModel
MVVM is a software architectural pattern that separates the presentation logic (View) from the business logic (Model) and introduces a middle layer (ViewModel) to mediate communication between them. This design pattern promotes better code organization, testability, and maintainability of Android applications.

## Tech Stack
- The app is entirely written in [Kotlin](https://kotlinlang.org/)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Hilt provides a standard way to use DI in your application by providing containers for every Android class in your project and managing their lifecycles automatically.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs.
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - A concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
- [Coil](https://coil-kt.github.io/coil/compose/) - A Kotlin-first image loading library for Android which uses Kotlin Coroutines behind the hood.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe REST client for Android and Java which aims to make it easier to consume RESTful web services.
- [Kotlin Flows](https://developer.android.com/kotlin/flow) - Aids in handling streams of data asynchronously which is being executed sequentially.
- [Moshi](https://github.com/square/moshi) - a modern JSON library for Android, Java and Kotlin. It makes it easy to parse JSON into Java and Kotlin classes.
- [Timber](https://github.com/JakeWharton/timber) - Timber is a logger with a small, extensible API. It provides utility on top of android's standard Log class.