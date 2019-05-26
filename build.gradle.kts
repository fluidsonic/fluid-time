import com.github.fluidsonic.fluid.library.*

plugins {
	id("com.github.fluidsonic.fluid-library") version "0.9.13"
}

fluidLibrary {
	name = "fluid-time"
	version = "0.9.1"
}

fluidLibraryVariant {
	description = "multiplatform date & time library"
	jdk = JDK.v1_7
}

kotlin {
	iosX64()

	sourceSets {
		getByName("iosX64Main") {
			kotlin.setSrcDirs(listOf("sources/ios"))
			resources.setSrcDirs(emptyList<Any>())

			dependencies {
				implementation(kotlinx("serialization-runtime-native", "0.11.0"))
			}
		}

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
	}
}
