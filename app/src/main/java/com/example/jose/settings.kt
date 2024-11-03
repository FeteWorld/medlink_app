package com.example.jose

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class settings : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    val shared_key="shared_key"
    private  var loginkey:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sharedPreferences =getSharedPreferences(shared_key, Context.MODE_PRIVATE)
        loginkey = sharedPreferences.getString("shared_key",null)
    }
    fun homepage(view: View){
        val home= Intent(this,HomeDocter::class.java)
        startActivity(home);
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
        return super.onOptionsItemSelected(item)
    }

    fun signout(view:View){
        val editor = sharedPreferences.edit()
        editor.clear();
        editor.apply()

        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent);

    }
}