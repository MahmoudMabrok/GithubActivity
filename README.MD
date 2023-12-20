# Github Activity


## Screenshots

### Desktop
![Screenshot 2023-12-20 at 10 11 57 PM](https://github.com/MahmoudMabrok/GithubActivity/assets/13488900/01a8a40f-bc4e-4e49-b7ed-a0db144d650c)
![Screenshot 2023-12-20 at 10 12 04 PM](https://github.com/MahmoudMabrok/GithubActivity/assets/13488900/20520de3-e7a0-4418-bd6b-9fb65b75de5d)



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

