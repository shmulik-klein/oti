plugins {
    kotlin("multiplatform")
}

kotlin {
    js {
        browser()
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(project(":shared"))
            }
        }
    }
}
