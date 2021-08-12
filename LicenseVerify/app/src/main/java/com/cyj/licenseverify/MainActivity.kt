package com.cyj.licenseverify

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cyj.licenseverify.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okio.IOException
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).async {
//                val deferred1 = async{
//                    delay(500)
//                    350
//                }
//                Log.d("coroutine delay", "${deferred1.await()}")
                send()
            }
        }
    }

    private fun send(): Boolean {
        try {
//            var str = ""
            val client = OkHttpClient()
            val url: String = getString(R.string.uri)
//            val gson = Gson()
//            val json: String = gson.toJson(metaInfo)
            val licenseNum = binding.editText.text
            val json = "{  \"b_no\": [    \"$licenseNum\"  ]}"
//            json.put("b_no", "1100132844")
            val body = RequestBody.create(JSON, json)
            val request = Request.Builder()
                .url(url)
                .post(body)
                .build()
            val response = client.newCall(request).execute()
            val str = response.body?.string()
//            val jsonArray = JSONArray(str);
//            val jsonObject = jsonArray.getJSONObject(0)
//            val result = jsonObject.getString("data")
            val jsonObject = JSONObject(str)
            val jsonArray = jsonObject.getJSONArray("data")
            val data = jsonArray.getJSONObject(0)
            val msg = data.getString("b_stt")
            if (msg != "") {
                binding.textView.setText(msg?.toString())
            }else{
                binding.textView.setText("조회가 되지 않습니다 확인해주세요")
            }
            Log.d("Response","Response : " + str)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}