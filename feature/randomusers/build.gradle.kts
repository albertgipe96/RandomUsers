plugins {
    alias(libs.plugins.randomusers.android.library.compose)
    alias(libs.plugins.randomusers.retrofit)
}

android {
    namespace = "com.development.feature.randomusers"
}

dependencies {
    implementation(projects.core.storage)
    implementation(projects.core.network)

    // Paging
    implementation(libs.androidx.paging)
    implementation(libs.androidx.paging.compose)
    implementation(libs.paging.common)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}