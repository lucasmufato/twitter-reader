package com.sytac.excercise.core.actions

import com.sytac.excercise.core.service.RecopilationService
import com.sytac.excercise.core.service.Statistics
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

internal class RecopilateMessagesTest{

	private val recopilationService: RecopilationService = mockk()
	private val statistics: Statistics = mockk()
	private val recopilateMessages = RecopilateMessages(recopilationService, statistics)

	@Test
	internal fun `calls recopilationService`() = runTest{
		coEvery { recopilationService.recopilate() }.just(Runs)
		coEvery { statistics.startTimer() }.just(Runs)
		coEvery { statistics.stopTimer() }.just(Runs)
		coEvery { statistics.writeToFile() }.just(Runs)

		recopilateMessages()

		coVerifyOrder {
			statistics.startTimer()
			recopilationService.recopilate()
			statistics.stopTimer()
			statistics.writeToFile()
		}
	}
}