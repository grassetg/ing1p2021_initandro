package com.example.hellogames


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_game_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class gameDetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val name : TextView = view.findViewById(R.id.nameValueView)
        val type : TextView = view.findViewById(R.id.typeValueView)
        val nbPlayers : TextView = view.findViewById(R.id.nbPlayersValueView)
        val year : TextView = view.findViewById(R.id.yearValueView)
        val desc: TextView = view.findViewById(R.id.textGameInfoLong)
        val image : ImageView = view.findViewById(R.id.gameDetailImage)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://androidlessonsapi.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
        val service = retrofit.create(WebServiceInterface::class.java)

        val wsCallback: Callback<GameDetail> = object : Callback<GameDetail> {
            override fun onFailure(call: Call<GameDetail>, t: Throwable) {
                // Code here what happens if calling the GetGameDetailService fails
                Log.w("TAG", "WebServiceInterface call failed")
            }

            override fun onResponse(call: Call<GameDetail>, response: Response<GameDetail>) {
                if (response.code() == 200) {
                    // We got our data !
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("TAG", "WebServiceInterface success : $responseData")
                    }

                    Log.d("RESULT", responseData.toString())
                    if (responseData != null) {
                        name.text = responseData.name
                        type.text = responseData.type
                        nbPlayers.text = responseData.players.toString()
                        year.text = responseData.year.toString()
                        desc.text = responseData.description_en
                        knowMoreButton.setOnClickListener() {
                            (activity as MainActivity).onLinkClick(responseData.url)
                        }
                        Glide.with(view).load(responseData.picture).into(image)
                    }

                }
            }
        }


            Log.d("Bundle","Game id in bundle :" + this.arguments!!.getInt("game_id"))
            service.detailGame(this.arguments!!.getInt("game_id")).enqueue(wsCallback)

    }
}
