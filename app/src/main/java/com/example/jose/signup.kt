package com.example.jose

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun signup(view : View){
        val name = findViewById<EditText>(R.id.editTextText4)
        val email = findViewById<EditText>(R.id.editTextText6)
        val phoneNo = findViewById<EditText>(R.id.editTextText7)
        val address = findViewById<EditText>(R.id.editTextText8)

        val urlapi ="https://medical-0pb7.onrender.com/api/signup"
        val data : JSONObject =  JSONObject();
        data.put("name",name.text.toString())
        data.put("email",email.text.toString())
        data.put("phoneNo",phoneNo.text.toString())
        data.put("address",address.text.toString())

        val reQueue : RequestQueue = Volley.newRequestQueue(this);
        val request  =  JsonObjectRequest(Request.Method.POST,urlapi,data , { response ->
            if(response.getString("login") =="successful") {
                val login = Intent(this, MainActivity::class.java)
                startActivity(login);
            }
        },{error ->

        })
        reQueue.add(request)

    }

    fun signin(view: View){
        val login = Intent(this, MainActivity::class.java)
        startActivity(login);
    }
    }