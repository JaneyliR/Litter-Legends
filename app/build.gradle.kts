plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("io.realm.kotlin") version "1.11.0"  // ✅ Realm Database Plugin
}

android {
    namespace = "com.example.litterlegends"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.litterlegends"
        minSdk = 26
        targetSdk = 35
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
        compose = true  // ✅ Enable Jetpack Compose
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {
    // ✅ Jetpack Compose BOM (Recommended for automatic versioning)
    implementation(platform("androidx.compose:compose-bom:2023.06.01"))

    // ✅ Jetpack Compose Core Dependencies
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.activity:activity-compose:1.10.0")

    // ✅ Jetpack Navigation for Compose (Latest Stable)
    implementation("androidx.navigation:navigation-compose:2.8.6")

    // ✅ Lifecycle ViewModel for Compose (Use 2.7.0 instead of 2.8.7)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")

    // ✅ Coil for Image Loading in Compose
    implementation("io.coil-kt:coil-compose:2.2.2")

    // ✅ MongoDB Realm Database (Latest version)
    implementation("io.realm.kotlin:library-sync:1.11.0")

    // ✅ AppCompat & Material (Stable Versions)
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")

    // ✅ Constraint Layout (If Needed)
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")

    // ✅ UI Debugging Tools
    debugImplementation("androidx.compose.ui:ui-tooling")

    // ✅ UI Testing (Use Correct Version)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.7.7")

    // ✅ Secure Password Hashing (Fixed Package Name)
    implementation("at.favre.lib:bcrypt:0.9.0")

    // ✅ Testing Dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}
