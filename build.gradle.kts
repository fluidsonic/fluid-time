import io.fluidsonic.gradle.*

plugins {
	id("io.fluidsonic.gradle") version "1.0.13"
}

fluidLibrary(name = "time", version = "0.9.19")

fluidLibraryVariant {
	description = "Multiplatform date & time library"

	common {
		dependencies {
			implementation(kotlinx("serialization-runtime-common", "0.20.0"))
		}
	}

	jvm(JvmTarget.jdk7) {
		dependencies {
			implementation(kotlinx("serialization-runtime", "0.20.0"))
			implementation("org.threeten:threetenbp:1.4.4")
		}
	}

	jvm(JvmTarget.jdk8) {
		dependencies {
			implementation(kotlinx("serialization-runtime", "0.20.0"))
		}
	}

	objc(ObjcTarget.iosArm64) {
		dependencies {
			implementation(kotlinx("serialization-runtime-iosarm64", "0.20.0"))
		}
	}

	objc(ObjcTarget.iosX64) {
		dependencies {
			implementation(kotlinx("serialization-runtime-iosx64", "0.20.0"))
		}
	}

	objc(ObjcTarget.macosX64) {
		dependencies {
			implementation(kotlinx("serialization-runtime-macosx64", "0.20.0"))
		}
	}
}
