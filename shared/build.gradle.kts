plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    ios {
        binaries.framework("OtiFramework") {
            baseName = "OtiFramework"
            export("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val iosMain by getting
    }
}

android {
    namespace = "com.oti"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}