package com.example.jose

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.jose.databinding.ActivityHomeBinding
import com.example.jose.databinding.ActivityMainBinding
import org.json.JSONObject

class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding;

    private lateinit var userArraylist: ArrayList<User>;

     lateinit var userArraylistdata: ArrayList<User>;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val urlapi = "https://medical-0pb7.onrender.com/api/patientlist"
        val data: JSONObject = JSONObject();
//        data.put("name",loginkey.toString())
//        data.put("password",password.text.toString())
        userArraylistdata = ArrayList()

        val reQueue: RequestQueue = Volley.newRequestQueue(this);
        val request = JsonObjectRequest(Request.Method.POST, urlapi, data, { response ->
//            name.text=response.getString("name")

            val jsonarray = response.getJSONArray("patient")
            for (i in 0 until jsonarray.length()) {
                val resobj = jsonarray.getJSONObject(i)
                val user =
                    User(resobj.getString("name"), resobj.getString("id"), resobj.getString("name"))
                userArraylistdata.add(user)
                Log.d("volley", resobj.getString("name"))
            }
            loadData(userArraylistdata)

            Log.d("message12345", userArraylistdata.toString())
//             userArraylistdata = response.getString("patient")
        }, { error ->

        })
        reQueue.add(request)
    }
    
    fun loadData(userdata:ArrayList<User>){
        binding.listview.isClickable = true
        binding.listview.adapter = myAdapter(this, userArraylistdata)
        binding.listview.setOnItemClickListener { parent, view, position, id ->
//            val name = resobj.getString("name")[position]
//            val id = resobj.getString("id")[position]
            val profile = Intent(this, Profile::class.java)
            profile.putExtra("name", "name")
            profile.putExtra("phone", "id")
            startActivity(profile)

        }
    }

//
//            val name= arrayOf("goutham","akhil","indudar","kiran","satya")
//            val phone= arrayOf("0123456789","0123456789","0123456789","0123456789","0123456789")
//            val status= arrayOf("Active","Active","InActive","InActive","Active")
//        val type = intent.getStringExtra("type")
//
//            userArraylist = ArrayList()
//            for( i in name.indices){
//                if(type=="add") {
//                    if (status[i] == "Active") {
//                        val user = User(name[i], phone[i], status[i])
//                        Log.d("message1234",user.toString())
//                        userArraylist.add(user)
//                        Log.d("message1234",userArraylist.toString())
//
//                    }
//                }else if(type=="all") {
//                        val user = User(name[i], phone[i], status[i])
//                        userArraylist.add(user)
//                }
//            }


//        val listview = findViewById<ListView>(R.id.listview)
//        val adapter: ArrayAdapter<User> = ArrayAdapter(this,android.R.layout.simple_list_item_1,userArraylist)






    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId ();
        if(id == R.id. navigation_home){
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = getMenuInflater();
        inflater.inflate(R.menu.top_nav_menu,menu)
        return true
    }
}