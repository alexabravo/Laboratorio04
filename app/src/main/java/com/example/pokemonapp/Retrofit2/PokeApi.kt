package com.example.pokemonapp.Retrofit2

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {
    @GET("{id}")
    fun getPokemonById(@Path("id") id: String): Call<JsonObject>
}
