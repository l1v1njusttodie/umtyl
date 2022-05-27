package com.example.umtyl

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Lesson1 : AppCompatActivity(){
    private var myUserName: String? = null
    private var myCurrentPosition:Int = 1
    private var myCardWordList: ArrayList<CardWord>? = null

    lateinit var frontAnim: AnimatorSet
    lateinit var backAnim: AnimatorSet
    var isFront = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson1)

        myUserName = intent.getStringExtra(Constants.USER_NAME)
        val btntest: Button = findViewById(R.id.testBtn)
        val cardFront: TextView = findViewById(R.id.card_front)
        val cardBack: TextView = findViewById(R.id.card_back)

        setCardWord()

        val scale:Float = applicationContext.resources.displayMetrics.density
        cardFront.cameraDistance = 8000 * scale
        cardBack.cameraDistance = 8000 * scale

        frontAnim = AnimatorInflater.loadAnimator(applicationContext, R.animator.front_animation) as AnimatorSet
        backAnim = AnimatorInflater.loadAnimator(applicationContext, R.animator.back_animation) as AnimatorSet


        cardFront.setOnClickListener{
            if(isFront){
                frontAnim.setTarget(cardFront)
                backAnim.setTarget(cardBack)
                frontAnim.start()
                backAnim.start()
                isFront = false
            }else{
                frontAnim.setTarget(cardBack)
                backAnim.setTarget(cardFront)
                backAnim.start()
                frontAnim.start()
                isFront = true
            }
        }

        btntest.setOnClickListener {
            val intent = Intent(this,QuizQuestionsActivity::class.java)
            intent.putExtra(Constants.USER_NAME, myUserName)
            startActivity(intent)
            finish()
        }
    }

    private fun setCardWord(){
        isFront = true
        val cardFront: TextView = findViewById(R.id.card_front)
        val cardBack: TextView = findViewById(R.id.card_back)
        val btnNext: Button = findViewById(R.id.btn_next)
        val btnPrev: Button = findViewById(R.id.btn_prev)

        val myCardWordList = ConstantWords.getCardWord()

        val word = myCardWordList!![myCurrentPosition - 1]

        cardFront.text = word.frontWord
        cardBack.text = word.backWord

        btnNext.setOnClickListener(){
            myCurrentPosition++
            isFront = true
            if(myCurrentPosition == myCardWordList!!.size) {Toast.makeText(this, "over", Toast.LENGTH_SHORT).show()}
            setCardWord()
        }
        btnPrev.setOnClickListener(){
            myCurrentPosition--
            isFront = true
            if(myCurrentPosition == 0) {Toast.makeText(this, "over", Toast.LENGTH_SHORT).show()}
            setCardWord()
        }
    }
}