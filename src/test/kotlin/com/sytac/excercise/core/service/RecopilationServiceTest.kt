package com.sytac.excercise.core.service

import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import java.io.BufferedInputStream
import java.io.File
import java.io.InputStream
import java.time.Duration

internal class RecopilationServiceTest {

	private lateinit var streamProvider: StreamProvider
	private lateinit var service: RecopilationService
	private val statistics: Statistics = mockk()

	@Test
	internal fun `reads stream for 20 seconds`() = runTest {
		streamProvider = FileStreamProvider("stream_example.txt")
		coEvery { statistics.incrementReadMessages() }.just(Runs)
		service = RecopilationService(streamProvider, Duration.ofSeconds(10), statistics)
		service.recopilate()
	}

	@Test
	internal fun `uses mapper for every read line and counts it`() = runTest {
		streamProvider = FileStreamProvider("short_stream.txt")
		coEvery { statistics.incrementReadMessages() }.just(Runs)
		service = RecopilationService(streamProvider, Duration.ofSeconds(3), statistics)

		service.recopilate()

		coVerify(exactly = 3) { statistics.incrementReadMessages() }
	}

	@Test
	internal fun `uses mapper for every read line and counts it 2`() = runTest {
		streamProvider = FileStreamProvider("short_stream.txt")
		coEvery { statistics.incrementReadMessages() }.just(Runs)
		service = RecopilationService(streamProvider, Duration.ofSeconds(3), statistics)

		service.recopilate()

		coVerify(exactly = 3) { statistics.incrementReadMessages() }
	}


}

class FileStreamProvider(val file: String) : StreamProvider {

	val ostream: BufferedInputStream = File("src/test/resources/$file").inputStream().buffered()

	override fun getStream(): InputStream {
		return ostream
	}

	override fun closeStream() {
		ostream.close()
	}

}
