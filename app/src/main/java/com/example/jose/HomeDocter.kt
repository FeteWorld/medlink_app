package com.example.jose

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class HomeDocter : AppCompatActivity() {
    val shared_key="shared_key"
    private lateinit var sharedPreferences: SharedPreferences
    private  var loginkey:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_docter)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sharedPreferences =getSharedPreferences(shared_key, Context.MODE_PRIVATE)
        loginkey = sharedPreferences.getString("shared_key",null)
        val name=findViewById<TextView>(R.id.textView)


        val urlapi ="https://medical-0pb7.onrender.com/api/doctershome"
        val data : JSONObject =  JSONObject();
        data.put("name",loginkey.toString())
//        data.put("password",password.text.toString())

        val reQueue : RequestQueue = Volley.newRequestQueue(this);
        val request  =  JsonObjectRequest(Request.Method.GET,urlapi,data , { response ->
            name.text=response.getString("name")
        },{ error ->

        })
        reQueue.add(request)

        val pending=findViewById<TextView>(R.id.textview1)
        val patient=findViewById<TextView>(R.id.textview2)

        pending.text="Add Prescription : 2"
        patient.text="No of Patient : 10"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_nav_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId ();
        if(id == R.id. settings){
            val home= Intent(this,settings::class.java)
            startActivity(home);
            return true;
        }
        return super.onOptionsItemSelected(item)
    }

    fun patients(view : View){
        val intent = Intent(this,Home::class.java)
        intent.putExtra("type","all")
        startActivity(intent)
    }
    fun addPrescription(view : View){
        val intent = Intent(this,Home::class.java)
        intent.putExtra("type","add")
        startActivity(intent)
    }
}