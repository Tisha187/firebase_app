package com.example.firebase

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Intents
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text
import java.lang.Double.valueOf

class registration : AppCompatActivity() {
    lateinit var edittextemail: TextInputEditText
    lateinit var edittextpassword:TextInputEditText
    lateinit var button: Button
    lateinit var mAuth: FirebaseAuth
    lateinit var progreesbar:ProgressBar
    lateinit var TextView:TextView


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        mAuth=FirebaseAuth.getInstance()
        edittextemail=findViewById<TextInputEditText>(R.id.Email)
        edittextpassword=findViewById<TextInputEditText>(R.id.Password)
        button=findViewById<Button>(R.id.button2)
        progreesbar=findViewById<ProgressBar>(R.id.progress_bar)
        TextView=findViewById<TextView>(R.id.Registernow)
        TextView.setOnClickListener { newActiviy() }

        button.setOnClickListener{ whenclicked()}
    }

    fun newActiviy(){
        val intent =Intent(this, login::class.java)
        startActivity(intent)
        finish()
    }

    fun whenclicked(){
        var Email=(edittextemail.text.toString())
        var password= (edittextpassword.text.toString())
        progreesbar.visibility= View.VISIBLE

        if(TextUtils.isEmpty(Email.toString())){
            Toast.makeText( this@registration,"please Enter email", Toast.LENGTH_SHORT).show()
            return
        }

        if(TextUtils.isEmpty(password.toString())){
            Toast.makeText( this@registration,"please Enter your password", Toast.LENGTH_SHORT).show()
            return
        }

        mAuth.createUserWithEmailAndPassword(Email.toString(), password.toString())
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {
                    progreesbar.visibility=View.GONE
                    Toast.makeText(
                        registration@this,
                        "you have registered",
                        Toast.LENGTH_SHORT,
                    ).show()

                    val user = mAuth.currentUser

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(
                        registration@this,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()

                }
            }

    }
}