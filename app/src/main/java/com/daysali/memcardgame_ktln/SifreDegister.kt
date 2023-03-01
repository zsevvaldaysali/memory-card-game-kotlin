package com.daysali.memcardgame_ktln

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.daysali.memcardgame_ktln.databinding.ActivitySifreDegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SifreDegister : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var binding : ActivitySifreDegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySifreDegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = Firebase.database.reference.child("cards") //db pathi tanımladık

        /*        binding.button3.setOnClickListener {
            var yeniSifre = binding.yenisifrebutonu.text.toString()
            val kullanıcı = Firebase.auth.currentUser

            kullanıcı!!.updatePassword(yeniSifre).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    println("başarılı")
                    Log.d("Firebase" , "password değiştirildi")
                }
                else {
                    println("şifre değiştirilemedi")
                }
            }
            val intent = Intent(this, GameScreen::class.java)
            startActivity(intent)
        }*/

        binding.button3.setOnClickListener {
            var yeniSifre = binding.yenisifrebutonu.text.toString()
            val kullanıcı = Firebase.auth.currentUser

            kullanıcı!!.updatePassword(yeniSifre).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    println("başarılı")
                    Log.d("Firebase" , "password değiştirildi")
                }
                else {
                    println("şifre değiştirilemedi")
                }
            }
            val intent = Intent(this, GameScreen::class.java)
            startActivity(intent)
        }






    }
}