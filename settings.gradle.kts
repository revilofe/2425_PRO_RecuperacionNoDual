plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "2425_PRO_RecuperacionNoDual"
include("src:main")
findProject(":src:main")?.name = "main"
