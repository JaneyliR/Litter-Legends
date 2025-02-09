plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("io.realm.kotlin") version "1.11.0" // 🔥 Add this with a version number
}

android {
    namespace = "com.example.litterlegends"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.litterlegends"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true // ✅ Enable Jetpack Compose
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    repositories {
        google()  // ✅ Required for Android dependencies
        mavenCentral()  // ✅ Required for most libraries
    }

}

dependencies {
    // ✅ Jetpack Compose BOM
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))

    // ✅ Jetpack Compose UI
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.foundation:foundation")

    // ✅ Activity with Compose
    implementation("androidx.activity:activity-compose:1.10.0")

    // ✅ Navigation in Jetpack Compose
    implementation("androidx.navigation:navigation-compose:2.8.6")

    // ✅ Lifecycle & ViewModel for Compose
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-view-model-compose:2.8.7")

    // ✅ Coil for Image Loading in Compose
    implementation("io.coil-kt:coil-compose:2.2.2")

    // ✅ MongoDB Realm Database
    implementation("io.realm.kotlin:library-sync:1.11.0")
    implementation("androidx.compose.ui:ui-tooling-preview-android:1.7.7")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")

    // ✅ Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    // ✅ Jetpack Compose UI Testing (Optional)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")

    implementation("at.fare.lib:bcrypt:0.9.0") // Secure password hashing

    // Jetpack Navigation Component for Fragment-based Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.6")

    implementation("androidx.navigation:navigation-compose:2.8.6")  // ✅ Navigation for Jetpack Compose


    // Jetpack Navigation Component for Compose
    implementation("androidx.navigation:navigation-compose:2.8.6")
}
