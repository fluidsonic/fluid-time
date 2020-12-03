import io.fluidsonic.gradle.*

plugins {
	id("io.fluidsonic.gradle") version "1.1.13"
}

fluidLibrary(name = "time", version = "0.10.4")

fluidLibraryModule(description = "Kotlin multiplatform date & time library") {
	language {
		withExperimentalApi("kotlinx.serialization.ExperimentalSerializationApi")
	}

	targets {
		common {
			dependencies {
				implementation(fluid("locale", "0.9.4"))
				implementation(kotlinx("serialization-core", "1.0.1"))
			}

			testDependencies {
				implementation(kotlinx("serialization-json", "1.0.1"))
			}
		}

		darwin()

		jvm {
			dependencies {
				implementation("org.threeten:threetenbp:1.5.0")
			}
		}
		jvmJdk8()
	}
}
