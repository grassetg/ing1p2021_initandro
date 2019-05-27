package com.example.hellogames

import retrofit2.Call
import retrofit2.http.GET

interface WebService {
    @GET("/game/list")
    fun listGames() : Call<List<Game>>
}