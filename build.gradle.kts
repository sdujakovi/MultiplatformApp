buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath ("org.jetbrains.kotlin:kotlin-serialization:1.7.10")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://dl.bintray.com/kotlin/exposed")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}