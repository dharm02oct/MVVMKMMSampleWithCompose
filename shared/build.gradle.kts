plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("kotlinx-serialization")
    id("com.squareup.sqldelight")

}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlin:kotlin-stdlib-common")
                //coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2-native-mt")

                // KTOR
                implementation("io.ktor:ktor-client-core:1.6.3")
                implementation("io.ktor:ktor-client-json:1.6.3")
                implementation("io.ktor:ktor-client-logging:1.6.3")
                implementation("io.ktor:ktor-client-serialization:1.6.3")
                // SQL Delight
                implementation("com.squareup.sqldelight:runtime:1.5.3")
                implementation("com.squareup.sqldelight:coroutines-extensions:1.5.3")
                //koin
                implementation("io.insert-koin:koin-core:3.1.5")
                implementation("io.insert-koin:koin-android:3.1.5")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-annotations-common"))
                implementation("org.jetbrains.kotlin:kotlin-test:1.6.20")
               // implementation("com.russhwolf:multiplatform-settings-test:0.9")
                implementation("io.insert-koin:koin-test:3.1.6")
               // implementation("app.cash.turbine:turbine:0.7.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")
                implementation("io.ktor:ktor-client-mock:2.0.0")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.30")
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.5.30")
                // KTOR
                implementation("io.ktor:ktor-client-android:1.6.3")
                // SQL Delight
                implementation("com.squareup.sqldelight:android-driver:1.5.1")
                implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
                implementation("androidx.paging:paging-runtime:3.1.0")
                implementation("com.squareup.sqldelight:android-paging-extensions:1.5.3")
               // koin
                implementation("io.insert-koin:koin-core:3.1.5")
                implementation("io.insert-koin:koin-android:3.1.5")
            }
        }
        val androidTest by getting{
         dependencies {
             implementation("androidx.test.ext:junit-ktx:1.1.3")
             implementation("com.squareup.sqldelight:sqlite-driver:1.5.3")
         }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }

    sqldelight {
        database("ContactDatabase") {
            packageName = "com.harman.mvvmkmmsample.db"
        }
        linkSqlite = true
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
    configurations {
       // create("androidTestApi")
//        create("androidTestDebugApi")
        create("testApi")
        create("testDebugApi")

    }
}
dependencies {
    implementation("androidx.annotation:annotation:1.2.0")
    implementation("androidx.test.ext:junit-ktx:1.1.3")
}
