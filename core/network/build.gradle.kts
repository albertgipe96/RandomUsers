plugins {
    alias(libs.plugins.randomusers.android.library)
    alias(libs.plugins.randomusers.retrofit)
}

android {
    namespace = "com.development.core.network"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}