package com.sytac.excercise.config

import com.sytac.excercise.core.actions.AuthorizeTemporaryToken
import com.sytac.excercise.core.actions.ExportRecopilatedMessages
import com.sytac.excercise.core.actions.RecopilateMessages
import com.sytac.excercise.core.actions.RequestTemporaryToken
import com.sytac.excercise.core.service.ClockImpl
import com.sytac.excercise.core.service.MessageRepositoryImpl
import com.sytac.excercise.core.service.RecopilationService
import com.sytac.excercise.core.service.StatisticsImpl
import com.sytac.excercise.delivery.CommandLine
import com.sytac.excercise.infrastructure.ConfigurationProvider
import com.sytac.excercise.infrastructure.exporter.MessageExporterImpl
import com.sytac.excercise.infrastructure.tweeter.services.StreamProviderImpl
import com.sytac.excercise.infrastructure.tweeter.services.TwitterAuthenticator
import com.sytac.excercise.infrastructure.tweeter.services.TwitterStreamMapper

class App {

	val config = ConfigurationProvider.config
	val authenticator = TwitterAuthenticator(config.consumerKey, config.consumerSecret)
	val requestTemporaryToken = RequestTemporaryToken(authenticator)
	val authorizeTemporaryToken = AuthorizeTemporaryToken(authenticator)
	val messageRepository = MessageRepositoryImpl()
	val mapper = TwitterStreamMapper()
	val clock = ClockImpl()

	val statistics = StatisticsImpl(clock, "output/statistics.txt")
	val streamProvider = StreamProviderImpl(authenticator, config.trackMessagesContaining)
	val messagesExporter = MessageExporterImpl("output/all.json", "output/authors.json", "output/messagesOnly.json")
	val recopilationService = RecopilationService(streamProvider, config.amountOfTimeToTrack, statistics, mapper, messageRepository, config.amountOfMessagesToTrack)
	val recopilateMessages = RecopilateMessages(recopilationService, statistics)
	val exportRecopilatedMessages = ExportRecopilatedMessages(messageRepository, messagesExporter)

	val commandLine = CommandLine(requestTemporaryToken, authorizeTemporaryToken, recopilateMessages, exportRecopilatedMessages)

	fun start() {
		commandLine.start()
	}
}