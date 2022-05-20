package com.sytac.excercise.core.actions

import com.sytac.excercise.core.service.RecopilationService
import com.sytac.excercise.core.service.Statistics

class RecopilateMessages(
	private val recopilationService: RecopilationService,
	private val statistics: Statistics
) {

	suspend operator fun invoke(){
		statistics.startTimer()
		recopilationService.recopilate()
		statistics.stopTimer()
		statistics.writeToFile()
	}

}