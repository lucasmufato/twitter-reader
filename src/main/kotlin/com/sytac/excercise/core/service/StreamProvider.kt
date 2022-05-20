package com.sytac.excercise.core.service

import java.io.InputStream

interface StreamProvider {

	fun getStream(): InputStream
	fun closeStream()

}
