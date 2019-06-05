import com.github.fluidsonic.fluid.library.*

plugins {
	id("com.github.fluidsonic.fluid-library") version "0.9.16"
}

fluidLibrary {
	name = "fluid-time"
	version = "0.9.8"
}

fluidLibraryVariant {
	description = "multiplatform date & time library"

	jvm(JDK.v1_7)
	objc()
}


kotlin {
	sourceSets {
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

		objcMain {
			dependencies {
				implementation(kotlinx("serialization-runtime-native", "0.11.0"))
			}
		}
	}
}
