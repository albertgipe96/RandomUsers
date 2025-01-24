import com.android.build.api.dsl.LibraryExtension
import com.development.buildlogic.convention.ExtensionType
import com.development.buildlogic.convention.configureBuildTypes
import com.development.buildlogic.convention.configureKoin
import com.development.buildlogic.convention.configureKotlinAndroid
import com.development.buildlogic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureBuildTypes(this, ExtensionType.LIBRARY)
                configureKoin(this)
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                }
            }
            dependencies {
                "implementation"(libs.findLibrary("kotlinx.serialization.json").get())
                "testImplementation"(libs.findLibrary("mockk.testing").get())
                "testImplementation"(libs.findLibrary("kotlinx.coroutines.test").get())
            }
        }
    }
}