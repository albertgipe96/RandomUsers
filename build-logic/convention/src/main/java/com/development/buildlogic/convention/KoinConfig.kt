package com.development.buildlogic.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureKoin(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.run {
        dependencies {
            "implementation"(libs.findLibrary("koin.core").get())
            "implementation"(libs.findLibrary("koin.androidx.workmanager").get())
            "implementation"(libs.findLibrary("koin.android").get())
            "implementation"(libs.findLibrary("koin.androidx.compose").get())
        }
    }
}