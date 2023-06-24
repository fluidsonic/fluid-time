import io.fluidsonic.gradle.*

plugins {
	id("io.fluidsonic.gradle") version "1.3.1"
}

fluidLibrary(name = "time", version = "0.18.0")

fluidLibraryModule(description = "Kotlin multiplatform date & time library") {
	language {
		withExperimentalApi("kotlinx.serialization.ExperimentalSerializationApi")
	}

	targets {
		common {
			dependencies {
				implementation(kotlinx("serialization-core", "1.5.1"))

				api(kotlinx("datetime", "0.4.0"))
			}

			testDependencies {
				implementation(kotlinx("serialization-json", "1.5.1"))
			}
		}

		darwin()
		js {
			testDependencies {
				implementation(npm("@js-joda/timezone", "2.18.0"))
			}
		}
		jvm()
	}
}
