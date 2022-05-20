package com.sytac.excercise.infrastructure.tweeter.services

import com.sytac.excercise.core.service.TokenService

class TokenServiceImpl(private val twitter: TwitterAuthenticator) : TokenService {

	override fun makeShortLivedTokenRequest(): String {
		return twitter.createAuthorizationURL()
	}

	override fun authorizeWithPin(pin: String) {
		twitter.authorizeWith(pin)
	}
}