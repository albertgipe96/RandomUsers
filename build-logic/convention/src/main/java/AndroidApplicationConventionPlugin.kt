import com.android.build.api.dsl.ApplicationExtension
import com.development.buildlogic.convention.ExtensionType
import com.development.buildlogic.convention.configureAndroidCompose
import com.development.buildlogic.convention.configureBuildTypes
import com.development.buildlogic.convention.configureKoin
import com.development.buildlogic.convention.configureKotlinAndroid
import com.development.buildlogic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()
                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()
                }
                configureKotlinAndroid(this)
                configureBuildTypes(this, ExtensionType.APPLICATION)
                configureAndroidCompose(this)
                configureKoin(this)
            }
        }
    }
}