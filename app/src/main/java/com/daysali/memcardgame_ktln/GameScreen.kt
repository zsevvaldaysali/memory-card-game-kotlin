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
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == binding.radioButton2.id){
                println(binding.radioButton2.text)
            }
            if(checkedId == binding.radioButton4.id){
                println(binding.radioButton4.text)
            }
            if(checkedId == binding.radioButton6.id){
                println(binding.radioButton6.text)
            }
        }
        binding.radioGroup2.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == binding.radioButtonTek.id){
                println(binding.radioButtonTek.text)
            }
            if(checkedId == binding.radioButtonMultiple.id){
                println(binding.radioButtonMultiple.text)
        }
        }

        binding.button5.setOnClickListener {
            val intent = Intent(this, SinglePlayerGame::class.java)
            startActivity(intent)
            finish()
        }



    }
}