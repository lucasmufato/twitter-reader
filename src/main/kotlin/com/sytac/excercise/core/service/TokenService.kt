package com.sytac.excercise.core.service

interface TokenService {
	fun makeShortLivedTokenRequest() :String
	fun authorizeWithPin(pin: String)
}
