package co.joebirch.minimise.authentication.remote

import co.joebirch.minimise.BuildKonfig
import co.joebirch.minimise.SignInQuery
import co.joebirch.minimise.SignUpMutation
import co.joebirch.minimise.authentication.mapper.toAuthenticationModel
import co.joebirch.minimise.authentication.model.AuthenticationModel
import com.apollographql.apollo.ApolloClient

open class AuthenticationRemoteStore : AuthenticationRemote {

    private val apolloClient = ApolloClient.Builder()
        .serverUrl(BuildKonfig.apiUrl)
        .addHttpHeader("Accept", "application/json")
        .addHttpHeader("Content-Type", "application/json")
        .build()

    override suspend fun signUp(
        apiKey: String,
        emailAddress: String,
        password: String
    ): AuthenticationModel {
        val signUp = SignUpMutation(
            emailAddress = emailAddress,
            password = password
        )
        val s = apolloClient.mutation(signUp).execute()
        return s.data?.createUser?.toAuthenticationModel()
            ?: AuthenticationModel()
    }

    override suspend fun signIn(
        apiKey: String,
        emailAddress: String,
        password: String
    ): AuthenticationModel {
        val repositoriesQuery = SignInQuery(
            emailAddress = emailAddress,
            password = password
        )
        return apolloClient.query(repositoriesQuery).execute().data?.login
            ?.toAuthenticationModel() ?: AuthenticationModel()
    }
}
