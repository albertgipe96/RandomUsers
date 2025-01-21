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
}

private fun BuildType.configureReleaseBuildType(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    // Release configurations
    isMinifyEnabled = true
    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}