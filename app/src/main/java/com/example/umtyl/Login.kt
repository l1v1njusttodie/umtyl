package com.example.umtyl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.umtyl.databinding.ActivityHomeBinding.inflate
import com.example.umtyl.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.createTw.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        binding.LoginBtn.setOnClickListener{
            val email = binding.emailEt.text.toString()
            val pass = binding.passwordEt.text.toString()

            if(email.isEmpty() || pass.isEmpty()){
                Toast.makeText(this, "Fill all the gaps!", Toast.LENGTH_SHORT).show()
            }
            else {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this, Home::class. java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, "This account does not exists", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }
}