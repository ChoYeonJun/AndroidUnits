package com.cyj.navermapwithfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment()
    }
    fun setFragment(){
        val blankfragment = BlankFragment()

        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.frameLayout, blankfragment)
        transaction.commit()
    }
}