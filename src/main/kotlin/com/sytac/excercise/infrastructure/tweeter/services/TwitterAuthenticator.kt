package com.sytac.excercise.infrastructure.tweeter.services

import com.google.api.client.auth.oauth.OAuthAuthorizeTemporaryTokenUrl
import com.google.api.client.auth.oauth.OAuthCredentialsResponse
import com.google.api.client.auth.oauth.OAuthGetAccessToken
import com.google.api.client.auth.oauth.OAuthGetTemporaryToken
import com.google.api.client.auth.oauth.OAuthHmacSigner
import com.google.api.client.auth.oauth.OAuthParameters
import com.google.api.client.http.HttpRequestFactory
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.sytac.excercise.core.service.TokenService
import mu.KotlinLogging
import java.io.IOException

/**
 * Provide access to the Twitter API by implementing the required OAuth flow
 *
 * @author Carlo Sciolla
 */
class TwitterAuthenticator(
	private val consumerKey: String,
	private val consumerSecret: String
): TokenService {
	lateinit var requestFactory: HttpRequestFactory
	private lateinit var requestTokenResponse: OAuthCredentialsResponse
	private lateinit var signer: OAuthHmacSigner
	private val logger = KotlinLogging.logger {}

	override fun makeShortLivedTokenRequest(): String {
		return createAuthorizationURL()
	}

	override fun authorizeWithPin(pin: String) {
		authorizeWith(pin)
	}

	fun createAuthorizationURL(): String {
		logger.debug("creating authorization URL")
		signer = OAuthHmacSigner()
		signer.clientSharedSecret = consumerSecret
		requestTokenResponse = getTemporaryToken(signer)
		logger.debug("temporary token retrieved, building autorization url")
		signer.tokenSharedSecret = requestTokenResponse.tokenSecret
		val authorizeUrl = OAuthAuthorizeTemporaryTokenUrl(AUTHORIZE_URL)
		authorizeUrl.temporaryToken = requestTokenResponse.token
		val url = authorizeUrl.build()
		logger.debug("authorization url built succesfully")
		return url
	}

	fun authorizeWith(providedPin: String){
		val accessTokenResponse = retrieveAccessTokens(providedPin, signer, requestTokenResponse.token)
		signer.tokenSharedSecret = accessTokenResponse.tokenSecret
		val parameters = OAuthParameters()
		parameters.consumerKey = consumerKey
		parameters.token = accessTokenResponse.token
		parameters.signer = signer
		requestFactory = TRANSPORT.createRequestFactory(parameters)
	}

	private fun getTemporaryToken(signer: OAuthHmacSigner): OAuthCredentialsResponse {
		val requestToken = OAuthGetTemporaryToken(REQUEST_TOKEN_URL)
		requestToken.consumerKey = consumerKey
		requestToken.transport = TRANSPORT
		requestToken.signer = signer
		val requestTokenResponse = try {
			requestToken.execute()
		} catch (e: IOException) {
			logger.error("Error adquiring token", e)
			throw TwitterAuthenticationException("Unable to aquire temporary token: " + e.message, e)
		}
		logger
		return requestTokenResponse
	}

	private fun retrieveAccessTokens(providedPin: String, signer: OAuthHmacSigner, token: String): OAuthCredentialsResponse {
		val accessToken = OAuthGetAccessToken(ACCESS_TOKEN_URL)
		accessToken.verifier = providedPin
		accessToken.consumerKey = consumerSecret
		accessToken.signer = signer
		accessToken.transport = TRANSPORT
		accessToken.temporaryToken = token
		val accessTokenResponse: OAuthCredentialsResponse = try {
			accessToken.execute()
		} catch (e: IOException) {
			throw TwitterAuthenticationException("Unable to authorize access: " + e.message, e)
		}
		return accessTokenResponse
	}

	companion object {
		private val TRANSPORT: HttpTransport = NetHttpTransport()
		private const val AUTHORIZE_URL = "https://api.twitter.com/oauth/authorize"
		private const val ACCESS_TOKEN_URL = "https://api.twitter.com/oauth/access_token"
		private const val REQUEST_TOKEN_URL = "https://api.twitter.com/oauth/request_token"
	}

}