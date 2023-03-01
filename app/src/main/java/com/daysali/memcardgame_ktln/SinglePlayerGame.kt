package com.daysali.memcardgame_ktln

import android.graphics.BitmapFactory
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Base64
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import com.daysali.memcardgame_ktln.databinding.ActivitySinglePlayerGameBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.lang.Math.round

class SinglePlayerGame : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivitySinglePlayerGameBinding
    private lateinit var imgButtonlar: List<ImageButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySinglePlayerGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = Firebase.database.reference.child("cards") //db pathi tanımladık

        var sound = MediaPlayer.create(this@SinglePlayerGame,R.raw.prologue)
        sound.start()

        // 3. Stops playback
        fun stopSound() {
            if (sound != null) {
                sound!!.stop()
                sound!!.release()
                sound = null
            }
        }

        fun playCongratulationsSound() {
                sound = MediaPlayer.create(this@SinglePlayerGame, R.raw.congratulations)
                sound!!.start()
        }

        fun playVictorySound() {

                sound = MediaPlayer.create(this, R.raw.victory)
                sound!!.start()
        }
        fun playShockedSound() {
            if (sound == null) {
                sound = MediaPlayer.create(this@SinglePlayerGame, R.raw.shocked)
                sound!!.start()
            } else sound!!.start()
        }

        // 2. Pause playback
        fun pauseSound() {
            if (sound?.isPlaying == true) sound?.pause()
        }


        imgButtonlar = listOf(
            binding.imageButton1,
            binding.imageButton2,
            binding.imageButton3,
            binding.imageButton4
        )
        println("image butonlarının sayısı: " + imgButtonlar.size)


        /*
        fun txtOlstr(){
            val dosya_ismi = "cardbilgileri.txt"
            var dosya = File(dosya_ismi)

            //yeni dosya oluşturdum
            val isYeniDosyaOlstrldu : Boolean = dosya.createNewFile()

            if(isYeniDosyaOlstrldu){
                println("$dosya_ismi başarıyla oluşturuldu")
            }
            else {
                println("$dosya_ismi zaten oluşturuldu!")
            }

            //zaten oluşturduğum dosyayı tekrar oluşturmaya çalışıyorum
            val isDosyaOlustrldu : Boolean = dosya.createNewFile()
            if(isDosyaOlustrldu){
                println("$dosya_ismi başarıyla oluşturuldu :) ")
            }
            else{
                println("$dosya_ismi zaaaten oluşturuldu!")
            }

        }*/
        //this.getDir("folder_name", Context.MODE_PRIVATE);


        //txtOlstr()
        val card_Listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val resimler: ArrayList<Card> = arrayListOf()
                var simdikiKart : Int? = null
                var eslesen_CardCounter = 0
                var oyuncuPuani : Float? = 0f
                var kalanSure : Float? = 0f
                var anlik : Long? = null
                var kartlarinToplamPuani : Float?
                var kartlarinPuanOrtalamasi : Float? = null
                // Get Card object and use the values to update the UI
                for (snapshot in dataSnapshot.children) {
                    val game_card = snapshot.getValue<Card>()
                    if (game_card != null) {
                        resimler.add(game_card.copy())
                    }
                }
                val ekrandaki_resimler: ArrayList<Card> = arrayListOf()
                resimler.shuffle()
                for (i in 0..1) {

                    ekrandaki_resimler.add(resimler[i].copy())
                    ekrandaki_resimler.add(resimler[i].copy())
                }
                ekrandaki_resimler.shuffle()
                println("zor2")

                var sureTut = object : CountDownTimer(45000, 1000) {
                    // override object functions here, do it quicker by setting cursor on object, then type alt + enter ; implement members
                    override fun onTick(t0: Long) {
                        anlik = t0/1000
                        binding.sureTut.text = "00:$anlik sn"

                    }
                    override fun onFinish() {
                        stopSound()
                        playShockedSound()
                        Toast.makeText(this@SinglePlayerGame, "Süreniz sona ermiştir!", Toast.LENGTH_SHORT).show()

                    }
                }
                sureTut.start()




                fun eslesmeControl(kontrol1:Int, kontrol2:Int){

                    if(ekrandaki_resimler[kontrol1].name.toString() == ekrandaki_resimler[kontrol2].name.toString()){
                        //println(ekrandaki_resimler[kontrol1].name + ekrandaki_resimler[kontrol1].score + ekrandaki_resimler[kontrol1].katsayi)
                        //println(ekrandaki_resimler[kontrol2].name + ekrandaki_resimler[kontrol2].score + ekrandaki_resimler[kontrol2].katsayi)
                        eslesen_CardCounter += 1
                        //println("sayaç" + eslesen_CardCounter)
                        ekrandaki_resimler[kontrol1].eslesme_control = true
                        ekrandaki_resimler[kontrol2].eslesme_control = true

                        stopSound()
                        playVictorySound()


                        /*if((ekrandaki_resimler[kontrol1].katsayi == 2)){

                        }
                        if((ekrandaki_resimler[kontrol1].katsayi == 1)){
                                ekrandaki_resimler[kontrol1].eslesme_control = true
                                ekrandaki_resimler[kontrol2].eslesme_control = true
                       }*/

                        oyuncuPuani = oyuncuPuani?.plus((2f * (ekrandaki_resimler[kontrol1].score!!.toFloat()) * (ekrandaki_resimler[kontrol1].katsayi!!.toFloat()) * (anlik!!.toFloat()/10)))
                        var yeni = (oyuncuPuani?.times(1000) ?:oyuncuPuani)?.let { round(it) }?.div(1000)

                        println(ekrandaki_resimler[kontrol1].name + " || score:" + ekrandaki_resimler[kontrol1].score + " || katsayi:" + ekrandaki_resimler[kontrol1].katsayi + " ||" + " zaman: " + "$anlik")
                        println("senar1) oyuncu puan:$oyuncuPuani yeni :$yeni")
                        binding.scoretut.text = yeni.toString()



                        if(eslesen_CardCounter == 2){
                            println("tüm kartlar eşleşti: " + eslesen_CardCounter)
                            stopSound()
                            playCongratulationsSound()
                        }

                        /*if(kalanSure == 0f){
                            stopSound()
                            playShockedSound()
                        }*/


                    }
                    else {
                        if(ekrandaki_resimler[kontrol1].house == ekrandaki_resimler[kontrol2].house){
                            kartlarinToplamPuani = (ekrandaki_resimler[kontrol1].score!!).toFloat() + (ekrandaki_resimler[kontrol2].score!!.toFloat())
                            oyuncuPuani = oyuncuPuani?.minus((((kartlarinToplamPuani!!)/(ekrandaki_resimler[kontrol1].katsayi!!.toFloat()))*((45 - (anlik!!.toFloat()))/10)))
                            var yeni = (oyuncuPuani?.times(1000) ?:oyuncuPuani)?.let { round(it) }?.div(1000)
                            println(ekrandaki_resimler[kontrol1].name + " || score:" + ekrandaki_resimler[kontrol1].score + " || katsayi:" + ekrandaki_resimler[kontrol1].katsayi + " ||" + " zaman: " + "$anlik")
                            println("senar2) $oyuncuPuani yeni :" + "$yeni")
                            binding.scoretut.text = yeni.toString()
                        }
                        if(ekrandaki_resimler[kontrol1].house != ekrandaki_resimler[kontrol2].house){
                            kartlarinToplamPuani = (((ekrandaki_resimler[kontrol1].score!!).toFloat()) + ((ekrandaki_resimler[kontrol2].score!!).toFloat()))
                            kartlarinPuanOrtalamasi = (kartlarinToplamPuani!!)/2
                            oyuncuPuani = oyuncuPuani?.minus((((kartlarinPuanOrtalamasi!!.toFloat())*(ekrandaki_resimler[kontrol1].katsayi!!.toFloat())*(ekrandaki_resimler[kontrol2].katsayi!!.toFloat()))*((45 - (anlik!!.toFloat()))/10)))
                            var yeni = (oyuncuPuani?.times(1000) ?:oyuncuPuani)?.let { round(it) }?.div(1000)
                            println(ekrandaki_resimler[kontrol1].name + " || score:" + ekrandaki_resimler[kontrol1].score + " || katsayi:" + ekrandaki_resimler[kontrol1].katsayi + " ||" + " zaman: " + "$anlik")
                            println("senar3) oyncu puanı: $oyuncuPuani yeni :" + "$yeni")
                            binding.scoretut.text = yeni.toString()
                        }

                    }

                }
                fun gorunumuResetle(){
                    for(item in ekrandaki_resimler){
                        if (!item.eslesme_control!!){
                            item.isFlip = false
                        }
                    }
                }
                fun kartiGuncelle(durum: Int){
                    val card = ekrandaki_resimler[durum]
                    if(card.isFlip!!){
                        println("Hatali oynama")
                        return
                    }

                    if (simdikiKart == null){
                        simdikiKart = durum
                        gorunumuResetle()
                    } else {
                        println(ekrandaki_resimler[durum].katsayi)
                        eslesmeControl(simdikiKart!!,durum)
                        simdikiKart = null
                    }

                    card.isFlip = !card.isFlip!!
                }
                fun guncelle() {
                    ekrandaki_resimler.forEachIndexed { index, card ->
                        val imgButton = imgButtonlar[index]

                        if(card.isFlip!!){
                            val decodedByte = Base64.decode(card.image, Base64.DEFAULT)
                            val bitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
                            imgButton.setImageBitmap(bitmap)
                        } else{
                            imgButton.setImageResource(R.drawable.default_card)
                        }

                    }
                }


                imgButtonlar.forEachIndexed { index, button ->

                    imgButtonlar[index].setImageResource(R.drawable.default_card)
                    button.setOnClickListener {
                        kartiGuncelle(index)
                        guncelle()
                    }
                }


                /*binding.imageButton1.setOnClickListener(object : View.OnClickListener{
                    override fun onClick(v: View?) {
                        val decodedByte = Base64.decode(ekrandaki_resimler[0].image, Base64.DEFAULT)
                        val bitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
                    }})*/
                /*val decodedByte = Base64.decode(ekrandaki_resimler[i].name, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)*/




            }


            override fun onCancelled(error: DatabaseError) {
                Log.w("firebase","Error",error.toException())
            }


        }


        database.addValueEventListener(card_Listener)
    }
}