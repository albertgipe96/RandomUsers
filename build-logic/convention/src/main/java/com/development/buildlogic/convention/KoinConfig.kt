package com.development.buildlogic.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureKoin(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.run {
        dependencies {
            "implementation"(libs.findLibrary("koin.android").get())
            "implementation"(libs.findLibrary("koin.androidx.navigation").get())
            "implementation"(libs.findLibrary("koin.androidx.compose").get())
            "testImplementation"(libs.findLibrary("koin.test.junit4").get())
        }
    }
}