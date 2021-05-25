import io.fluidsonic.gradle.*
import org.jetbrains.kotlin.gradle.plugin.*

plugins {
	id("io.fluidsonic.gradle") version "1.1.23"
}

fluidLibrary(name = "time", version = "0.13.3")

fluidLibraryModule(description = "Kotlin multiplatform date & time library") {
	language {
		withExperimentalApi("kotlinx.serialization.ExperimentalSerializationApi")
	}

	targets {
		common {
			dependencies {
				implementation(kotlinx("serialization-core", "1.1.0")) // https://youtrack.jetbrains.com/issue/KT-46598

				api(kotlinx("datetime", "0.2.0"))
			}

			testDependencies {
				implementation(kotlinx("serialization-json", "1.1.0")) // https://youtrack.jetbrains.com/issue/KT-46598
			}
		}

		darwin {
			withoutWatchosX64()
		}

		js(KotlinJsCompilerType.BOTH) {
			testDependencies {
				implementation(npm("@js-joda/timezone", "2.5.0"))
			}
		}

		jvm()
	}
}
