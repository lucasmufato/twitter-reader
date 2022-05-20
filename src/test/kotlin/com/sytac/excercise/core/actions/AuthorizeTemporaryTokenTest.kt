package com.sytac.excercise.core.actions


import com.sytac.excercise.core.service.TokenService
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class AuthorizeTemporaryTokenTest{

	private val tokenService: TokenService = mockk()
	private val authorizeTemporaryToken = AuthorizeTemporaryToken(tokenService)

	@Test
	internal fun `calls authorize service`() {
		coEvery { tokenService.authorizeWithPin(any()) }.just(Runs)

		authorizeTemporaryToken("123456")

		coVerify { tokenService.authorizeWithPin("123456") }
	}
}