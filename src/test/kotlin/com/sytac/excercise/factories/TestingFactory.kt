package com.sytac.excercise.factories

import com.sytac.excercise.core.domain.Author
import com.sytac.excercise.core.domain.Message
import java.time.LocalDateTime

object TestingFactory {

	val FIRST_OF_JANUARY_AT_1 = LocalDateTime.of(2020, 1, 1, 1, 0, 0)
	val SECOND_OF_JANUARY_AT_1 = LocalDateTime.of(2020, 1, 2, 1, 0, 0)
	val THIRD_OF_JANUARY_AT_1 = LocalDateTime.of(2020, 1, 2, 1, 0, 0)

	val BEAVER_AUTHOR = Author(234L, FIRST_OF_JANUARY_AT_1, "beaver guy", "BeaverGuy")
	val BIG_BEAVER_AUTHOR = Author(2345L, FIRST_OF_JANUARY_AT_1, "BIG beaver", "BigBeaver")

	val BEAVER_MESSAGE = Message(123L, FIRST_OF_JANUARY_AT_1, "some beaver text", BEAVER_AUTHOR)
	val BIG_BEAVER_MESSAGE = Message(1234L, FIRST_OF_JANUARY_AT_1, "BIG beaver text", BIG_BEAVER_AUTHOR)
}