/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * Learn more about Gradle by exploring our samples at https://docs.gradle.org/7.2/samples
 */
plugins {
	id ("java")
	id ("eclipse")
	id ("idea")
}

repositories {
	mavenCentral()
}

dependencies {
	testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
	testImplementation("org.assertj:assertj-core:3.22.0")

}