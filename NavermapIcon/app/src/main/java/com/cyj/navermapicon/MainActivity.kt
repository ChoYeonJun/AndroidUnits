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
import java.io.IOException


class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    private lateinit var drawerButton: Button
    private lateinit var binding: ActivityMainBinding
    var drawerToggle: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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


    fun getPoint(address: String) : List<Address>{
        try{
            val geocoder = Geocoder(baseContext)
            val list = geocoder.getFromLocationName(address, 10)

            toast(list.toString())

            return list
//                toast(e.toString())
//                log("geocoder", e.toString())
            // ... your code that throws the exception here
        }catch(e: IOException){
            Log.e("Error", "grpc failed: " + e.message, e)
            val geocoder = Geocoder(baseContext)
            val list = geocoder.getFromLocationName(address, 10)
            toast(list.toString())
            return list
            // ... retry again your code that throws the exeception
        }


    }

    fun setMarker(list: List<Address>){
        toast(list.size.toString())
        if (list != null) {
            if (list.size == 0) {
                toast("올바른 주소를 입력해주세요")
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
                    // 이벤트 소비, OnMapClick 이벤트는 발생하지 않음
//                        uploadContent()
                    true
                }
                marker.map = naverMap

            }
        }else{
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

    fun isConnected(context: Context): Boolean{
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
        handler.postDelayed(Runnable {
            val list = getPoint(str)
            setMarker(list)
        }, 0)


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
        toast(heightDp.toString())
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

}