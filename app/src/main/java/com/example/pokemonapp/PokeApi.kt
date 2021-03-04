package com.example.pokemonapp

import android.widget.EditText
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

public interface PokeApi {
    @GET("{id}")
    fun getPokemonById(@Path("id") id: EditText): Call<JsonObject>
}