package com.development.buildlogic.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal enum class ExtensionType {
    APPLICATION,
    LIBRARY
}

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    extensionType: ExtensionType
) {
    when (extensionType) {
        ExtensionType.APPLICATION -> {
            commonExtension.run {
                buildFeatures {
                    buildConfig = true
                }
                extensions.configure<ApplicationExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType()
                        }
                        release {
                            configureReleaseBuildType(commonExtension)
                        }
                    }
                }
            }
        }
        ExtensionType.LIBRARY -> {
            commonExtension.run {
                buildFeatures {
                    buildConfig = true
                }
                extensions.configure<LibraryExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType()
                        }
                        release {
                            configureReleaseBuildType(commonExtension)
                        }
                    }
                }
            }
        }
    }
}

private fun BuildType.configureDebugBuildType() {
    // Debug configurations
    buildConfigField("String", "BASE_URL", "\"https://randomuser.me\"") // Staging base url
}

private fun BuildType.configureReleaseBuildType(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    // Release configurations
    buildConfigField("String", "BASE_URL", "\"https://randomuser.me\"") // Prod base url

    isMinifyEnabled = true
    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}