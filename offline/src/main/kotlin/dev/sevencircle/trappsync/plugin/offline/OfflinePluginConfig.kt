/*
 *  ______   ______    ______   __     __
 * /\  ___\ /\  __ \  /\__  _\ /\ \  _ \ \
 * \ \  __\ \ \ \/\ \ \/_/\ \/ \ \ \/ ".\ \
 *  \ \_\    \ \_____\   \ \_\  \ \__/".~\_\
 *   \/_/     \/_____/    \/_/   \/_/   \/_/
 *
 * Copyright © 2025 Var Group S.p.A. All rights reserved.
 *  
 */

package dev.sevencircle.trappsync.plugin.offline

import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property

/**
 * Created by Samuele Pozzebon on 22/08/2024
 */

internal val JSON_FILE_NAME = "offline.json"

interface OfflinePluginConfig {
    val offlineJsonFile: RegularFileProperty
    val offlineHash: Property<String>
}
