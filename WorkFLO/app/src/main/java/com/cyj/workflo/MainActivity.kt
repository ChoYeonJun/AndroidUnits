package com.cyj.workflo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.cyj.workflo.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL


class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var binding: ActivityMainBinding
    lateinit var mHandler: Handler
    lateinit var runnable: Runnable
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar)
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        getSongInfo()

        var onPause = true
        binding.playBtn.setOnClickListener {
//            if(binding.playBtn.)
            if (mediaPlayer != null && mediaPlayer.isPlaying) {
                binding.playBtn.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                var playbackPosition = mediaPlayer.currentPosition
                mediaPlayer.pause()
            } else {
                binding.playBtn.setImageResource(R.drawable.ic_baseline_pause_24)
                try {
                    mediaPlayer.start()

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        }

    }

    fun setSongInfo(json: JSONObject) {
        val singer = json.get("singer").toString()
        val album = json.get("album").toString()
        val title = json.get("title").toString()

        binding.albumName.text = album
        binding.songArtist.text = singer
        binding.songTitle.text = title
        val imageUrl = json.get("image").toString()
        CoroutineScope(Dispatchers.Main).launch {
            val bitmap = withContext(Dispatchers.IO) {
                loadImage(imageUrl)
            }
            Log.d("singer", bitmap.toString())
            binding.albumCover.setImageBitmap(bitmap)
        }
        val mp3Url = json.get("file").toString()
        initAudio(mp3Url)
        val lyrics = json.get("lyrics").toString()
        Log.d("singer", singer.toString())
    }

    fun loadImage(uri: String): Bitmap? {
        val bmp: Bitmap? = null
        try {
            val url = URL(uri)
            val stream = url.openStream()
            val bitmap = BitmapFactory.decodeStream(stream)

            return bitmap
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return bmp
    }
    private fun initializeSeekBar() {
        binding.songSeekBar.max = mediaPlayer.duration
        Log.d("max", mediaPlayer.duration.toString())

        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed(object: Runnable{
            override fun run(){
                try{
                    binding.songSeekBar.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000)
                }catch(e: Exception){
                  e.printStackTrace()
                    binding.songSeekBar.progress = 0
                }
            }
        }, 1000)

        binding.songSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (mediaPlayer != null && fromUser){
                    mediaPlayer.seekTo(progress)

                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

    }


    fun getSongInfo() {
        var jsonString: String
        val job = CoroutineScope(Dispatchers.Default).async {
            URL("https://grepp-programmers-challenges.s3.ap-northeast-2.amazonaws.com/2020-flo/song.json").readText()
        }
        CoroutineScope(Dispatchers.Main).launch {
            jsonString = job.await()
            Log.d("json", jsonString)
            val jsonObject = JSONObject(jsonString)
            setSongInfo(jsonObject)
//            val jsonArray = jsonObject.getJSONArray("data")
        }
    }


    private fun initAudio(url: String) {
//            killMediaPlayer()
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(url)
        mediaPlayer.prepare()
        initializeSeekBar()
    }

    private fun killMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }
}