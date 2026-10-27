package co.joebirch.minimise.authentication

import co.joebirch.minimise.authentication.mapper.ResetPasswordResponseMapper
import co.joebirch.minimise.authentication.remote.AuthenticationRemoteStore

object RepositoriesProvider {
     fun getAuthenticationDataRepository(
         remoteStore: AuthenticationRemoteStore,
         passwordResponseMapper: ResetPasswordResponseMapper
     ): AuthenticationRepository =
         AuthenticationDataRepository(
             remoteStore,
             passwordResponseMapper
         )
}