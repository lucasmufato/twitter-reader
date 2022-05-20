package com.sytac.excercise.infrastructure.tweeter.services

import com.google.api.client.http.GenericUrl
import com.google.api.client.http.HttpRequestFactory
import com.google.api.client.http.HttpResponse
import com.sytac.excercise.core.service.StreamProvider
import mu.KotlinLogging
import java.io.InputStream

class StreamProviderImpl(
	private val twitterAuthenticator: TwitterAuthenticator,
	private val trackMessagesContaining: String
) : StreamProvider {

	private lateinit var inputStream: InputStream
	private val logger = KotlinLogging.logger {}

	override fun getStream(): InputStream {
		logger.info("Obtaining request factory")
		val requestFactory: HttpRequestFactory = twitterAuthenticator.requestFactory
		logger.info("Requesting track info")
		val getRequest = requestFactory.buildGetRequest(GenericUrl("https://stream.twitter.com/1.1/statuses/filter.json?track=$trackMessagesContaining"))
		getRequest.readTimeout = 0
		val httpResponse: HttpResponse = getRequest.execute()
		inputStream = httpResponse.content
		logger.info("Track request OK, returning stream")
		return inputStream
	}

	override fun closeStream() {
		inputStream.close()
	}


}