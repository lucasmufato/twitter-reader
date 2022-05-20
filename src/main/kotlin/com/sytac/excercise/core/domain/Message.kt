package com.sytac.excercise.core.domain

import java.time.LocalDateTime

data class Message(
	val id: Long,
	val creationDate: LocalDateTime,
	val text: String,
	val author: Author
)

