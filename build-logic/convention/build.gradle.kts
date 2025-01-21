plugins {
    `kotlin-dsl`
}

group = "com.development.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "randomusers.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "randomusers.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "randomusers.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("room") {
            id = "randomusers.room"
            implementationClass = "RoomLibraryConventionPlugin"
        }
    }
}