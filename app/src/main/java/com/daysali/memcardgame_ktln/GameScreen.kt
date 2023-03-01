package com.daysali.memcardgame_ktln

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.daysali.memcardgame_ktln.databinding.ActivityGameScreenBinding

class GameScreen : AppCompatActivity() {
    private lateinit var binding: ActivityGameScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var seviye2 :  String = "2x2"
        var seviye4 :  String = "4x4"
        var seviye6 :  String = "6x6"
        var seviye : String = ""
        var gamerSayisi : String = ""
        var gamerSayisiTek : String = "Tek Oyuncu"
        var gamerSayisiCok : String = "Çoklu Oyuncu"

        binding.button2.setOnClickListener {
            val intent = Intent(this@GameScreen, SifreDegister::class.java)
            startActivity(intent)
            finish()
            }


        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == binding.radioButton2.id){
                seviye = (binding.radioButton2.text).toString()
            }
            if(checkedId == binding.radioButton4.id){
                seviye = (binding.radioButton4.text).toString()
            }
            if(checkedId == binding.radioButton6.id){
                seviye = (binding.radioButton6.text).toString()
            }
        }
        binding.radioGroup2.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == binding.radioButtonTek.id){
                gamerSayisi = (binding.radioButtonTek.text).toString()
            }
            if(checkedId == binding.radioButtonMultiple.id){
                gamerSayisi = (binding.radioButtonMultiple.text).toString()
        }
        }

        binding.button5.setOnClickListener {
            if(seviye == seviye2 && gamerSayisi == gamerSayisiTek){
                val intent = Intent(this@GameScreen, SinglePlayerGame::class.java)
                startActivity(intent)
                finish()
            }
            if(seviye == seviye4 && gamerSayisi == gamerSayisiTek){
                val intent = Intent(this@GameScreen, SinglePlayerGame4vs4::class.java)
                startActivity(intent)
                finish()
            }
            if(seviye == seviye6 && gamerSayisi == gamerSayisiTek){
                val intent = Intent(this@GameScreen, SinglePlayerGame6vs6::class.java)
                startActivity(intent)
                finish()
            }
            if(seviye == seviye2 && gamerSayisi == gamerSayisiCok){
                val intent = Intent(this@GameScreen, CokluPlayerGame::class.java)
                startActivity(intent)
                finish()
            }
            if(seviye == seviye4 && gamerSayisi == gamerSayisiCok){
                val intent = Intent(this@GameScreen, CokluPlayerGame4vs4::class.java)
                startActivity(intent)
                finish()
            }
            if(seviye == seviye6 && gamerSayisi == gamerSayisiCok){
                val intent = Intent(this@GameScreen, CokluPlayerGame6vs6::class.java)
                startActivity(intent)
                finish()
            }

        }



    }
}