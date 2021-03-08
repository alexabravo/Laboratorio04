package com.example.pokemonapp.Retrofit2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit2() {
    private val utilsUrls: PokeApi

    var Utils = Utils()

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Utils.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        utilsUrls = retrofit.create(PokeApi::class.java)
    }

    fun getService(): PokeApi {
        return utilsUrls
    }
}
