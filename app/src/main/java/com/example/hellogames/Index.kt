package com.example.hellogames


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Index : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_index, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val image1: ImageView = view.findViewById(R.id.imageGame1)
        val image2: ImageView = view.findViewById(R.id.imageGame2)
        val image3: ImageView = view.findViewById(R.id.imageGame3)
        val image4: ImageView = view.findViewById(R.id.imageGame4)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://androidlessonsapi.herokuapp.com/api/help/#api-Game-GameList")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
        val service = retrofit.create(WebService::class.java)
        val call = arrayListOf<Game>()
        val wsCallback: Callback<List<Game>> = object : Callback<List<Game>> {
            override fun onFailure(call: Call<List<Game>>, t: Throwable) {
                // Code here what happens if calling the WebService fails
                Log.w("TAG", "WebService call failed")
            }

            override fun onResponse(call: Call<List<Game>>, response: Response<List<Game>>) {
                if (response.code() == 200) {
                    // We got our data !
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("TAG", "WebService success : " + responseData.size)
                    }
                }
            }
        }

        service.listGames().enqueue(wsCallback)
    }
}
