package com.sytac.excercise.core.service

import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.io.FileReader
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime
import kotlin.test.assertEquals

internal class StatisticsImplTest{

	private val clock: Clock = mockk()
	private val statistics = StatisticsImpl(clock, "output/statistics.txt")

	@Test
	internal fun `after 3 seconds and 3 messages read writes correct file`() {
		coEvery { clock.getCurrentLocalDateTime()} returns LocalDateTime.of(2022, 5,18,0,0,0)
		statistics.startTimer()

		statistics.incrementReadMessages()
		statistics.incrementReadMessages()
		statistics.incrementReadMessages()

		coEvery { clock.getCurrentLocalDateTime()} returns LocalDateTime.of(2022, 5,18,0,0, 3)
		statistics.stopTimer()

		statistics.writeToFile()

		val fileReader = FileReader("output/statistics.txt")
		assertEquals("""Run from: 2022-05-18T00:00 to 2022-05-18T00:00:03 had 1 messages per second.""", fileReader.readLines().first())
		fileReader.close()
		Files.delete(Paths.get("output/statistics.txt"))
	}


}