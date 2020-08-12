import io.fluidsonic.gradle.*

plugins {
	id("io.fluidsonic.gradle") version "1.1.4"
}

fluidLibrary(name = "time", version = "0.10.1")

fluidLibraryModule(description = "Multiplatform date & time library") {
	targets {
		common {
			dependencies {
				implementation(fluid("locale", "0.9.0"))
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
