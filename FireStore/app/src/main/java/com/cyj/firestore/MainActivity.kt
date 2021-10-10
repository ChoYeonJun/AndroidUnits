package com.cyj.firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Firebase.firestore

        val city1 = hashMapOf(
            "name" to "LA",
            "state" to "CA",
            "country" to "USA"
        )

        val city = hashMapOf(
            "name" to "miami",
            "state" to "Florida",
            "country" to "USA"
        )

        db.collection("cities").document("LA")
            .set(city1)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

        db.collection("cities").document("Miami")
            .set(city)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    companion object {
        const val TAG = "MyLog"
    }
}