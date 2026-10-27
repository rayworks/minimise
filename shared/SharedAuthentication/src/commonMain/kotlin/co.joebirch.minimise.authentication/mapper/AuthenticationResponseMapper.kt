package co.joebirch.minimise.authentication.mapper

import co.joebirch.minimise.SignInQuery
import co.joebirch.minimise.SignUpMutation
import co.joebirch.minimise.authentication.model.AuthenticationModel

fun SignInQuery.Login.toAuthenticationModel(): AuthenticationModel {
    return this.onAuthData?.let {
        AuthenticationModel(it.token)
    } ?: run {
        AuthenticationModel(message = this.onAuthenticationError?.message)
    }
}

fun SignUpMutation.CreateUser.toAuthenticationModel(): AuthenticationModel {
    return this.onAuthData?.let {
        AuthenticationModel(it.token)
    } ?: run {
        AuthenticationModel(message = this.onAuthenticationError?.message)
    }
}