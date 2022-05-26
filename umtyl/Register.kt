package com.example.umtyl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.umtyl.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etName: EditText = findViewById(R.id.fullnameEt)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView5.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        binding.createBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passwordEt.text.toString()
            val confirmpass = binding.confpasswordEt.text.toString()
            //val fullname = binding.fullnameEt.text.toString()

            if(email.isEmpty() || pass.isEmpty() || confirmpass.isEmpty()){
                Toast.makeText(this, "Fill all the gaps!", Toast.LENGTH_SHORT).show()
            }
            else if(pass != confirmpass){
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
            }
            else {
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this, Home::class. java)
                        intent.putExtra(Constants.USER_NAME, etName.text.toString())
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }
}