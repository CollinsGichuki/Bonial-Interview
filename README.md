<p align="center">
  <h2>Bonial-Interview</h2>

  <p>
  A simple Android app built with Kotlin consuming provided API endpoint to display brochures in a gridLayout. It is built using the Repository and MVVM patterns as well as Architecture Components.
  
  Min Api Level : 21 Supports over 87% devices
  
  Build System : Gradle
    </p>
</p>

### Architecture
MVVM is a Model-View-ViewModel architecture that removes the tight coupling between each component.
Most importantly, in this architecture the children don't have the direct reference to the parent, 
they have the reference through observables.

### Layers
- Model
    - It represents the data and the business logic of the Android application. It consists of the logic - local
and remote data source, model classes and the repository.

- View
    - It consists of the UI code(Activity), XML. It sends the user action to the ViewModel but does not 
get the response back directly. To get the response, it has to subscribe to the observables which the ViewModel 
exposes to it.

- ViewModel
    - This is a bridge between the View and the Model(Business Logic). It does not have any clue which view has to use it
as it does not have a direct reference to the View. 

### Libraries Added

- [Jetpack🚀](https://developer.android.com/jetpack)
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage UI related data in a lifecycle conscious way and act as a channel between the repository and the ui
    - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - support library that allows binding of UI views by providing a binding class that contains direct references to all views that have an ID in the corresponding layout.
    - [Room](https://developer.android.com/training/data-storage/room) - Provides abstraction layer over SQLite
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - a life-cycle aware observable data holder class

- [Coroutines](https://developer.android.com/kotlin/coroutines) - a concurrency design pattern that you can use on Android to simplify code that executes asynchronously
- [DiffUtil](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil) - A utility class that calculates the difference between two lists and outputs a list of update operations that converts the first list into the second one. DiffUtil is more efficient than using notifyDataSetChanged().
- [Retrofit](https://square.github.io/retrofit/) - type safe http client and supports coroutines out of the box.
- [Gson](https://github.com/google/gson) - A Java serialization/deserialization library to convert Java Objects into JSON and back.
- [ok-http-logging-interceptor](https://square.github.io/okhttp/interceptors/) - intercepts the request and adds the api key
- [Glide](https://github.com/bumptech/glide) - An image loading and caching library for Android focused on smooth scrolling
- [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Lightweight dependency injection library for android.
- [Truth](https://truth.dev/) - It is an assertion library used in Android testing.
- [JUnit](https://junit.org/junit4/) - It is a simple framework to write repeatable tests.
