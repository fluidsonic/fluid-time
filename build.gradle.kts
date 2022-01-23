import io.fluidsonic.gradle.*
import org.jetbrains.kotlin.gradle.plugin.*

plugins {
	id("io.fluidsonic.gradle") version "1.1.25"
}

fluidLibrary(name = "time", version = "0.15.0")

fluidLibraryModule(description = "Kotlin multiplatform date & time library") {
	language {
		withExperimentalApi("kotlinx.serialization.ExperimentalSerializationApi")
	}

	targets {
		common {
			dependencies {
				implementation(kotlinx("serialization-core", "1.3.2"))

				api(kotlinx("datetime", "0.3.2"))
			}

			testDependencies {
				implementation(kotlinx("serialization-json", "1.3.2"))
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
