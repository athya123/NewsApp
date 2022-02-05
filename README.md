# NewsApp

Link to apk and app demo -> [Google Drive](https://drive.google.com/drive/folders/1edS5_cFDby-1mW7LCmrBkRaRvRTfagLe?usp=sharing)

A simple app to fetch news from NewsApi and show ads from admob.

***

The following features were implemented :-

- MVVM Architecture implemented in Kotlin language.

- Activity responsible for the UI code.

- ViewModel responsible for providing data required by UI class.

- Repository layer provide data to ViewModel Class.

- Data fetched from API [https://newsapi.org/]

- Data changes when the user’s location changes (if the user is in US, it shows US specific news, etc.)

- Added native ads from Admob in news feed.

- Using Firebase Remote config to switch on and off ads from console.

- Used Hilt for dependency injection, Retrofit for network calls, Glide for image processing, RxJava 3 for Reactive programming, LiveData for UI updates and data binding for binding data to data sources. 
