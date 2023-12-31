# Github Activity


## Screenshots

### Desktop, Android, iOS
![Screenshot 2024-01-04 at 10 23 09 PM](https://github.com/MahmoudMabrok/GithubActivity/assets/13488900/8119847b-5ed9-426a-b09c-078ac6871212)

### WEB 
![Screenshot 2024-01-04 at 11 20 43 PM](https://github.com/MahmoudMabrok/GithubActivity/assets/13488900/e473727e-4111-4539-ba20-7b95a7979c97)



## Before running!
 - check your system with [KDoctor](https://github.com/Kotlin/kdoctor)
 - install JDK 17 on your machine
 - add `local.properties` file to the project root and set a path to Android SDK there

### Android
To run the application on android device/emulator:  
 - open project in Android Studio and run imported android run configuration

To build the application bundle:
 - run `./gradlew :composeApp:assembleDebug`
 - find `.apk` file in `composeApp/build/outputs/apk/debug/composeApp-debug.apk`

### Desktop
Run the desktop application: `./gradlew :composeApp:run`

### iOS
To run the application on iPhone device/simulator:
 - Open `iosApp/iosApp.xcproject` in Xcode and run standard configuration
 - Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio

### Browser
Run the browser application: `./gradlew :composeApp:jsBrowserDevelopmentRun`

