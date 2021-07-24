package com.cyj.firebaseauthaccess

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cyj.firebaseauthaccess.databinding.ActivityDoneBinding

class DoneActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDoneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("Email") && intent.hasExtra("Name")){
            binding.editTextAccountEmail.setText(intent.getStringExtra("Email"))
            binding.editTextAccountName.setText(intent.getStringExtra("Name"))
        }
    }


}