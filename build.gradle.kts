import io.fluidsonic.gradle.*

plugins {
	id("io.fluidsonic.gradle") version "3.0.0"
}

fluidLibrary(name = "time", version = "0.19.0")

fluidLibraryModule(description = "Kotlin multiplatform date & time library") {
	targets {
		common {
			dependencies {
				implementation(kotlinx("serialization-core", "1.10.0"))

				api(kotlinx("datetime", "0.7.1"))
			}

			testDependencies {
				implementation(kotlinx("serialization-json", "1.10.0"))
			}
		}

		@Suppress("DEPRECATION")
		js {
			testDependencies {
				implementation(npm("@js-joda/timezone", "2.24.0"))
			}
		}
		jvm()
	}
}
