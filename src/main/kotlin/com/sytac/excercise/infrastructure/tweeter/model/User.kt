package com.sytac.excercise.infrastructure.tweeter.model

import com.sytac.excercise.core.domain.Author
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Serializable
data class User(
	val id: Long,
	val created_at: String,
	val name: String,
	val screen_name: String
) {
	fun toAuthor(): Author {
		val format = "EEE MMM dd HH:mm:ss Z yyyy"
		return Author(id, LocalDateTime.parse(created_at, DateTimeFormatter.ofPattern(format, Locale.ENGLISH)), name, screen_name)
	}
}