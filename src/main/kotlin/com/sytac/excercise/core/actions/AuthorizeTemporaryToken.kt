package com.sytac.excercise.core.actions

import com.sytac.excercise.core.service.TokenService

class AuthorizeTemporaryToken(private val tokenService: TokenService) {

	operator fun invoke(pin: String) {
		tokenService.authorizeWithPin(pin)
	}
}