package com.sytac.excercise.infrastructure.tweeter.services

class TwitterAuthenticationException : Exception {
	constructor() : super() {}
	constructor(message: String?) : super(message) {}
	constructor(message: String?, t: Throwable?) : super(message, t) {}
	constructor(t: Throwable?) : super(t) {}
}