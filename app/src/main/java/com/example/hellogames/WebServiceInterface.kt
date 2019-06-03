package com.example.hellogames

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServiceInterface {
    @GET("game/list")
    fun listGames() : Call<List<Game>>

    @GET("game/details")
    fun detailGame(@Query("game_id") game_id : Int) : Call<GameDetail>
}