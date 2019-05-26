import com.github.fluidsonic.fluid.library.*
import org.jetbrains.kotlin.gradle.plugin.mpp.*

plugins {
	id("com.github.fluidsonic.fluid-library") version "0.9.13"
}

fluidLibrary {
	name = "fluid-time"
	version = "0.9.1"
}

fluidLibraryVariant {
	description = "multiplatform date & time library"
	jdk = JDK.v1_7
}

kotlin {
	iosX64()
	macosX64()

	sourceSets {
		getByName("iosX64Main") {
			kotlin.setSrcDirs(listOf("sources/ios"))
			resources.setSrcDirs(emptyList<Any>())

			dependencies {
				implementation(kotlinx("serialization-runtime-native", "0.11.0"))
			}
		}

		getByName("macosX64Main") {
			kotlin.setSrcDirs(listOf("sources/ios"))
			resources.setSrcDirs(emptyList<Any>())

			dependencies {
				implementation(kotlinx("serialization-runtime-native", "0.11.0"))
			}
		}

		commonMain {
			dependencies {
				implementation(kotlinx("serialization-runtime", "0.11.0"))
			}
		}

		jvmMain {
			dependencies {
				implementation("org.threeten:threetenbp:1.4.0")
			}
		}
	}
}


val macosTest by tasks.creating<Task> {
	dependsOn("linkTestDebugExecutableMacosX64")
	group = JavaBasePlugin.VERIFICATION_GROUP

	doLast {
		val binary = kotlin.targets.getByName<KotlinNativeTarget>("macosX64").binaries.getExecutable("test", "DEBUG").outputFile
		exec {
			println("$ \"${binary.absolutePath}\"")
			commandLine(binary.absolutePath)
		}
	}
}


val iosTest by tasks.creating<Task> {
	val device = findProperty("iosDevice")?.toString() ?: "iPhone 8"
	dependsOn("linkTestDebugExecutableIosX64")
	group = JavaBasePlugin.VERIFICATION_GROUP

	doLast {
		val binary = kotlin.targets.getByName<KotlinNativeTarget>("iosX64").binaries.getExecutable("test", "DEBUG").outputFile
		exec {
			println("$ xcrun simctl spawn \"$device\" \"${binary.absolutePath}\"")
			commandLine("xcrun", "simctl", "spawn", device, binary.absolutePath)
		}
	}
}

tasks.named("check") {
	dependsOn("iosTest")
	dependsOn("macosTest")
}
