## Mindvalley Channel

#### The app is to recreate the Channels view that is on the Mindvalley app.

The app consists of 3 sections. New Episode, Channel and Category.
The channel view has a series or course design.
All the data are fetched from 3 different APIs and stored in the local database for offline support.


## Tech stack & Open-source libraries

* Language: Kotlin
* UI: Jetpack compose
* Local database: Room Persistence
* Dependency injection: Koin
* Coroutines + Flow for asynchronous
* ViewModel - UI related data holder, lifecycle aware
* MVVM Architecture (Model - View - ViewModel)
* Clean architecture: UI, Domain & Data layer
* Use cases, Repository
* Coil - loading images.
* Retrofit2 & OkHttp3 - construct the REST APIs and paging network data.
* Gson - JSON representation.
* Swipe Refresh - Pull to refresh
* Mockito & JUnit - Unit & UI testing 
* Timber - logging.

## Challenges

* Challenges faced while using nested LazyColumn with compose view.
There were restrictions of using nested scrollable view and hence put the fixed size for the three sections of the channel view.
* Challenges faced while writing the UI test for the compose layouts as I was doing this for the first time.

## Future enhancements 

* Smooth scroll of the three sections
* Add more tests for the compose layouts
* Error message display for the respective section of the list
* Custom layouts for handling different screen sizes


