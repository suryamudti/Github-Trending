# Github Trending App

## Description
Github Trending App is a simple app showing the list of popular repositories from github,
this App was built with Android Studio 3.5, and Using [Kotlin](https://kotlinlang.org/docs/reference/) as the Programming language,

This App also using [GithubTrendingApi](https://github-trending-api.now.sh) as Server Back-end API as remote data source.


Tech stack included :

- [Kodein](https://github.com/Kodein-Framework/Kodein-DI) (Dependency Injection)
    Kodein is a very simple and yet very useful dependency retrieval container. it is very easy to use and configure.

- [Room](https://developer.android.com/topic/libraries/architecture/room) (Persistance Storage)
    The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.

- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) (Architecture Component)
    The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.

- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) (Architecture Component)
    LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

- [DataBinding](https://developer.android.com/topic/libraries/data-binding/) (Architecture Component)
    The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.

- [Material Design Component](https://material.io/components/) (Design)
    Material Components are interactive building blocks for creating a user interface.

- [Expandable RecyclerView](https://github.com/hendraanggrian/recyclerview-expandable) (Recyclerview Framework)
    Expandable RecyclerView layout library.

- [Shimmer](https://github.com/facebook/shimmer-android) (Loading layout)
    An easy, flexible way to add a shimmering effect to any view in an Android app.

- [Retrofit](https://github.com/square/retrofit) (Network Request)
    Type-safe HTTP client for Android and Java by Square, Inc. https://square.github.io/retrofit/

- [Mockito](https://developer.android.com/training/testing/unit-testing/local-unit-tests) (Unit Test)
    Mockito is a mocking framework that tastes really good. It lets you write beautiful tests with a clean & simple API. Mockito doesn’t give you hangover because the tests are very readable and they produce clean verification errors.
    
- [MVVM](https://developer.android.com/jetpack/docs/guide) (Google Recommended Architecture Pattern)
    MVVM stands for Model, View, ViewModel.
    
    - Model: This holds the data of the application. It cannot directly talk to the View. Generally, it’s recommended to expose the data to the ViewModel through Observables.
    - View: It represents the UI of the application devoid of any Application Logic. It observes the ViewModel.
    - ViewModel: It acts as a link between the Model and the View. It’s responsible for transforming the data from the Model. It provides data streams to the View. It also uses hooks or callbacks to update the View. It’ll ask for the data from the Model.

Next Step : 

- Implementing Modularization
- Implementing Jetpack Compose (Coming Soon)

Happy Coding 🎉