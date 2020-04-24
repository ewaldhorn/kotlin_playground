plugins {
    id("org.jetbrains.kotlin.js") version "1.3.72"
}

group = "za.co.nofuss.cammiweb"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-js"))
}

kotlin.target.browser { }