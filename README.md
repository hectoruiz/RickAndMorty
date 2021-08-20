# RickAndMorty

Simple app with a list with some characters from RickAndMorty populated on a recyclerview. Every item on the list allows, if have valid location Url, navigate to the last know location about the character clicked. Also the residents of every location can be checked.

Libraries used:
- Dagger Hilt: For dependency injection.
- Coroutines: To retrieve data from API rest and Shared Preferences outside the UI Thread.
- LiveData: According observer pattern used to get noticed changes from view models on the views.
- Picasso: Retrieve the images from an URL.
- Retrofit: Rest Client for HTTP requests.
- Moshi: JSON serialization/deserialization
- Architecture Components:
    - Navigation: Used single activity with two fragments. Flow between fragments through navigation.
    - SafeArgs: For passing parameters through fragments using navigation.
    - ViewBinding: Linking the layouts with the views.
    - ViewModels: At the presentation layer.
    
- Used clean architecture with a modularized project.
