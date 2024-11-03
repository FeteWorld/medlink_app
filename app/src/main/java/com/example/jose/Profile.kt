package com.example.jose

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name =intent.getStringExtra("name")
        val phone =intent.getStringExtra("phone")
        val profilename = findViewById<TextView>(R.id.textView)
        val profilephone = findViewById<TextView>(R.id.textView2)

        profilename.text= "Name :" +name
        profilephone.text= "Phone No :"+phone
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = getMenuInflater();
        inflater.inflate(R.menu.top_nav_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId ();
        if(id == R.id.navigation_home){
            val home= Intent(this,HomeDocter::class.java)
            startActivity(home);
            return true;
        }
        if(id == R.id. settings){
            val home= Intent(this,settings::class.java)
            startActivity(home);
            return true;
        }
        return super.onOptionsItemSelected(item)
    }

    fun viewp(view: View){
        val intent = Intent(this,AddPrescription::class.java)
        startActivity(intent)
    }
}