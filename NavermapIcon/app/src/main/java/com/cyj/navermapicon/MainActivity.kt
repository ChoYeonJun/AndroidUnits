package com.cyj.navermapicon

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.location.Address
import android.location.Geocoder
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.opengl.Visibility
import android.os.Bundle
import android.os.FileUtils
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout.LayoutParams
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cyj.navermapicon.databinding.ActivityMainBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.util.*


class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    private lateinit var drawerButton: Button
    private lateinit var binding: ActivityMainBinding
    private lateinit var slidePanel: SlidingUpPanelLayout
    var drawerToggle: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        slidePanel = binding.mainFrame                      // SlidingUpPanel
        slidePanel.addPanelSlideListener(PanelEventListener())  // 이벤트 리스너 추가
//        slidePanel.panelHeight = 500
//        val but = binding.imageView
//        but.setOnClickListener {
//            toast(drawerToggle.toString())
//            if (drawerToggle == false) {
//                val objectAnimator = ObjectAnimator.ofFloat(binding.content, "translationY", -1200f)
//                objectAnimator.duration = 500 //0.5초에 걸쳐 진행.
//                binding.imageView3.visibility = View.VISIBLE
//                objectAnimator.start()
//            } else {
//                val objectAnimator = ObjectAnimator.ofFloat(binding.content, "translationY", 0f)
//                objectAnimator.duration = 500 //0.5초에 걸쳐 진행.
//                binding.imageView3.visibility = View.GONE
//                objectAnimator.start()
//            }
//            drawerToggle = !drawerToggle
//        }

        setFragment()
        setContentMargin()
    }


    fun getPoint(address: String): List<Address> {
//        val job = CoroutineScope(Dispatchers.Default).async {
//                // background thread
//
//        }
        val geocoder = Geocoder(baseContext)
        val list = geocoder.getFromLocationName(address, 10)
        log("point", list[0].toString())
//        toast(list.toString())
        return list
//        val msg = job.await()
//        setMarker(list)

    }

    fun setMarker(list: List<Address>) {
//        toast(list.size.toString())
        if (list != null) {
            if (list.size == 0) {
//                toast("올바른 주소를 입력해주세요")
                Log.d("Point", "list size == 0")
            } else {
                val address: Address = list[0]
                val lat: Double = address.getLatitude()
                val lon: Double = address.getLongitude()

//                    Toast.makeText(this, "좌표: " + lat.toString() + " " + lon.toString(), Toast.LENGTH_SHORT).show()
                Log.d("Point", "좌표: " + lat.toString() + " " + lon.toString())

                val marker = Marker()
                marker.position = LatLng(lat, lon)
//                    marker.icon = OverlayImage.fromResource(R.drawable.button_background)
//                    val dr = resources.getDrawable(R.drawable.btn_middle)
//                    val bitmap = (dr as BitmapDrawable).bitmap
//                    val d: Drawable = BitmapDrawable(
//                        resources,
//                        Bitmap.createScaledBitmap(bitmap, 50, 50, true)
//                    )
//
//                    val layers = arrayOf(resources.getDrawable(R.drawable.btn_left), d)
//                    val layerDrawable = LayerDrawable(layers)
//                    val b = Bitmap.createBitmap(300 ,300, Bitmap.Config.ARGB_8888)
//                    val c = Canvas(b)
//                    layerDrawable.draw(c)
//                    marker.icon = OverlayImage.fromBitmap(bitmap)
                marker.setOnClickListener { overlay ->
                    toast("마커 1 클릭")
                    val state = slidePanel.panelState
                    // 닫힌 상태일 경우 열기
                    if (state == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                        slidePanel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
                    }
                    // 열린 상태일 경우 닫기
                    else if (state == SlidingUpPanelLayout.PanelState.EXPANDED) {
                        slidePanel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
                    }
                    // 이벤트 소비, OnMapClick 이벤트는 발생하지 않음
//                        uploadContent()
                    true
                }
                marker.map = naverMap

            }
        } else {
            Log.d("Point", "list is null")
        }
    }

    fun setFragment() {
        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }

        mapFragment.getMapAsync(this)
    }

    fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        return isConnected
    }

    override fun onMapReady(naverMap: NaverMap) {
//        val params = LayoutParams(
//            LayoutParams.MATCH_PARENT,
//            LayoutParams.WRAP_CONTENT
//        )
//        params.setMargins(0,binding.content.height,0,0)
//        binding.map
        this.naverMap = naverMap
        val uiSettings = naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = true
//        naverMap.locationSource = locationSource
        val str = "전라북도 전주시 덕진구 소리로 179"
        val handler = Handler(Looper.getMainLooper())
        var alist: Deferred<Any>

        try {
            CoroutineScope(Dispatchers.Default).launch {
                val job : Deferred<List<Address>> = async{
                    getPoint(str)
                }
                CoroutineScope(Dispatchers.Main).launch {
                    val list = job.await()
                    setMarker(list)
                }

            }


//            val result = job.await()
//            if (alist != null){
//
//            }
//            val add = alist
////                val address: List<Address> = add as List<Address>
////                setMarker(address)
////                setMarker(add)
//            if (add is List<*>) {
//                toast(add.toString())
//                val address: List<Address> = add.filterIsInstance<Address>()
//                setMarker(address)
//            }
        } catch (e: Exception) {
            toast(e.message.toString())
            log("Async", e.message.toString())
        }

//        handler.postDelayed(Runnable {
//
////            val list =
////            setMarker(list)
//        }, 0)


    }

    //    override fun onRequestPermissionsResult(requestCode: Int,
//                                            permissions: Array<String>,
//                                            grantResults: IntArray) {
//        if (locationSource.onRequestPermissionsResult(requestCode, permissions,
//                grantResults)) {
//            if (!locationSource.isActivated) { // 권한 거부됨
//                naverMap.locationTrackingMode = LocationTrackingMode.None
//            }
//            return
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }
    fun setContentMargin() {
//
//    val Framelayout = binding.content
//    val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE)
//    inflater.

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        val params = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )

        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)


        val height = metrics.heightPixels
        val heightDp = height.pixelsToDpInt(this)

        val width = metrics.widthPixels
        params.setMargins(0, heightDp, 0, 0)
//        toast(heightDp.toString())
//        binding.content.layoutParams = params
    }

    fun Int.pixelsToDpInt(context: Context): Int {
        return this / (context.resources
            .displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
    }


//     fun uploadContent(){
//
////         val frmlayout = binding.map
////         val imageView = ImageView(baseContext)
////         imageView.setImageResource(R.drawable.btn_left)
////         frmlayout.addView(imageView, 100, 100)
//         toast(drawerToggle.toString())
//         if (drawerToggle == false) {
//             val objectAnimator = ObjectAnimator.ofFloat(binding.content, "translationY", -1200f)
//             objectAnimator.duration = 500 //0.5초에 걸쳐 진행.
//             objectAnimator.start()
//         } else {
//             val objectAnimator = ObjectAnimator.ofFloat(binding.content, "translationY", 0f)
//             objectAnimator.duration = 500 //0.5초에 걸쳐 진행.
//             objectAnimator.start()
//         }
//         drawerToggle = !drawerToggle
//     }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun log(tag: String, text: String) {
        Log.d(tag, text)
    }
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