package com.cyj.slidinglayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cyj.slidinglayout.databinding.ActivityMainBinding
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding  // 뷰 바인딩
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val slidePanel = binding.mainFrame                      // SlidingUpPanel
        slidePanel.addPanelSlideListener(PanelEventListener())  // 이벤트 리스너 추가
        slidePanel.panelHeight = 500
        // 패널 열고 닫기
        binding.btnToggle.setOnClickListener {
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
        binding.btnTouch.setOnClickListener {
            val touchEnabled = slidePanel.isTouchEnabled
            if (touchEnabled) {
                binding.btnTouch.text = "잠금해제"
                slidePanel.isTouchEnabled = false
            } else {
                binding.btnTouch.text = "터치잠금"
                slidePanel.isTouchEnabled = true
            }
        }

        // 패널 활성화 여부 설정 (터치, 함수 모두 불가능)
        binding.btnEnable.setOnClickListener {
            val enabled = slidePanel.isEnabled
            if (enabled) {
                binding.btnEnable.text = "활성화"
                slidePanel.isEnabled = false
            } else {
                binding.btnEnable.text = "비활성화"
                slidePanel.isEnabled = true
            }
        }

    }

    // 이벤트 리스너
    inner class PanelEventListener : SlidingUpPanelLayout.PanelSlideListener {
        // 패널이 슬라이드 중일 때
        override fun onPanelSlide(panel: View?, slideOffset: Float) {
            binding.tvSlideOffset.text = slideOffset.toString()
        }

        // 패널의 상태가 변했을 때
        override fun onPanelStateChanged(panel: View?, previousState: SlidingUpPanelLayout.PanelState?, newState: SlidingUpPanelLayout.PanelState?) {
            if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                binding.btnToggle.text = "열기"
            } else if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
                binding.btnToggle.text = "닫기"
            }
        }
    }
}