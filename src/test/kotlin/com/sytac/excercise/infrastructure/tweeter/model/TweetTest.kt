package com.sytac.excercise.infrastructure.tweeter.model

import com.sytac.excercise.core.domain.Author
import com.sytac.excercise.core.domain.Message
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.test.assertEquals


internal class TweetTest{

	@Test
	internal fun `Tweet to Message`() {

		val user = User(2427890449, "Fri Apr 04 21:42:51 +0000 2014","Shandy Drinker","ShandyMemoirs")
		val tweet = Tweet(1525955551389179904,"Sun May 15 21:45:28 +0000 2022","RT @bbw_matures: https://t.co/AF6EDAtLVk",
			user)

		val expectedAuthor = Author(2427890449, LocalDateTime.of(2014,4,4,21,42,51),"Shandy Drinker","ShandyMemoirs")
		val expectedMessage = Message(1525955551389179904, LocalDateTime.of(2022, 5,15,21,45,28) ,"RT @bbw_matures: https://t.co/AF6EDAtLVk",
			expectedAuthor)

		assertEquals(expectedMessage, tweet.toMessage())

	}
}