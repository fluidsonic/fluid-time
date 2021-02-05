import io.fluidsonic.gradle.*
import org.jetbrains.kotlin.gradle.plugin.*

plugins {
	id("io.fluidsonic.gradle") version "1.1.18"
}

fluidLibrary(name = "time", version = "0.13.1")

fluidLibraryModule(description = "Kotlin multiplatform date & time library") {
	language {
		withExperimentalApi("kotlinx.serialization.ExperimentalSerializationApi")
	}

	targets {
		common {
			dependencies {
				implementation(kotlinx("serialization-core", "1.1.0-RC"))

				api(kotlinx("datetime", "0.1.1"))
			}

			testDependencies {
				implementation(kotlinx("serialization-json", "1.1.0-RC"))
			}
		}

		darwin {
			withoutWatchosX64()
		}

		js(KotlinJsCompilerType.BOTH) {
			testDependencies {
				implementation(npm("@js-joda/timezone", "2.5.0"))
			}
		}

		jvm()
	}
}
