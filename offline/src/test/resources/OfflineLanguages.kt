package dev.sevencircle.trappsync.plugin.offline

import dev.sevencircle.trappsync.core.domain.LanguageModel
import dev.sevencircle.trappsync.core.domain.OfflineLanguages


val parsedLanguages: OfflineLanguages = object : OfflineLanguages {
	override val languageMap = setOf(
		LanguageModel(
			languageCode = "en-US",
			translationMap = mapOf(
				"home.greetings" to "Hi {{1}}!",
				"home.welcome" to "Welcome to TrAPPSync",
				"home.welcomeSubtitle" to "This is a demo of the library developed by zero12 srl",
				"list.title" to "Vocabulary list ",
				"item1.title" to "Item 1 - Title",
				"item1.body" to "Item 1 - Body",
				"item2.title" to "Item 2 - Title",
				"item2.body" to "Item 2 - Body",
				"item3.title" to "Item 3 - Title",
				"item3.body" to "Item 3 - Body",
				"item4.title" to "Item 4 - Title",
				"item4.body" to "Item 4 - Body",
				"home.namepage" to " Home",
				"list.namepage" to "Vocabulary list ",
				"list.items" to "",
				"item5.title" to "",
			),
		hash = "1737707679811"
		),
		LanguageModel(
			languageCode = "en-GB",
			translationMap = mapOf(
				"home.greetings" to "Hi {{0}}!",
				"home.welcome" to "Welcome to TrAPPSync, clearly in British and totally not in American",
				"home.welcomeSubtitle" to "This is a British demo of the library developed by zero12 srl",
				"list.title" to "British vocabulary list",
				"item1.title" to "Item 1 - Title, in British",
				"item1.body" to "Item 1 - Body, in British",
				"item2.title" to "Item 2 - Title, in British",
				"item2.body" to "Item 2 - Body, in British",
				"item3.title" to "Item 3 - Title, in British",
				"item3.body" to "Item 3 - Body, in British",
				"item4.title" to "Item 4 - Title, in British",
				"item4.body" to "Item 4 - Body, in British",
				"home.namepage" to " Home",
				"list.namepage" to "Vocabulary list ",
				"list.items" to "",
				"item5.title" to "",
			),
		hash = "1737707679811"
		),
		LanguageModel(
			languageCode = "fr-FR",
			translationMap = mapOf(
				"home.greetings" to "Bonjour {{0}}",
				"home.welcome" to "Welcome to TrAPPSync",
				"home.welcomeSubtitle" to "This is a demo of the library developed by zero12 srl",
				"list.title" to "Vocabulary list ",
				"item1.title" to "Item 1 - Title",
				"item1.body" to "Item 1 - Body",
				"item2.title" to "Item 2 - Title",
				"item2.body" to "Item 2 - Body",
				"item3.title" to "Item 3 - Title",
				"item3.body" to "Item 3 - Body",
				"item4.title" to "Item 4 - Title",
				"item4.body" to "Item 4 - Body",
				"home.namepage" to " Home",
				"list.namepage" to "Vocabulary list ",
				"list.items" to "",
				"item5.title" to "",
			),
		hash = "1737707679811"
		),
		LanguageModel(
			languageCode = "it-IT",
			translationMap = mapOf(
				"home.greetings" to "Ciao {{1}}!",
				"home.welcome" to "Benvenuto in TrAPPSync",
				"home.welcomeSubtitle" to "Questa è una demo dell'app sviluppata da zero12 srl",
				"list.title" to "Lista dei vocaboli",
				"item1.title" to "Oggetto 1 - Titolo",
				"item1.body" to "Oggetto 1 - Corpo",
				"item2.title" to "Oggetto 2 - Titolo",
				"item2.body" to "Oggetto 2 - Corpo",
				"item3.title" to "Oggetto 3 - Titolo",
				"item3.body" to "Oggetto 3 - Corpo",
				"item4.title" to "Oggetto 4 - Titolo",
				"item4.body" to "Item 4 - Corpo",
				"home.namepage" to " Home",
				"list.namepage" to "Vocabulary list ",
				"list.items" to "",
				"item5.title" to "",
			),
		hash = "1737707679811"
		),
	)

	override val baseLanguage = "en-US"

	override val localeMap = mapOf(
		"en" to "en-US", 
		"en-AU" to "en-US", 
		"en-CA" to "en-US", 
		"en-GB" to "en-GB", 
		"en-IE" to "en-US", 
		"en-IN" to "en-US", 
		"en-MT" to "en-US", 
		"en-NZ" to "en-US", 
		"en-PH" to "en-US", 
		"en-SG" to "en-US", 
		"en-US" to "en-US", 
		"en-ZA" to "en-US", 
		"fr" to "fr-FR", 
		"fr-BE" to "fr-FR", 
		"fr-CA" to "fr-FR", 
		"fr-CH" to "fr-FR", 
		"fr-FR" to "fr-FR", 
		"fr-LU" to "fr-FR", 
		"it" to "it-IT", 
		"it-CH" to "it-IT", 
		"it-IT" to "it-IT", 
	)
}