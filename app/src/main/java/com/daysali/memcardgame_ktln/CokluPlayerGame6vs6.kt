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
import com.daysali.memcardgame_ktln.databinding.ActivityCokluPlayerGame6vs6Binding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class CokluPlayerGame6vs6 : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var imgButtonlar: List<ImageButton>
    private lateinit var binding: ActivityCokluPlayerGame6vs6Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCokluPlayerGame6vs6Binding.inflate(layoutInflater)
        setContentView(binding.root)
        database = Firebase.database.reference.child("cards") //db pathi tanımladık
        var sira: Boolean = true



        var ev_grfndr = "GRYFFINDOR"
        var ev_slythrn = "SLYTHERIN"
        var ev_hflpf = "HUFFLEPUFF"
        var ev_rvnclw = "RAVENCLAW"

        var grfndr_say = 0
        var slythrn_say = 0
        var hflpf_say = 0
        var rvnclw_say = 0

        imgButtonlar = listOf(
            binding.cardimgButton1,
            binding.cardimgButton2,
            binding.cardimgButton3,
            binding.cardimgButton4,
            binding.cardimgButton5,
            binding.cardimgButton6,
            binding.cardimgButton7,
            binding.cardimgButton8,
            binding.cardimgButton9,
            binding.cardimgButton10,
            binding.cardimgButton11,
            binding.cardimgButton12,
            binding.cardimgButton13,
            binding.cardimgButton14,
            binding.cardimgButton15,
            binding.cardimgButton16,
            binding.cardimgButton17,
            binding.cardimgButton18,
            binding.cardimgButton19,
            binding.cardimgButton20,
            binding.cardimgButton21,
            binding.cardimgButton22,
            binding.cardimgButton23,
            binding.cardimgButton24,
            binding.cardimgButton25,
            binding.cardimgButton26,
            binding.cardimgButton27,
            binding.cardimgButton28,
            binding.cardimgButton29,
            binding.cardimgButton30,
            binding.cardimgButton31,
            binding.cardimgButton32,
            binding.cardimgButton33,
            binding.cardimgButton34,
            binding.cardimgButton35,
            binding.cardimgButton36
        )

        var sound = MediaPlayer.create(this@CokluPlayerGame6vs6,R.raw.prologue)
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
            sound = MediaPlayer.create(this@CokluPlayerGame6vs6, R.raw.congratulations)
            sound!!.start()
        }

        fun playVictorySound() {

            sound = MediaPlayer.create(this, R.raw.victory)
            sound!!.start()
        }
        fun playShockedSound() {
            if (sound == null) {
                sound = MediaPlayer.create(this@CokluPlayerGame6vs6, R.raw.shocked)
                sound!!.start()
            } else sound!!.start()
        }

        // 2. Pause playback
        fun pauseSound() {
            if (sound?.isPlaying == true) sound?.pause()
        }


        val card_Listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val resimler: ArrayList<Card> = arrayListOf()
                var simdikiKart : Int? = null
                var eslesen_CardCounter = 0
                var oyuncu1Puani : Float? = 0f
                var oyuncu2Puani : Float? = 0f
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
                resimler.forEach {
                    if(grfndr_say < 4){

                        if(it.house.toString() == "GRYFFINDOR")
                        {
                            ekrandaki_resimler.add(it.copy())
                            ekrandaki_resimler.add(it.copy())
                            grfndr_say +=1

                            //println("resimler[$index].house" + resimler[index].house )
                            //println("ekrandaki_resimler[$index].house" + ekrandaki_resimler[index].house )
                        }
                    }
                    if( slythrn_say < 4){
                        if(it.house.toString() == ev_slythrn)
                        {
                            ekrandaki_resimler.add(it.copy())
                            ekrandaki_resimler.add(it.copy())
                            slythrn_say +=1

                            //println("resimler[$index].house" + resimler[index].house )
                            //println("ekrandaki_resimler[$index].house" + ekrandaki_resimler[index].house )
                        }
                    }

                    if( rvnclw_say < 5){

                        if(it.house.toString() == ev_rvnclw)
                        {
                            ekrandaki_resimler.add(it.copy())
                            ekrandaki_resimler.add(it.copy())
                            rvnclw_say +=1

                            //println("resimler[$index].house" + resimler[index].house )
                            //println("ekrandaki_resimler[$index].house" + ekrandaki_resimler[index].house )

                        }
                    }
                    if( hflpf_say < 5){

                        if(it.house.toString() == ev_hflpf)
                        {
                            ekrandaki_resimler.add(it.copy())
                            ekrandaki_resimler.add(it.copy())
                            hflpf_say +=1

                            //println("resimler[$index].house" + resimler[index].house )
                            //println("ekrandaki_resimler[$index].house" + ekrandaki_resimler[index].house )

                        }
                    }


                    println("grfndr_say" + grfndr_say)
                    println("slythrn_say" + slythrn_say)
                    println("rvnclw_say" + rvnclw_say)
                    println("hflpf_say" + hflpf_say)

                }
                ekrandaki_resimler.shuffle()
                println("zor2")

                var sureTut = object : CountDownTimer(60000, 1000) {
                    // override object functions here, do it quicker by setting cursor on object, then type alt + enter ; implement members
                    override fun onTick(t0: Long) {
                        anlik = t0/1000
                        binding.sureTut.text = "00:$anlik sn"

                    }
                    override fun onFinish() {
                        stopSound()
                        playShockedSound()
                        Toast.makeText(this@CokluPlayerGame6vs6, "Süreniz sona ermiştir!", Toast.LENGTH_SHORT).show()

                    }
                }
                sureTut.start()




                fun eslesmeControl(kontrol1:Int, kontrol2:Int){


                    if(ekrandaki_resimler[kontrol1].name.toString() == ekrandaki_resimler[kontrol2].name.toString()){
                        //println(ekrandaki_resimler[kontrol1].name)
                        //println(ekrandaki_resimler[kontrol2].name)
                        eslesen_CardCounter += 1
                        //println("sayaç" + eslesen_CardCounter)
                        ekrandaki_resimler[kontrol1].eslesme_control = true
                        ekrandaki_resimler[kontrol2].eslesme_control = true



                        stopSound()
                        playVictorySound()
                        if(sira){
                            oyuncu1Puani = oyuncu1Puani?.plus((2f * (ekrandaki_resimler[kontrol1].score!!.toFloat()) * (ekrandaki_resimler[kontrol1].katsayi!!.toFloat())))
                            var yeni = (oyuncu1Puani?.times(1000) ?:oyuncu1Puani)?.let { Math.round(it) }?.div(1000)

                            println(ekrandaki_resimler[kontrol1].name + " || score:" + ekrandaki_resimler[kontrol1].score + " || katsayi:" + ekrandaki_resimler[kontrol1].katsayi + " ||")
                            println("senar1) oyuncu puan:$oyuncu1Puani yeni :$yeni")
                            binding.scoretut.text = yeni.toString()
                        }
                        else{
                            oyuncu2Puani = oyuncu2Puani?.plus((2 * (ekrandaki_resimler[kontrol1].score!!.toFloat()) * (ekrandaki_resimler[kontrol1].katsayi!!.toFloat())))
                            var yeni2 = (oyuncu2Puani?.times(1000) ?:oyuncu2Puani)?.let { Math.round(it) }?.div(1000)
                            println(ekrandaki_resimler[kontrol1].name + " || score:" + ekrandaki_resimler[kontrol1].score + " || katsayi:" + ekrandaki_resimler[kontrol1].katsayi + " ||")
                            println("senar1) oyuncu puan:$oyuncu2Puani yeni :$yeni2")
                            binding.scoretut2.text = yeni2.toString()
                        }


                        /*if((ekrandaki_resimler[kontrol1].katsayi == 2)){

                        }
                        if((ekrandaki_resimler[kontrol1].katsayi == 1)){
                                ekrandaki_resimler[kontrol1].eslesme_control = true
                                ekrandaki_resimler[kontrol2].eslesme_control = true
                       }*/


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
                            if(sira){
                                kartlarinToplamPuani = (((ekrandaki_resimler[kontrol1].score!!).toFloat()) + ((ekrandaki_resimler[kontrol2].score!!).toFloat()))
                                oyuncu1Puani = oyuncu1Puani?.minus(((kartlarinToplamPuani!!)/(ekrandaki_resimler[kontrol1].katsayi!!.toFloat())))
                                var yeni = (oyuncu1Puani?.times(1000) ?:oyuncu1Puani)?.let { Math.round(it) }?.div(1000)

                                println(ekrandaki_resimler[kontrol1].name + " || score:" + ekrandaki_resimler[kontrol1].score + " || katsayi:" + ekrandaki_resimler[kontrol1].katsayi + " ||")
                                println("senar2) oyuncu puan:$oyuncu1Puani yeni :$yeni")
                                binding.scoretut.text = yeni.toString()
                            }
                            else{
                                kartlarinToplamPuani = (((ekrandaki_resimler[kontrol1].score!!).toFloat()) + ((ekrandaki_resimler[kontrol2].score!!).toFloat()))
                                oyuncu2Puani = oyuncu2Puani?.minus(((kartlarinToplamPuani!!)/(ekrandaki_resimler[kontrol1].katsayi!!.toFloat())))
                                var yeni2 = (oyuncu2Puani?.times(1000) ?:oyuncu2Puani)?.let { Math.round(it) }?.div(1000)
                                println(ekrandaki_resimler[kontrol1].name + " || score:" + ekrandaki_resimler[kontrol1].score + " || katsayi:" + ekrandaki_resimler[kontrol1].katsayi + " ||")
                                println("senar2) oyuncu puan:$oyuncu2Puani yeni :$yeni2")
                                binding.scoretut2.text = yeni2.toString()
                            }
                        }
                        else{

                            if(sira && ekrandaki_resimler[kontrol1].house != ekrandaki_resimler[kontrol2].house){
                                kartlarinToplamPuani = (((ekrandaki_resimler[kontrol1].score!!).toFloat()) + ((ekrandaki_resimler[kontrol2].score!!).toFloat()))
                                kartlarinPuanOrtalamasi = (kartlarinToplamPuani!!)/2
                                oyuncu1Puani = oyuncu1Puani?.minus(((kartlarinPuanOrtalamasi!!.toFloat())*(ekrandaki_resimler[kontrol1].katsayi!!.toFloat())*(ekrandaki_resimler[kontrol2].katsayi!!.toFloat())))
                                var yeni = (oyuncu1Puani?.times(1000) ?:oyuncu1Puani)?.let { Math.round(it) }?.div(1000)

                                println(ekrandaki_resimler[kontrol1].name + " || score:" + ekrandaki_resimler[kontrol1].score + " || katsayi:" + ekrandaki_resimler[kontrol1].katsayi + " ||")
                                println("senar3) oyuncu puan:$oyuncu1Puani yeni :$yeni")
                                binding.scoretut.text = yeni.toString()
                            }
                            else{
                                kartlarinToplamPuani = (ekrandaki_resimler[kontrol1].score!!).toFloat() + (ekrandaki_resimler[kontrol2].score!!).toFloat()
                                kartlarinPuanOrtalamasi = (kartlarinToplamPuani!!)/2f
                                oyuncu2Puani = oyuncu2Puani?.minus(((kartlarinPuanOrtalamasi!!.toFloat())*(ekrandaki_resimler[kontrol1].katsayi!!.toFloat())*(ekrandaki_resimler[kontrol2].katsayi!!.toFloat())))
                                var yeni2 = (oyuncu2Puani?.times(1000) ?:oyuncu2Puani)?.let { Math.round(it) }?.div(1000)
                                println(ekrandaki_resimler[kontrol1].name + " || score:" + ekrandaki_resimler[kontrol1].score + " || katsayi:" + ekrandaki_resimler[kontrol1].katsayi + " ||")
                                println("senar3) oyuncu puan:$oyuncu1Puani yeni :$yeni2")
                                binding.scoretut2.text = yeni2.toString()

                            }
                        }
                        sira = !sira

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
