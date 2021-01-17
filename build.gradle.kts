import io.fluidsonic.gradle.*

plugins {
	id("io.fluidsonic.gradle") version "1.1.17"
}

fluidLibrary(name = "time", version = "0.12.0")

fluidLibraryModule(description = "Kotlin multiplatform date & time library") {
	language {
		withExperimentalApi("kotlinx.serialization.ExperimentalSerializationApi")
	}

	targets {
		common {
			dependencies {
				implementation(kotlinx("serialization-core", "1.0.1"))

				api(kotlinx("datetime", "0.1.1"))
			}

			testDependencies {
				implementation(kotlinx("serialization-json", "1.0.1"))
			}
		}

		darwin()

		js {
			testDependencies {
				implementation(npm("@js-joda/timezone", "2.5.0"))
			}
		}

		jvm()
	}
}
