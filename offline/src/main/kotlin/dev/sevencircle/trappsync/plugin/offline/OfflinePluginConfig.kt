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

package dev.sevencircle.trappsync.plugin.offline

import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property

/**
 * Created by Samuele Pozzebon on 22/08/2024
 */

internal val JSON_FILE_NAME = "offline.json"
internal val DEFAULT_HASH = "OFFLINE_HASH"

interface OfflinePluginConfig {
    val offlineJsonFile: RegularFileProperty
    val offlineHash: Property<String>
}
