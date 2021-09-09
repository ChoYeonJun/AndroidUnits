package com.cyj.chatui

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cyj.chatui.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    lateinit var chatAdapter: ChatAdapter
    val chats = mutableListOf<Chat>()
    private lateinit var toolbar : Toolbar
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.title = "문의하기"
        toolbar.setTitleTextColor(Color.BLACK)
        toolbar.setSubtitleTextColor(Color.BLACK)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)  // 왼쪽 버튼 사용 여부 true
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24)  // 왼쪽 버튼 이미지 설정
        supportActionBar!!.setDisplayShowTitleEnabled(false)
//        binding.backBtn.setOnClickListener {
//            onStop()
//        }

        recyclerView = binding.recycleView
        initRecycler()
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 클릭된 메뉴 아이템의 아이디 마다 when 구절로 클릭시 동작을 설정한다.
        when(item!!.itemId){
            android.R.id.home->{ // 메뉴 버튼
                Snackbar.make(toolbar,"Menu pressed",Snackbar.LENGTH_SHORT).show()
            }
            R.id.menu_search->{ // 검색 버튼
                Snackbar.make(toolbar,"Search menu pressed",Snackbar.LENGTH_SHORT).show()
            }
            R.id.menu_account->{ // 계정 버튼
                Snackbar.make(toolbar,"Account menu pressed",Snackbar.LENGTH_SHORT).show()
            }
            R.id.menu_logout->{ // 로그아웃 버튼
                Snackbar.make(toolbar,"Logout menu pressed",Snackbar.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRecycler() {
        chatAdapter = ChatAdapter(this)
        recyclerView.adapter = chatAdapter


        chats.apply {
            add(Chat( chat = "자유 좌석은 선착순인가요?", isOther = true))
            add(Chat( chat = "네! 1번부터 21번까지 아무 좌석이나 먼저 예약하시고, 방문해서 원하시는 좌석에 앉으시면 됩니다~ ", isOther = false))
            add(Chat( chat = "고맙다 게이야", isOther = true))
            add(Chat( chat = "섹1스 섹1스", isOther = false))
            add(Chat( chat = "꺼억", isOther = true))

            chatAdapter.mChat = chats
            chatAdapter.notifyDataSetChanged()

        }
    }
}