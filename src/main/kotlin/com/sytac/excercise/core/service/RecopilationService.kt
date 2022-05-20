package com.sytac.excercise.core.service

import com.sytac.excercise.infrastructure.tweeter.services.TwitterStreamMapper
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withTimeout
import mu.KotlinLogging
import java.time.Duration

class RecopilationService(
	private val stream: StreamProvider,
	private val timeout: Duration,
	private val statistics: Statistics,
	private val mapper: TwitterStreamMapper = TwitterStreamMapper(),
	private val messageRepository: MessageRepository = MessageRepositoryImpl(),
	val maxCount: Int = 100
) {
	private val logger = KotlinLogging.logger {}
	private var count=0

	suspend fun recopilate(){
		logger.info("Starting to recopilate msg")
		val buffer = stream.getStream().bufferedReader()
		logger.debug("Stream retrieved")
		withTimeout(timeout.toMillis()){
			var line: String? = buffer.readLine()
			logger.debug("Read a message")
			while(line != null && isActive && count<maxCount){
				statistics.incrementReadMessages()
				count++
				val message = mapper.map(line)?.toMessage()
				logger.debug("message ${if(message == null)"skipped" else "converted ok"}")
				message?.let { messageRepository.add(it) }
				line = buffer.readLine()
			}
		}
		logger.info("Finished recopilating msg. count was $count")
	}

}