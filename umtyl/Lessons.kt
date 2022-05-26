package com.example.umtyl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Lessons : AppCompatActivity() {
    private var myUserName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lessons)

        myUserName = intent.getStringExtra(Constants.USER_NAME)
        val lesson1: ImageView = findViewById(R.id.lesson1)
        lesson1.setOnClickListener{
            val intent = Intent(this, Lesson1::class.java)
            intent.putExtra(Constants.USER_NAME, myUserName)
            startActivity(intent)
            finish()
        }
    }
}