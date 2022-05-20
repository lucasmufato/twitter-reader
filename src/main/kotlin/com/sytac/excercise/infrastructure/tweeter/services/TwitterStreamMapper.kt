package com.sytac.excercise.infrastructure.tweeter.services

import com.sytac.excercise.infrastructure.tweeter.model.Tweet
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class TwitterStreamMapper {

	private val json = Json { ignoreUnknownKeys = true }

	fun map(rawRepresentation: String): Tweet? {
		return try {
			json.decodeFromString(rawRepresentation)
		}catch (e: RuntimeException){
			null
		}

	}

}