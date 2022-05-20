package com.sytac.excercise.core.actions

import com.sytac.excercise.core.domain.Message
import com.sytac.excercise.core.service.MessageExporter
import com.sytac.excercise.core.service.MessageRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class ExportRecopilatedMessagesTest{

	@Test
	internal fun `calls message exporter`() {
		val messageRepository: MessageRepository = mockk()
		val messageCollection = mockk<Collection<Message>>("mockedCollection")
		coEvery { messageRepository.getAllSorted() }.returns(messageCollection)
		val messagesExporter: MessageExporter = mockk()
		coEvery { messagesExporter.export(any()) }.just(Runs)
		val exportAction = ExportRecopilatedMessages(messageRepository, messagesExporter)

		exportAction()

		coVerify { messagesExporter.export(messageCollection) }
	}
}