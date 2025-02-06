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

import kotlinx.serialization.Serializable

/**
 * Created by Samuele Pozzebon on 22/08/2024
 */

@Serializable
internal data class OfflineJson(
    val languageFallback: Map<String, String>,
    val baseLanguage: String,
    val translations: Map<String, Map<String, String>>
)
