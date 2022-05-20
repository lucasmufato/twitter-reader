package com.sytac.excercise.core.service

import com.sytac.excercise.core.domain.Message

interface MessageExporter {
	fun export(messages: Collection<Message>)
}
