package com.cyj.slidingrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cyj.slidingrecyclerview.databinding.ActivityMainBinding
import com.cyj.slidingrecyclerview.databinding.SlideLayoutBinding
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding  // 뷰 바인딩
    private lateinit var slideLayout: SlideLayoutBinding

    private lateinit var personList: List<Person>
    private lateinit var adapter: ExpandableAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        slidelayout = SlideLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        slideLayout = binding.slide

        val recyclerView = slideLayout.recyclerView

        personList = ArrayList()
        personList = loadData()

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ExpandableAdapter(this, personList)
        recyclerView.adapter = adapter


        val slidePanel = binding.mainFrame                      // SlidingUpPanel
        slidePanel.addPanelSlideListener(PanelEventListener())  // 이벤트 리스너 추가
        slidePanel.panelHeight = 200
        // 패널 열고 닫기
        slideLayout.btnToggle.setOnClickListener {
            val state = slidePanel.panelState
            // 닫힌 상태일 경우 열기
            if (state == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                slidePanel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            }
            // 열린 상태일 경우 닫기
            else if (state == SlidingUpPanelLayout.PanelState.EXPANDED) {
                slidePanel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            }
        }

        // 터치로 슬라이드 가능 여부 설정 (panelState 변경으로 여닫는 건 가능)
        slideLayout.btnTouch.setOnClickListener {
            val touchEnabled = slidePanel.isTouchEnabled
            if (touchEnabled) {
                slideLayout.btnTouch.text = "잠금해제"
                slidePanel.isTouchEnabled = false
            } else {
                slideLayout.btnTouch.text = "터치잠금"
                slidePanel.isTouchEnabled = true
            }
        }

        // 패널 활성화 여부 설정 (터치, 함수 모두 불가능)
        slideLayout.btnEnable.setOnClickListener {
            val enabled = slidePanel.isEnabled
            if (enabled) {
                slideLayout.btnEnable.text = "활성화"
                slidePanel.isEnabled = false
            } else {
                slideLayout.btnEnable.text = "비활성화"
                slidePanel.isEnabled = true
            }
        }

    }
    private fun loadData(): List<Person> {
        val people = ArrayList<Person>()

        val persons = resources.getStringArray(R.array.people)

        for (i in persons.indices) {
            val person = Person().apply {
                name = persons[i]
            }
            people.add(person)
        }
        return people
    }

    inner class PanelEventListener : SlidingUpPanelLayout.PanelSlideListener {
        // 패널이 슬라이드 중일 때
        override fun onPanelSlide(panel: View?, slideOffset: Float) {
//            slideLayout.tvSlideOffset.text = slideOffset.toString()
        }

        // 패널의 상태가 변했을 때
        override fun onPanelStateChanged(
            panel: View?,
            previousState: SlidingUpPanelLayout.PanelState?,
            newState: SlidingUpPanelLayout.PanelState?,
        ) {
            if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                slideLayout.btnToggle.text = "열기"
            } else if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
                slideLayout.btnToggle.text = "닫기"
            }
        }
    }
}