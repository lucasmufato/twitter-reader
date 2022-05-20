package com.sytac.excercise.infrastructure.exporter

import com.sytac.excercise.factories.TestingFactory.BEAVER_MESSAGE
import com.sytac.excercise.factories.TestingFactory.BIG_BEAVER_MESSAGE
import org.junit.jupiter.api.Test
import java.io.FileReader
import kotlin.test.assertEquals

internal class MessageExporterImplTest {

	private lateinit var fileReader: FileReader
	private val exporter = MessageExporterImpl("all.json", "authors.json", "messages.json")

	@Test
	internal fun `checks messagesWithAuthors`() {
		val messages = listOf(BEAVER_MESSAGE, BIG_BEAVER_MESSAGE)

		exporter.export(messages)

		fileReader = FileReader("all.json")
		assertEquals(EXPECTED_all_json_FILE_CONTENT, fileReader.readText())
		fileReader.close()
	}

	@Test
	internal fun `checks authors`() {
		val messages = listOf(BEAVER_MESSAGE, BIG_BEAVER_MESSAGE)

		exporter.export(messages)

		fileReader = FileReader("authors.json")
		assertEquals(EXPECTED_authors_json_FILE_CONTENT, fileReader.readText())
		fileReader.close()
	}

	@Test
	internal fun `checks messages`() {
		val messages = listOf(BEAVER_MESSAGE, BIG_BEAVER_MESSAGE)

		exporter.export(messages)

		fileReader = FileReader("messages.json")
		assertEquals(EXPECTED_messages_json_FILE_CONTENT, fileReader.readText())
		fileReader.close()
	}

	private companion object {

		val EXPECTED_all_json_FILE_CONTENT =
			"""[{"id":123,"creation_date_epoch":1577840400,"text":"some beaver text","author":{"id":234,"creation_date_epoch":1577840400,"name":"beaver guy","screen_name":"BeaverGuy"}},{"id":1234,"creation_date_epoch":1577840400,"text":"BIG beaver text","author":{"id":2345,"creation_date_epoch":1577840400,"name":"BIG beaver","screen_name":"BigBeaver"}}]""".trimMargin()
		val EXPECTED_messages_json_FILE_CONTENT =
			"""[{"id":123,"creation_date_epoch":1577840400,"text":"some beaver text","author":234},{"id":1234,"creation_date_epoch":1577840400,"text":"BIG beaver text","author":2345}]"""
		val EXPECTED_authors_json_FILE_CONTENT =
			"""[{"id":234,"creation_date_epoch":1577840400,"name":"beaver guy","screen_name":"BeaverGuy"},{"id":2345,"creation_date_epoch":1577840400,"name":"BIG beaver","screen_name":"BigBeaver"}]"""
	}
}