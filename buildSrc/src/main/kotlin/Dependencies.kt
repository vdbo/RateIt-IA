object Versions {
    const val gradleBuildTools = "3.3.0"
    const val kotlin = "1.3.11"
    const val androidMinSdk = 21
    const val androidTargetSdk = 28
    const val appCompat = "1.0.2"
    const val materialDesign = "1.0.0"
    const val constraintLayout = "1.1.3"
    const val mockito = "2.23.4"
    const val dagger = "2.19"
    const val androidKtx = "1.0.0"
    const val rxJava2 = "2.2.0"
    const val rxKotlin = "2.3.0"
    const val rxAndroid = "2.1.0"
    const val rxBinding = "3.0.0-alpha2"
    const val room = "2.1.0-alpha03"
    const val archComponents = "2.0.0"
    const val junit = "4.12"
}

object Dependencies {
    val kotlinLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val materialDesignLibrary = "com.google.android.material:material:${Versions.materialDesign}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val androidKtx = "androidx.core:core-ktx:${Versions.androidKtx}"
    val rxJava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxJava2}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    val rxAndroid2 = "io.reactivex.rxjava2:rxjava:${Versions.rxAndroid}"
    val rxBinding2 = "com.jakewharton.rxbinding3:rxbinding-material:${Versions.rxBinding}"
    val room = "androidx.room:room-runtime:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    val roomRxJava2 = "androidx.room:room-rxjava2:${Versions.room}"
    val archComponents = "androidx.lifecycle:lifecycle-extensions:${Versions.archComponents}"
    val archComponentsCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.archComponents}"
    val junit = "junit:junit:${Versions.junit}"
    val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    val roomTesting = "androidx.room:room-testing:${Versions.room}"
    val archComponentsTesting = "androidx.arch.core:core-testing:${Versions.archComponents}"
}