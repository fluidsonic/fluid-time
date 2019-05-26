pluginManagement {
	repositories {
		bintray("kotlin/kotlin-eap")
		gradlePluginPortal()
		jcenter()
		bintray("fluidsonic/maven")
	}
}

rootProject.name = "fluid-time"

enableFeaturePreview("GRADLE_METADATA")


fun RepositoryHandler.bintray(name: String) =
	maven("https://dl.bintray.com/$name")
