import io.fluidsonic.gradle.*

plugins {
	id("io.fluidsonic.gradle") version "1.1.0"
}

fluidLibrary(name = "time", version = "0.9.20")

fluidLibraryModule(description = "Multiplatform date & time library") {
	targets {
		common {
			dependencies {
				implementation(kotlinx("serialization-runtime", "1.0-M1-1.4.0-rc"))
			}
		}

		jvm()
		jvmJdk7 {
			dependencies {
				implementation("org.threeten:threetenbp:1.4.4")
			}
		}

		nativeDarwin()
	}
}
