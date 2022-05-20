package com.sytac.excercise.core.actions

import com.sytac.excercise.core.service.MessageExporter
import com.sytac.excercise.core.service.MessageRepository

class ExportRecopilatedMessages(
	private val messageRepository: MessageRepository,
	private val messagesExporter: MessageExporter
) {
	operator fun invoke() {
		messagesExporter.export(messageRepository.getAllSorted())
	}
}