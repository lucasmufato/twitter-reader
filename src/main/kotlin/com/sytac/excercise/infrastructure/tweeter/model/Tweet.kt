package com.sytac.excercise.infrastructure.tweeter.model

import com.sytac.excercise.core.domain.Message
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


@Serializable
data class Tweet(
	val id: Long,
	val created_at: String,
	val text: String,
	val user: User
){
	fun toMessage(): Message{
		val format = "EEE MMM dd HH:mm:ss Z yyyy"
		//val substring = text.substring(0, text.length.coerceAtMost(20))
		val substring = text
		return Message(id, LocalDateTime.parse(created_at, DateTimeFormatter.ofPattern(format, Locale.ENGLISH)), substring,
			user.toAuthor())
	}

}

