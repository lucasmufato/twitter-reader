package com.sytac.excercise.core.service

import com.sytac.excercise.core.domain.Message

class MessageRepositoryImpl : MessageRepository {

	private val list = mutableListOf<Message>()

	override fun add(message: Message) {
		list.add(message)
	}

	override fun getAllSorted(): Collection<Message> {
		list.sortWith(  compareBy({ it.author.creationDate }, {it.creationDate} ))
		return list
	}

}