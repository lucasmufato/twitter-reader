package com.sytac.excercise.infrastructure

import com.sytac.excercise.infrastructure.tweeter.model.Tweet
import com.sytac.excercise.infrastructure.tweeter.model.User
import com.sytac.excercise.infrastructure.tweeter.services.TwitterStreamMapper
import org.junit.jupiter.api.Test
import java.io.FileReader
import kotlin.test.assertEquals

internal class TwitterStreamMapperTest{

	val reader = FileReader("src/test/resources/stream_example.txt").buffered()
	val mapper = TwitterStreamMapper()

	@Test
	internal fun `maps correctly a tweet`() {
		val line: String = reader.readLine()

		val message = mapper.map(line)

		assertEquals(
			Tweet(
				1525955551389179904,
				"Sun May 15 21:45:28 +0000 2022",
				"RT @bbw_matures: https://t.co/AF6EDAtLVk",
				User(2427890449, "Fri Apr 04 21:42:51 +0000 2014", "Shandy Drinker", "ShandyMemoirs")
			),
			message)
	}

	@Test
	internal fun `ignore count messages`() {
		val line = """{"limit":{"track":1067,"timestamp_ms":"1652651134733"}}"""
		val message = mapper.map(line)

		assertEquals(
			null,
			message)
	}
}