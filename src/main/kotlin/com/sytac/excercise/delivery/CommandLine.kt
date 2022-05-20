package com.sytac.excercise.delivery


import com.sytac.excercise.core.actions.AuthorizeTemporaryToken
import com.sytac.excercise.core.actions.ExportRecopilatedMessages
import com.sytac.excercise.core.actions.RecopilateMessages
import com.sytac.excercise.core.actions.RequestTemporaryToken
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import java.util.Scanner

class CommandLine(
	private val requestTemporaryToken: RequestTemporaryToken,
	private val authorizeTemporaryToken: AuthorizeTemporaryToken,
	private val recopilateMessages: RecopilateMessages,
	private val exportRecopilatedMessages: ExportRecopilatedMessages
) {

	fun start() = runBlocking {
		println("")
		println("Welcome Syntac team")
		println("")
		println("")

		val url = requestTemporaryToken()
		println()
		println("Please enter in the following link to authenticate with Twitter")
		println(url)
		println()
		authorizeTemporaryToken(
			Scanner(System.`in`).nextLine()
		)
		println("Authorization succesfull")
		println("Recopilating messages")

		recopilateMessages()

		println("Exporting messages")

		exportRecopilatedMessages()

		println("All Done!")

	}


}