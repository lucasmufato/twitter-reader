package com.sytac.excercise.core.domain

import java.time.LocalDateTime

data class Author(
	val id: Long,
	val creationDate: LocalDateTime,
	val name: String,
	val screenName: String
)