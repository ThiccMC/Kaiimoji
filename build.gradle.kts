plugins {
    `java-library`
    id("com.github.johnrengelman.shadow") version "8.0.0"
    id("xyz.jpenilla.run-paper") version "2.1.0"
}


group = "dev.kugge"
version = "0.0.1"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("dev.folia:folia-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("com.github.LeonMangler:SuperVanish:6.2.17")
}

tasks.compileJava {
    options.encoding = Charsets.UTF_8.name()
    options.release.set(17)
}

tasks.processResources {
    filter { line -> line.replace("\${version}", project.version.toString()) }
}

tasks.shadowJar {
    archiveFileName.set("Kaiimoji-${project.version}.jar")
}

tasks.jar {
    enabled = false
}

tasks.assemble {
    dependsOn(tasks.shadowJar)
}
