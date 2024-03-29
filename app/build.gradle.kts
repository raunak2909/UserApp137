plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.firebaseproject137"
    compileSdk = 34

    buildFeatures{
        viewBinding = true
    }


    defaultConfig {
        applicationId = "com.example.firebaseproject137"
        minSdk = 24
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-firestore:24.10.0")
    implementation("com.google.firebase:firebase-auth:22.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //for cicrcularImageView
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // picasso for network image
    implementation("com.squareup.picasso:picasso:2.71828")

    //slider
    //implementation("com.github.smarteist:autoimageslider:1.4.0")
    implementation("com.github.denzcoskun:ImageSlideshow:0.1.2")

    //viewmodel corountines
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
}