import com.github.fluidsonic.fluid.library.*

plugins {
	id("com.github.fluidsonic.fluid-library") version "0.9.20"
}

fluidLibrary {
	name = "fluid-time"
	version = "0.9.11"
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
				implementation(kotlinx("serialization-runtime-common", "0.11.1"))
			}
		}

		iosArm64Main {
			dependencies {
				implementation(kotlinx("serialization-runtime-iosarm64", "0.11.1"))
			}
		}

		iosX64Main {
			dependencies {
				implementation(kotlinx("serialization-runtime-iosx64", "0.11.1"))
			}
		}

		jvmMain {
			dependencies {
				implementation(kotlinx("serialization-runtime", "0.11.1"))
				implementation("org.threeten:threetenbp:1.4.0")
			}
		}

		macosX64Main {
			dependencies {
				implementation(kotlinx("serialization-runtime-macosx64", "0.11.1"))
			}
		}
	}
}
