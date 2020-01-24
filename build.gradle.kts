import io.fluidsonic.gradle.*

plugins {
	id("io.fluidsonic.gradle") version "1.0.6"
}

fluidLibrary(name = "time", version = "0.9.16")

fluidLibraryVariant {
	description = "Multiplatform date & time library"

	common {
		dependencies {
			implementation(kotlinx("serialization-runtime-common", "0.14.0"))
		}
	}

	jvm(JvmTarget.jdk7) {
		dependencies {
			implementation(kotlinx("serialization-runtime", "0.14.0"))
			implementation("org.threeten:threetenbp:1.4.1")
		}
	}

	jvm(JvmTarget.jdk8) {
		dependencies {
			implementation(kotlinx("serialization-runtime", "0.14.0"))
		}
	}

	objc(ObjcTarget.iosArm64) {
		dependencies {
			implementation(kotlinx("serialization-runtime-iosarm64", "0.14.0"))
		}
	}

	objc(ObjcTarget.iosX64) {
		dependencies {
			implementation(kotlinx("serialization-runtime-iosx64", "0.14.0"))
		}
	}

	objc(ObjcTarget.macosX64) {
		dependencies {
			implementation(kotlinx("serialization-runtime-macosx64", "0.14.0"))
		}
	}
}
