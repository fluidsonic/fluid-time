import com.github.fluidsonic.fluid.library.*

plugins {
	id("com.github.fluidsonic.fluid-library") version "0.9.24"
}

fluidLibrary {
	name = "fluid-time"
	version = "0.9.13"
}

fluidLibraryVariant {
	description = "multiplatform date & time library"

	common {
		dependencies {
			implementation(kotlinx("serialization-runtime-common", "0.13.0"))
		}
	}

	jvm(JvmTarget.jdk7) {
		dependencies {
			implementation(kotlinx("serialization-runtime", "0.13.0"))
			implementation("org.threeten:threetenbp:1.4.0")
		}
	}

	jvm(JvmTarget.jdk8) {
		dependencies {
			implementation(kotlinx("serialization-runtime", "0.13.0"))
		}
	}

	objc(ObjcTarget.iosArm64) {
		dependencies {
			implementation(kotlinx("serialization-runtime-iosarm64", "0.13.0"))
		}
	}

	objc(ObjcTarget.iosX64) {
		dependencies {
			implementation(kotlinx("serialization-runtime-iosx64", "0.13.0"))
		}
	}

	objc(ObjcTarget.macosX64) {
		dependencies {
			implementation(kotlinx("serialization-runtime-macosx64", "0.13.0"))
		}
	}
}
