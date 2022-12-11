package com.daysali.memcardgame_ktln

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)
        // Write a message to the database
        val database = Firebase.database.reference.child("cards") //db pathi tanımladık

        fun newCard( name: String, house: String, image: String, score: String) {
            val card = Card(name, house, image, score)
            val cardId = database.push().key

            database.child(cardId!!).setValue(card) //carddan alınan verilerle db oluşturduu
        }

        /*newCard("Severus Snape","SLYTHERIN",
            "",
            "18")*/

        /*val decodedByte = Base64.decode(imageString, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedString.length)*/

        val cardListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                for(snapshot in dataSnapshot.children){
                    val card = snapshot.getValue<Card>()

                }

                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("Firebase", "loadPost:onCancelled", databaseError.toException())
            }
        }
        //database.addValueEventListener(cardListener)


    }
}