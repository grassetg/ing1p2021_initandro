package com.example.hellogames

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val indexFragment = Index()
        fragmentTransaction.replace(R.id.fragment_index, indexFragment)
        fragmentTransaction.commit()
    }
}
