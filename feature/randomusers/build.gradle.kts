plugins {
    alias(libs.plugins.randomusers.android.library.compose)
}

android {
    namespace = "com.development.feature.randomusers"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}