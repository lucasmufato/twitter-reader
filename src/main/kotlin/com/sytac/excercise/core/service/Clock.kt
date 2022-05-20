package com.sytac.excercise.core.service

import java.time.LocalDateTime

interface Clock {
	fun getCurrentLocalDateTime(): LocalDateTime

}
