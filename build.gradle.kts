import com.github.fluidsonic.fluid.library.*

plugins {
	id("com.github.fluidsonic.fluid-library") version "0.9.11"
}

fluidLibrary {
	name = "fluid-time"
	version = "0.9.0"
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
			resources.setSrcDirs(emptyList())
		}

		jvmMain {
			dependencies {
				implementation("org.threeten:threetenbp:1.4.0")
			}
		}
	}
}
