package com.cyj.slidingrecyclerview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cyj.slidingrecyclerview.databinding.ActivityMainBinding
import com.cyj.slidingrecyclerview.databinding.SlideLayoutBinding
import com.sothree.slidinguppanel.ScrollableViewHelper
import com.sothree.slidinguppanel.SlidingUpPanelLayout


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding  // 뷰 바인딩
    private lateinit var slideLayout: SlideLayoutBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var slidePanel:SlidingUpPanelLayout
    private lateinit var personList: List<Person>
    private lateinit var adapter: ExpandableAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        slidelayout = SlideLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        slideLayout = binding.slide

        recyclerView = slideLayout.recyclerView

        personList = ArrayList()
        personList = loadData()

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ExpandableAdapter(this, personList)
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            var flag = false
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    //최상단

                    slidePanel.isTouchEnabled = false
                }
                else if (!recyclerView.canScrollVertically(-1)) {
                    //최하단
                    slidePanel.isTouchEnabled = true
                }
            }

        })

        slidePanel = binding.mainFrame                      // SlidingUpPanel
        slidePanel.addPanelSlideListener(PanelEventListener())  // 이벤트 리스너 추가
        slidePanel.panelHeight = 200
        slidePanel.setScrollableView(recyclerView)
        slidePanel.setScrollableViewHelper(NestedScrollableViewHelper())
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

//        // 터치로 슬라이드 가능 여부 설정 (panelState 변경으로 여닫는 건 가능)
//        slideLayout.btnTouch.setOnClickListener {
//            val touchEnabled = slidePanel.isTouchEnabled
//            if (touchEnabled) {
//                slideLayout.btnTouch.text = "잠금해제"
//                slidePanel.isTouchEnabled = false
//            } else {
//                slideLayout.btnTouch.text = "터치잠금"
//                slidePanel.isTouchEnabled = true
//            }
//        }

//        // 패널 활성화 여부 설정 (터치, 함수 모두 불가능)
//        slideLayout.btnEnable.setOnClickListener {
//            val enabled = slidePanel.isEnabled
//            if (enabled) {
//                slideLayout.btnEnable.text = "활성화"
//                slidePanel.isEnabled = false
//            } else {
//                slideLayout.btnEnable.text = "비활성화"
//                slidePanel.isEnabled = true
//            }
//        }
//
    }

    inner class NestedScrollableViewHelper : ScrollableViewHelper() {
        override fun getScrollableViewScrollPosition(
            scrollableView: View,
            isSlidingUp: Boolean,
        ): Int {
            return if (recyclerView is NestedScrollView) {
                if (isSlidingUp) {
                    toast(recyclerView.scrollY.toString())
                    recyclerView.getScrollY()
                } else {
                    val nsv = recyclerView as NestedScrollView
                    val child = nsv.getChildAt(0)
                    child.bottom - (nsv.height + nsv.scrollY)
                }
            } else {
                0
            }
        }
    }
        fun toast(string: String){
            Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
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
        )
        {
//            if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
//                slideLayout.btnToggle.text = "열기"
//            } else if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
//                slideLayout.btnToggle.text = "닫기"
////                slidePanel.isTouchEnabled = false
//            }
        }
    }
}