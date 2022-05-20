package com.sytac.excercise.core.service

import com.sytac.excercise.factories.TestingFactory.BEAVER_AUTHOR
import com.sytac.excercise.factories.TestingFactory.BEAVER_MESSAGE
import com.sytac.excercise.factories.TestingFactory.FIRST_OF_JANUARY_AT_1
import com.sytac.excercise.factories.TestingFactory.SECOND_OF_JANUARY_AT_1
import com.sytac.excercise.factories.TestingFactory.THIRD_OF_JANUARY_AT_1
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


internal class MessageRepositoryImplTest {

	private val repository: MessageRepository = MessageRepositoryImpl()

	@Test
	internal fun `orders messages by users creation date`() {
		repository.add(MESSAGE_OF_USER_FROM_3_1_2020)
		repository.add(MESSAGE_OF_USER_FROM_1_1_2020)
		repository.add(MESSAGE_OF_USER_FROM_2_1_2020)

		val sortedMessages = repository.getAllSorted()

		assertEquals(MESSAGE_OF_USER_FROM_1_1_2020, sortedMessages.elementAt(0))
		assertEquals(MESSAGE_OF_USER_FROM_2_1_2020, sortedMessages.elementAt(1))
		assertEquals(MESSAGE_OF_USER_FROM_3_1_2020, sortedMessages.elementAt(2))
	}

	@Test
	internal fun `orders messages by its creation date when user is the same`() {
		repository.add(MESSAGE_FROM_1_1_2020_OF_USER_1)
		repository.add(MESSAGE_FROM_2_1_2020_OF_USER_2)
		repository.add(MESSAGE_FROM_3_1_2020_OF_USER_1)

		val sortedMessages = repository.getAllSorted()

		assertEquals(MESSAGE_FROM_2_1_2020_OF_USER_2, sortedMessages.elementAt(0))
		assertEquals(MESSAGE_FROM_1_1_2020_OF_USER_1, sortedMessages.elementAt(1))
		assertEquals(MESSAGE_FROM_3_1_2020_OF_USER_1, sortedMessages.elementAt(2))
	}

	private companion object {
		val MESSAGE_OF_USER_FROM_1_1_2020 = BEAVER_MESSAGE.copy(author = BEAVER_AUTHOR.copy(creationDate = FIRST_OF_JANUARY_AT_1))
		val MESSAGE_OF_USER_FROM_2_1_2020 = BEAVER_MESSAGE.copy(author = BEAVER_AUTHOR.copy(creationDate = SECOND_OF_JANUARY_AT_1))
		val MESSAGE_OF_USER_FROM_3_1_2020 = BEAVER_MESSAGE.copy(author = BEAVER_AUTHOR.copy(creationDate = THIRD_OF_JANUARY_AT_1))

		val MESSAGE_FROM_1_1_2020_OF_USER_1 = BEAVER_MESSAGE.copy(
			creationDate = FIRST_OF_JANUARY_AT_1,
			author = BEAVER_AUTHOR.copy(creationDate = THIRD_OF_JANUARY_AT_1))
		val MESSAGE_FROM_2_1_2020_OF_USER_2 = BEAVER_MESSAGE.copy(
			creationDate = SECOND_OF_JANUARY_AT_1,
			author = BEAVER_AUTHOR.copy(creationDate = FIRST_OF_JANUARY_AT_1))
		val MESSAGE_FROM_3_1_2020_OF_USER_1 = BEAVER_MESSAGE.copy(
			creationDate = THIRD_OF_JANUARY_AT_1,
			author = BEAVER_AUTHOR.copy(creationDate = THIRD_OF_JANUARY_AT_1))
	}
}