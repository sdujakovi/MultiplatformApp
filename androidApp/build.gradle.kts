
plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
}

val materialVersion = "1.5.0"
val koinVersion = "3.2.0"
val composeVersion = "1.3.0"
val composeNavVersion = "2.5.1"
val composeActVersion = "1.6.0-alpha01"
val composeCoilVersion = "2.0.0-rc03"

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.example.multiplatformapp.android"
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
        kotlinCompilerExtensionVersion = "1.3.0"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))

    // Material
    implementation("com.google.android.material:material:$materialVersion")

    // Core
    implementation ("androidx.core:core-ktx:1.8.0")

    // Compose
    implementation("androidx.compose.ui:ui:1.2.1")
    implementation("androidx.compose.ui:ui-graphics:1.2.1")
    implementation("androidx.compose.ui:ui-tooling:1.2.1")
    implementation("androidx.compose.material:material:1.2.1")
    implementation("androidx.navigation:navigation-compose:$composeNavVersion")
    implementation("androidx.activity:activity-compose:$composeActVersion")
    implementation("io.coil-kt:coil-compose:$composeCoilVersion")
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.23.1")

    // Koin
    implementation("io.insert-koin:koin-android:$koinVersion")
}