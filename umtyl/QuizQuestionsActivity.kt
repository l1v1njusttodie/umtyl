package com.example.umtyl


import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var myCurrentPosition: Int = 1
    private var myQuestionsList: ArrayList<Question>? = null
    private var mySelectedOptionPosition: Int = 0
    private var myCorrectAnswers: Int = 0
    private var myUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        //initialization
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val tvProgress: TextView = findViewById(R.id.tv_progress)
        val tvQuestion: TextView = findViewById(R.id.tv_question)
        val tvOptionOne: TextView = findViewById(R.id.tv_option_one)
        val tvOptionTwo: TextView = findViewById(R.id.tv_option_two)
        val tvOptionThree: TextView = findViewById(R.id.tv_option_three)
        val tvOptionFour: TextView = findViewById(R.id.tv_option_four)
        val btnSubmit: Button = findViewById(R.id.btn_submit)
        //initialization

        myUserName = intent.getStringExtra(Constants.USER_NAME)

        myQuestionsList = Constants.getQuestions()

        setQuestion()

        tvOptionOne.setOnClickListener(this)
        tvOptionTwo.setOnClickListener(this)
        tvOptionThree.setOnClickListener(this)
        tvOptionFour.setOnClickListener(this)
        btnSubmit.setOnClickListener(this)
    }

    private fun setQuestion(){
        //initialization
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val tvProgress: TextView = findViewById(R.id.tv_progress)
        val tvQuestion: TextView = findViewById(R.id.tv_question)
        val tvOptionOne: TextView = findViewById(R.id.tv_option_one)
        val tvOptionTwo: TextView = findViewById(R.id.tv_option_two)
        val tvOptionThree: TextView = findViewById(R.id.tv_option_three)
        val tvOptionFour: TextView = findViewById(R.id.tv_option_four)
        val btnSubmit: Button = findViewById(R.id.btn_submit)
        //initialization


        val question = myQuestionsList!![myCurrentPosition - 1]

        defaultOptionsView()

        if(myCurrentPosition == myQuestionsList!!.size){
            btnSubmit.text = "Finish"
        }else{
            btnSubmit.text = "Check"
        }

        progressBar.progress = myCurrentPosition
        tvProgress.text = "$myCurrentPosition" + "/" + progressBar.max
        tvQuestion.text = question!!.question
        tvOptionOne.text = question.optionOne
        tvOptionTwo.text = question.optionTwo
        tvOptionThree.text = question.optionThree
        tvOptionFour.text = question.optionFour
    }

    private fun defaultOptionsView(){
        //initialization
        val tvOptionOne: TextView = findViewById(R.id.tv_option_one)
        val tvOptionTwo: TextView = findViewById(R.id.tv_option_two)
        val tvOptionThree: TextView = findViewById(R.id.tv_option_three)
        val tvOptionFour: TextView = findViewById(R.id.tv_option_four)
        //initialization
        val options = ArrayList<TextView>()
        options.add(0, tvOptionOne)
        options.add(1, tvOptionTwo)
        options.add(2, tvOptionThree)
        options.add(3, tvOptionFour)

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }

    }

    override fun onClick(v: View?) {
        //initialization
        val tvOptionOne: TextView = findViewById(R.id.tv_option_one)
        val tvOptionTwo: TextView = findViewById(R.id.tv_option_two)
        val tvOptionThree: TextView = findViewById(R.id.tv_option_three)
        val tvOptionFour: TextView = findViewById(R.id.tv_option_four)
        val btnSubmit: Button = findViewById(R.id.btn_submit)
        //initialization

        when(v?.id){
            R.id.tv_option_one -> {
                selectedOptionView(tvOptionOne, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(tvOptionTwo, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(tvOptionThree, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(tvOptionFour, 4)
            }
            R.id.btn_submit -> {
                if(mySelectedOptionPosition == 0){
                    myCurrentPosition++

                    when{
                        myCurrentPosition <= myQuestionsList!!.size ->{
                            setQuestion()
                        }else ->{
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME, myUserName)
                        intent.putExtra(Constants.CORRECT_ANSWERS, myCorrectAnswers)
                        intent.putExtra(Constants.TOTAL_QUESTIONS, myQuestionsList!!.size)
                        startActivity(intent)
                    }
                    }
                }else{
                    val question = myQuestionsList?.get(myCurrentPosition - 1)
                    if(question!!.correctAnswer != mySelectedOptionPosition){
                        answerView(mySelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        myCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(myCurrentPosition == myQuestionsList!!.size){
                        btnSubmit.text = "Finish"
                    }else{
                        btnSubmit.text = "Next question"
                    }
                    mySelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        //initialization
        val tvOptionOne: TextView = findViewById(R.id.tv_option_one)
        val tvOptionTwo: TextView = findViewById(R.id.tv_option_two)
        val tvOptionThree: TextView = findViewById(R.id.tv_option_three)
        val tvOptionFour: TextView = findViewById(R.id.tv_option_four)
        //initialization
        when(answer){
            1 ->{
                tvOptionOne.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 ->{
                tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 ->{
                tvOptionThree.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 ->{
                tvOptionFour.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionsView()
        mySelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }
}