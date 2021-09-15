package com.cyj.workflo

import android.location.Address
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.util.Log
import kotlinx.coroutines.*
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Default).launch {

        }

    }

    fun GetSongInfo(): String{
        var jsonString: String
        val job = CoroutineScope(Dispatchers.Default).async {
            URL("https://grepp-programmers-challenges.s3.ap-northeast-2.amazonaws.com/2020-flo/song.json").readText()
        }
        CoroutineScope(Dispatchers.Main).launch {
            jsonString = job.await()
            Log.d("json", jsonString)
        }


    }
}