package com.sytac.excercise.core.service

interface Statistics {
	fun startTimer()
	fun incrementReadMessages()
	fun stopTimer()
	fun writeToFile()
}