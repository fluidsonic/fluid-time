import io.fluidsonic.gradle.*
import org.jetbrains.kotlin.gradle.plugin.*

plugins {
	id("io.fluidsonic.gradle") version "1.2.1"
}

fluidLibrary(name = "time", version = "0.16.0")

fluidLibraryModule(description = "Kotlin multiplatform date & time library") {
	language {
		withExperimentalApi("kotlinx.serialization.ExperimentalSerializationApi")
	}

	targets {
		common {
			dependencies {
				implementation(kotlinx("serialization-core", "1.4.0"))

				api(kotlinx("datetime", "0.4.0"))
			}

			testDependencies {
				implementation(kotlinx("serialization-json", "1.4.0"))
			}
		}

		darwin()
		js(KotlinJsCompilerType.BOTH) {
			testDependencies {
				implementation(npm("@js-joda/timezone", "2.12.0"))
			}
		}
		jvm()
	}
}
