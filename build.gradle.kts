import io.fluidsonic.gradle.*

plugins {
	id("io.fluidsonic.gradle") version "1.1.8"
}

fluidLibrary(name = "time", version = "0.10.3")

fluidLibraryModule(description = "Kotlin multiplatform date & time library") {
	language {
		withExperimentalApi("kotlinx.serialization.ExperimentalSerializationApi")
	}

	targets {
		common {
			dependencies {
				implementation(fluid("locale", "0.9.2"))
				implementation(kotlinx("serialization-core", "1.0.0-RC"))
			}
		}

		darwin()

		jvm {
			dependencies {
				implementation("org.threeten:threetenbp:1.4.4")
			}
		}
		jvmJdk8()
	}
}
