plugins {
    id("org.jetbrains.kotlin.js") version "1.3.72"
}

group = "za.co.nofuss"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://kotlin.bintray.com/kotlin-js-wrappers/")
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-js"))

    //React, React DOM and Wrappers
    implementation("org.jetbrains:kotlin-react:16.13.0-pre.94-kotlin-1.3.70")
    implementation("org.jetbrains:kotlin-react-dom:16.13.0-pre.94-kotlin-1.3.70")
    implementation(npm("react", "16.13.1"))
    implementation(npm("react-dom", "16.13.1"))

    //Kotlin Styled
    implementation("org.jetbrains:kotlin-styled:1.0.0-pre.94-kotlin-1.3.70")
    implementation(npm("styled-components"))
    implementation(npm("inline-style-prefixer"))

    //Video Player
    implementation(npm("react-player"))

    //Share Buttons
    implementation(npm("react-share"))

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.5")
}

kotlin.target.browser { }