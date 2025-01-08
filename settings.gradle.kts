/*
 *  ______   ______    ______   __     __
 * /\  ___\ /\  __ \  /\__  _\ /\ \  _ \ \
 * \ \  __\ \ \ \/\ \ \/_/\ \/ \ \ \/ ".\ \
 *  \ \_\    \ \_____\   \ \_\  \ \__/".~\_\
 *   \/_/     \/_____/    \/_/   \/_/   \/_/
 *
 * Copyright © 2025 Zero12 S.r.l. All rights reserved.
 *  
 */

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("pluginLibs") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "trappsync_plugin"

include(":offline")
