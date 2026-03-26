plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
    }
}

android {
    namespace = "com.oti"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
}