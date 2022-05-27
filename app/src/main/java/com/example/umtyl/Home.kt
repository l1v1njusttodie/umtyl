package com.example.umtyl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class Home : AppCompatActivity() {
    private var myUserName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        myUserName = intent.getStringExtra(Constants.USER_NAME)

        val blessons: ImageView = findViewById(R.id.lessons)
        blessons.setOnClickListener {
            val intent = Intent(this,Lessons::class.java)
            intent.putExtra(Constants.USER_NAME, myUserName)
            startActivity(intent)
            finish()
        }
    }
}