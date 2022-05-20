package com.sytac.excercise.core.actions

import com.sytac.excercise.core.service.TokenService

class RequestTemporaryToken(private val tokenService: TokenService) {

	operator fun invoke():String{
		return tokenService.makeShortLivedTokenRequest()
	}
}

