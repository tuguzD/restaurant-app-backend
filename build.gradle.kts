plugins {
    id("org.springframework.boot") version "2.7.0" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    kotlin("jvm") version "1.6.21" apply false
    kotlin("plugin.jpa") version "1.6.21" apply false
    kotlin("plugin.spring") version "1.6.21" apply false
    kotlin("plugin.serialization") version "1.6.21" apply false
    kotlin("kapt") version "1.6.21" apply false
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
