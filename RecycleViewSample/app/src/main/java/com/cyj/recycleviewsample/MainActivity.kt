package com.cyj.recycleviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var profileAdapter: ProfileAdapter
    lateinit var rv_profile: RecyclerView
    val datas = mutableListOf<ProfileData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_profile = findViewById(R.id.rv_profile)
        initRecycler()
    }
    private fun initRecycler() {
        profileAdapter = ProfileAdapter(this)
        rv_profile.adapter = profileAdapter


        datas.apply {
            add(ProfileData( name = "mary", age = 24))
            add(ProfileData( name = "jenny", age = 26))
            add(ProfileData( name = "jhon", age = 27))
            add(ProfileData( name = "ruby", age = 21))
            add(ProfileData( name = "yuna", age = 23))

            profileAdapter.datas = datas
            profileAdapter.notifyDataSetChanged()

        }
    }
}