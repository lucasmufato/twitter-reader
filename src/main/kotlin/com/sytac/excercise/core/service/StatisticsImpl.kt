package com.sytac.excercise.core.service

import java.io.FileWriter
import java.time.Duration
import java.time.LocalDateTime

class StatisticsImpl(private val clock: Clock, val fileName: String) : Statistics {
	private lateinit var startTime: LocalDateTime
	private lateinit var stopTime: LocalDateTime
	private var counter = 0
	override fun startTimer() {
		startTime = clock.getCurrentLocalDateTime()
		counter = 0
	}

	override fun incrementReadMessages() {
		counter ++
	}

	override fun stopTimer() {
		stopTime = clock.getCurrentLocalDateTime()
	}

	override fun writeToFile() {
		val seconds = Duration.between(startTime, stopTime).seconds
		val messagesPerSecond = counter/seconds
		val fileWriter = FileWriter(fileName, true)
		fileWriter.write("Run from: $startTime to $stopTime had $messagesPerSecond messages per second.\r")
		fileWriter.flush()
		fileWriter.close()
	}
}