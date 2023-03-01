package com.daysali.memcardgame_ktln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.daysali.memcardgame_ktln.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Write a message to the database
        database = Firebase.database.reference.child("cards") //db pathi tanımladık


        /*yeni_GameCard("Ted Lupin","HUFFLEPUFF",
            "",
            "10")*/

        /*var user = Firebase.auth.currentUser
        println(user!!.email.toString())
        println(user!!)
        var txtNewPass = binding.editTextTextPassword9.text.toString()

        println("hadi be "+user!!.updatePassword(txtNewPass))

        user.updatePassword(txtNewPass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                println("Update Success")
                Log.d("Firebase", "password değiştirildi! ")
            }
            else {
                println("Error Update")
            }
        }*/


        val card_Listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val resimler: ArrayList<Card> = arrayListOf()

                // Get Post object and use the values to update the UI
                for (snapshot in dataSnapshot.children) {
                    val game_card = snapshot.getValue<Card>()
                    if (game_card != null) {
                        resimler.add(game_card.copy())
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("Firebase", "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(card_Listener)


    }
}