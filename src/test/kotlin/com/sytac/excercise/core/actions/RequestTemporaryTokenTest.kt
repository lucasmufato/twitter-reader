package com.sytac.excercise.core.actions

import com.sytac.excercise.core.service.TokenService
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class RequestTemporaryTokenTest{

	private val tokenService: TokenService = mockk()
	private val requestTemporaryToken = RequestTemporaryToken(tokenService)

	@Test
	internal fun `request temporary token to service`() {
		coEvery { tokenService.makeShortLivedTokenRequest() }.returns("https://someplace.com")

		val request = requestTemporaryToken()

		assertEquals(request, "https://someplace.com")
	}
}