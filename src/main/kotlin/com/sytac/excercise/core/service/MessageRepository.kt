package com.sytac.excercise.core.service

import com.sytac.excercise.core.domain.Message

interface MessageRepository {
	fun add(message: Message)
	fun getAllSorted(): Collection<Message>
}