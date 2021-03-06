import org.gradle.api.tasks.bundling.Jar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.run.BootRunTask

version = "1.0-SNAPSHOT"

plugins {
    idea
    application
    kotlin("jvm")
    id("org.springframework.boot") version ("1.5.7.RELEASE")
}

task<Wrapper>("wrapper") {
    gradleVersion = "4.2"
    distributionType = Wrapper.DistributionType.ALL
}

tasks.withType<Jar> {
    baseName = "note"
    version = ""
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

//应用于 bootRun
tasks.withType<BootRunTask> {
    addResources = true
    jvmArgs = listOf("-Dconfig.enable_upload=true")
}

//应用于 run
application {
    mainClassName = "com.xhstormr.note.Application"
    applicationDefaultJvmArgs = listOf(
            "-Dspring.profiles.active=dev",
            "-Dconfig.enable_upload=true")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

buildscript {
    repositories {
        maven("http://maven.aliyun.com/nexus/content/groups/public/")
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlin_version"]}")
    }
}

repositories {
    maven("http://maven.aliyun.com/nexus/content/groups/public/")
}

dependencies {
    compile("com.zaxxer:HikariCP")
    compile("org.postgresql:postgresql")

    compile("org.jetbrains.kotlin:kotlin-stdlib-jre8")
    compile("org.springframework.boot:spring-boot-starter-data-jpa")
    compile("org.springframework.boot:spring-boot-starter-thymeleaf")
    compile("org.springframework.boot:spring-boot-starter-actuator")
    compile("org.springframework.boot:spring-boot-devtools")
}
