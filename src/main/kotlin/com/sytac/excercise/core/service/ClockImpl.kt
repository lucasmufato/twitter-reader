package com.sytac.excercise.core.service

import java.time.LocalDateTime
import java.time.ZoneOffset

class ClockImpl : Clock {
	override fun getCurrentLocalDateTime(): LocalDateTime {
		return LocalDateTime.now(ZoneOffset.UTC)
	}
}