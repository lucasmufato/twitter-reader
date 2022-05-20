package com.sytac.excercise.infrastructure.exporter

import com.sytac.excercise.core.domain.Author
import com.sytac.excercise.core.domain.Message
import com.sytac.excercise.core.service.MessageExporter
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.FileWriter
import java.time.ZoneOffset

class MessageExporterImpl(
	private val fullMessagesFileName: String,
	private val authorsFileName: String,
	private val messagesFileName: String
) : MessageExporter {

	override fun export(messages: Collection<Message>) {
		writeIn(fullMessagesFileName, messagesAndAuthorsInJson(messages))
		writeIn(authorsFileName, authorsInJson(messages))
		writeIn(messagesFileName, messagesWithoutAuthorsInJson(messages))
	}

	private fun messagesWithoutAuthorsInJson(messages: Collection<Message>) =
		Json.encodeToString(messages.map { MessageWithoutAuthorJson.from(it) })

	private fun authorsInJson(messages: Collection<Message>) =
		Json.encodeToString(messages.map { it.author }.toSet().map { AuthorJson.from(it) })

	private fun messagesAndAuthorsInJson(messages: Collection<Message>) =
		Json.encodeToString(messages.map { MessageJson.from(it) })

	private fun writeIn(fileName: String, content:String) {
		val fileWriter = FileWriter(fileName)
		fileWriter.write( content )
		fileWriter.flush()
		fileWriter.close()
	}

}

@Serializable
data class MessageWithoutAuthorJson(
	val id: Long,
	val creation_date_epoch: Long,
	val text: String,
	val author: Long
) {

	companion object {
		fun from(message: Message): MessageWithoutAuthorJson {
			with(message) {
				return MessageWithoutAuthorJson(id, creationDate.toEpochSecond(ZoneOffset.UTC), text, author.id)
			}
		}
	}

}

@Serializable
data class MessageJson(
	val id: Long,
	val creation_date_epoch: Long,
	val text: String,
	val author: AuthorJson
) {

	companion object {
		fun from(message: Message): MessageJson {
			with(message) {
				return MessageJson(id, creationDate.toEpochSecond(ZoneOffset.UTC), text, AuthorJson.from(author))
			}
		}
	}

}

@Serializable
data class AuthorJson(
	val id: Long,
	val creation_date_epoch: Long,
	val name: String,
	val screen_name: String
) {
	companion object {
		fun from(author: Author): AuthorJson {
			with(author) {
				return AuthorJson(id, creationDate.toEpochSecond(ZoneOffset.UTC), name, screenName)
			}
		}
	}
}