plugins {
    id("com.android.application")
    kotlin("android")

    ///
    id("kotlin-android")
    id("kotlinx-serialization")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")

}


android {
    dataBinding {
        isEnabled = true
    }
    compileSdk = 32
    defaultConfig {
        applicationId = "com.harman.mvvmkmmsample.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-beta03"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

val kotlinVersion = "1.6.10"
val gradleVersion = "7.2.0"
//Android
val androidBuildToolsVersion = "30.0.3"
val androidMinSdkVersion = 26
val androidTargetSdkVersion = 31
val androidCompileSdkVersion = 31

//Libraries
val supportLibraryVersion = "1.4.1"
val materialVersion = "1.5.0"
val coreKtxVersion = "1.7.0"
val constraintLayoutVersion = "2.1.3"
val koinVersion = "3.1.5"
val gsonVersion = "2.8.9"
val lifecycleVersion = "2.2.0"//todo move 2.4.0, get rid of extensions artifact
val navigationVersion = "2.5.0-rc01"
val kotlinCoroutinesVersion = "1.6.0"
val roomVersion = "2.4.1"
val pagingVersion = "3.1.0"
val retrofitVersion = "2.9.0"
val okhttpVersion = "4.9.3"
val composeVersion = "1.2.0-alpha05"
val activityVersion ="1.6.0-alpha04"
val composePaging = "1.0.0-alpha15"
val uiPreview ="1.1.1"

//Testing
val jUnitVersion = "4.12"
val espressoVersion = "3.4.0"
val coreTestingVersion = "2.1.0"
val composeViewModel = "2.4.1"



dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":shared"))
    //material
    implementation("com.google.android.material:material:1.4.0")
    //app compact
    implementation("androidx.appcompat:appcompat:1.3.1")
    //constraint
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    //koin
    implementation("io.insert-koin:koin-core:3.1.5")
    implementation("io.insert-koin:koin-android:3.1.5")


    api("org.jetbrains.kotlin:kotlin-stdlib-common")

    //ktor
    implementation("io.ktor:ktor-client-core:1.6.3")
    implementation("io.ktor:ktor-client-json:1.6.3")
    implementation("io.ktor:ktor-client-logging:1.6.3")
    implementation("io.ktor:ktor-client-serialization:1.6.3")

    implementation("androidx.core:core-ktx:$coreKtxVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.0-alpha06")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.navigation:navigation-fragment:$navigationVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinCoroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.databinding:databinding-common:4.2.2")
    implementation("androidx.paging:paging-runtime:$pagingVersion")
    implementation ("androidx.paging:paging-compose:$composePaging")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.activity:activity-ktx:$activityVersion")
    debugImplementation ("androidx.compose.ui:ui-tooling:$uiPreview")
    implementation ("androidx.compose.ui:ui-tooling-preview:$uiPreview")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$composeViewModel")

}