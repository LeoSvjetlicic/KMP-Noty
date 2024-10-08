package org.example.project.expects

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url
import org.example.project.data.model.PokemonResponse
import org.example.project.utils.Resource

class ApiImplementation : Api {
    override suspend fun getPokemonList(): Resource<PokemonResponse> {
        try {
            val response = client.get {
                url("pokemon")
            }
            return Resource.Success(response.body())
        } catch (e: RedirectResponseException) {
            // handle 3xx codes
            return (Resource.Error(e))

        } catch (e: ClientRequestException) {
            //handle 4xx error codes
            return (Resource.Error(e))

        } catch (e: ServerResponseException) {
            //handle 5xx error codes
            return (Resource.Error(e))
        } catch (e: Exception) {
            return (Resource.Error(e))
        }
    }
}
