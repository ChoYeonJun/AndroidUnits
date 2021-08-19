package com.cyj.kakaoaddresssearch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    // 초기변수설정
    private lateinit var edit_addr: EditText

    // 주소 요청코드 상수 requestCode
    private val SEARCH_ADDRESS_ACTIVITY = 10000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI 요소 연결
        edit_addr = findViewById(R.id.edit_addr)

        // 터치 안되게 막기
        edit_addr.setFocusable(false);
        // 주소입력창 클릭
        edit_addr.setOnClickListener(View.OnClickListener {
            Log.i("주소설정페이지", "주소입력창 클릭")
            val status = NetworkStatus.getConnectivityStatus(applicationContext)
            if (status == NetworkStatus.TYPE_MOBILE || status == NetworkStatus.TYPE_WIFI) {
                Log.i("주소설정페이지", "주소입력창 클릭")
                val i = Intent(applicationContext, AddressApiActivity::class.java)
                // 화면전환 애니메이션 없애기
                overridePendingTransition(0, 0)
                // 주소결과
                startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY)
            } else {
                Toast.makeText(applicationContext, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        Log.i("test", "onActivityResult")
        when (requestCode) {
            SEARCH_ADDRESS_ACTIVITY -> if (resultCode == RESULT_OK) {
                Log.i("result", "result in")

                val data = intent?.extras!!.getString("data")
                if (data != null) {
                    Log.i("test", "data:$data")
                    edit_addr!!.setText(data)
                }
            }
        }
    }
}