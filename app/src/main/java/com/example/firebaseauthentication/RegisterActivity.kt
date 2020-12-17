package com.example.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException as FirebaseAuthException1

class RegisterActivity : AppCompatActivity() {
    private lateinit var inputEmail : EditText
    private lateinit var inputPass : EditText
    private lateinit var submitbtn : Button
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        inputEmail= findViewById(R.id.editTextTextEmailAddress)
        inputPass=findViewById(R.id.editTextTextPassword)
        submitbtn=findViewById(R.id.button)
        mAuth= FirebaseAuth.getInstance()
        submitbtn.setOnClickListener{
            val email = inputEmail.text.toString()
            val pass = inputPass.text.toString()
            if(email.isEmpty() || pass.isEmpty() ){
                Toast.makeText(this,"Please Write Both Password and Email",Toast.LENGTH_SHORT).show()
                Toast.makeText(this,email+pass,Toast.LENGTH_SHORT).show()

            }else {
                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(){task ->
                    if(task.isSuccessful){
                        //
                    }else{
                        val  e = task.exception;
                        Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show()

                    }


                }
                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(){task ->
                    if(task.isSuccessful){
                        startActivity(Intent(this,MainActivity::class.java))

                    }else {
                        Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }





    }
}