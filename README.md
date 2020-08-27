# quotations
Exploring Android Jetpack through a simple app.

This app serves as just a small demo of Jetpack with special attention to the following components:
 * ViewModel
 * LiveData
 * Room
 
The app follows the MVVM architecture pattern, where the ViewModel will ask for data from a Repository which will give out the locally stored data and then also asks the network for more data, updating everything through RxJava observables.
