package co.joebirch.minimise.authentication.remote

import co.joebirch.minimise.authentication.model.AuthenticationModel

interface AuthenticationRemote {

    suspend fun signUp(
        apiKey: String,
        emailAddress: String,
        password: String
    ): AuthenticationModel

    suspend fun signIn(
        apiKey: String,
        emailAddress: String,
        password: String
    ): AuthenticationModel
}