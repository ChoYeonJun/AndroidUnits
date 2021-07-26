package com.cyj.firebaseauthaccess

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cyj.firebaseauthaccess.databinding.ActivityDoneBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


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

        binding.buttonPushData.setOnClickListener { PushtoUsers() }
    }

    private fun PushtoUsers() {
        val database = Firebase.database

        val myRef = database.getReference("users/test")
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        if (currentFirebaseUser != null) {
            val mRef = database.getReference("marks")
//            val id: MutableMap<String, String> = HashMap()
//            mRef.setValue(currentFirebaseUser.uid.toString())
            val postRef = mRef.child(currentFirebaseUser.uid)
            val marks: MutableMap<String, String> = HashMap()
            marks["Name"] = "fname"
            marks["Phone"] = "fphone"
            marks["Year"] = "fyear"
            marks["Email"] = "femail"
            val uid = mRef.key
            postRef.setValue(marks)
        }

        Toast.makeText(this, "Thanks!", Toast.LENGTH_LONG).show()
    }

//region firebase realtime db rules set
//{
//    "rules":{
//      "marks":{
//          "$uid":{
//              ".read":"auth.uid==$uid",
//              ".write":"auth.uid==$uid",
//          }
//      }
//  }
//}
//$uid : marks 하위 노드들을 지칭하는 변수
//fir-authaccess-default-rtdb:
//  marks:
//      uid1:
//          Email: "femail"
//          Name: "fname"
//          Phone: "fphone"
//          Year: "fyear"
//      uid2:
//          Email: "temail"
//          Name: "tname"
//          Phone: "tphone"
//          Year: "tyear"
//가 firebase realtime db에 저장되어 있을 때
// uid1, uid2를 지칭하는 변수를 $uid로 설정(다른이름 $userid, $user... 상관없음)하여
// 접근하는 user의 uid가 접근하려는 db에 존재하는지 확인하고 그 db에만 접근하도록 한다.

//endregion
}