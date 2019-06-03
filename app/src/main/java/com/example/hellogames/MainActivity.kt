package com.example.hellogames

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(), FragmentOnClickInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val indexFragment = Index()
        fragmentTransaction.replace(R.id.activity_main, indexFragment)
        fragmentTransaction.commit()
    }

    override fun onImageClick(game_id : Int) {
        val dataBundle = Bundle()
        dataBundle.putInt("game_id", game_id)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val detailFragment = gameDetail()
        detailFragment.arguments = dataBundle
        fragmentTransaction.replace(R.id.activity_main, detailFragment)
        fragmentTransaction.commit()
    }

    override fun onLinkClick(url: String) {
        val implicitIntent = Intent(Intent.ACTION_VIEW)

        // Add the required data in the intent (herethe URL we want to open)
        Log.d("INTENT", "url : $url")
        implicitIntent.data = Uri.parse(url)

        // Launch the intent
        startActivity(implicitIntent)
    }
}
