# Gojek Trend App

## Description
Gojek Trend App is a simple app showing the list of popular repositories from github
this App was built with Android Studio 3.5, and Using Kotlin as the Programming language,

This App also using [GithubTrendingApi](https://github-trending-api.now.sh) as Server Back-end API as remote data source.


## Installation

If you dont have Android Studio, this link may clearly guide you,
[Android Studio Instalation](https://developer.android.com/studio/install)

You can found and get the apk file by execute this command,
If you running in Unix, open your terminal and type this command
```bash
home/AndroidStudioProjects/GojekTrendApp/app/app-release.apk
```
if you running in Windows, open your Command Prompt and type this command
```bash
AndroidStudioProjects\GojekTrendApp\app\release\app-release.apk

```
After get the apk file you may copy it to your android device to continue the instalation.


## Libraries
library were include in this project are

```
    //Essensial libraries
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation"org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.core:core-ktx:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'com.google.android.material:material:1.2.0-alpha01'

    //ViewModel and LiveData
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0'
    implementation "androidx.lifecycle:lifecycle-extensions:2.1.0"

    //Android Room
    implementation "androidx.room:room-runtime:2.2.1"
    implementation "androidx.room:room-ktx:2.2.1"
    kapt "androidx.room:room-compiler:2.2.1"

    //Kotlin Coroutines
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.0"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.0"

    //Kodein Dependency Injection
    implementation "org.kodein.di:kodein-di-generic-jvm:6.2.1"
    implementation "org.kodein.di:kodein-di-framework-android-x:6.2.1"

    //Retrofit and GSON
    implementation 'com.squareup.retrofit2:retrofit:2.6.2'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.2'
    implementation 'com.squareup.okhttp3:mockwebserver:3.14.0'

    //Swipe Refresh
    implementation "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"

    //Glide Images
    implementation 'com.github.bumptech.glide:glide:4.10.0'

    //Shimmer Loading
    implementation 'com.facebook.shimmer:shimmer:0.5.0@aar'

    //Three Ten Bp DateTime Util
    implementation 'com.jakewharton.threetenabp:threetenabp:1.2.1'
    testImplementation ('org.threeten:threetenbp:1.3.2'){
        exclude group:'com.jakewharton.threetenabp', module:'threetenabp'
    }

    //Expandable RecyclerView
    implementation 'io.github.hendraanggrian:expandablelayoutrecyclerview:0.1.1'

    //Circle ImageVIew
    implementation 'de.hdodenhof:circleimageview:3.0.1'

    //Testing
    testImplementation 'junit:junit:4.12'
    testImplementation "org.mockito:mockito-inline:3.0.0"
    testImplementation "androidx.arch.core:core-testing:2.1.0"
    testImplementation 'com.squareup.okhttp3:mockwebserver:3.14.0'
    testImplementation "androidx.test:core:1.2.0"
    testImplementation "androidx.test:rules:1.2.0"
    testImplementation "androidx.test.ext:junit:1.1.1"
    testImplementation "org.robolectric:robolectric:4.2.1"
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
    androidTestImplementation "androidx.test.espresso:espresso-contrib:3.2.0"
    androidTestImplementation "androidx.arch.core:core-testing:2.1.0"
    androidTestImplementation 'com.squareup.okhttp3:mockwebserver:3.14.0'
    androidTestImplementation "androidx.test:core:1.2.0"
    androidTestImplementation "androidx.test:rules:1.2.0"

    //Dependencies for Idle Resource
    implementation "androidx.test.espresso:espresso-idling-resource:3.2.0"
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## Thanks
Thank you GO-JEK