pluginManagement {
    repositories {
        google()  // ✅ Correct place for repositories
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()  // ✅ Correct place for repositories
        mavenCentral()
    }
}

rootProject.name = "LitterLegends"
include(":app")
