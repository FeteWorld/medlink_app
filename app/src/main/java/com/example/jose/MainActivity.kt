package com.example.jose

import android.content.Context
import android.content.ContextParams
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    val shared_key="shared_key"
    val type="type"

    lateinit var nameEdt: EditText
    lateinit var jobEdt: EditText
    private lateinit var sharedPreferences: SharedPreferences
    private  var loginkey:String? = null
    private  var typekey:String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferences =getSharedPreferences(shared_key, Context.MODE_PRIVATE)
        loginkey = sharedPreferences.getString("shared_key",null)
        typekey = sharedPreferences.getString("type",null)

//        getdata();
//        postdata();
    }

    override fun onStart() {
        super.onStart()
        Log.d("msg",loginkey.toString())
        if(loginkey != null){
            if(typekey =="patient") {
                val home = Intent(this, HomePatient::class.java)
                startActivity(home);
            }else if(typekey =="docter") {
                val home = Intent(this, HomeDocter::class.java)
                startActivity(home);
            }else if(typekey =="pharmacy") {
                val home = Intent(this, HomePharmacy::class.java)
                startActivity(home);
            }
        }
    }
    private fun getdata(){
        val name="All"
        val urlapi ="https://medical-0pb7.onrender.com/api/data"


        val reQueue : RequestQueue= Volley.newRequestQueue(this)
        val request  = JsonObjectRequest(Request.Method.GET,urlapi,null,{ result ->
            val jsonarray =result.getJSONArray("users")
            for(i in 0 until jsonarray.length()){
                val resobj = jsonarray.getJSONObject(i)
                Log.d("volley",resobj.getString("name"))
            }
        },{err->
            Log.d("volley error",err.message.toString())

        })
        reQueue.add(request)

        Toast.makeText(this,"0000",Toast.LENGTH_SHORT).show()
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val inflater :MenuInflater = getMenuInflater();
//        inflater.inflate(R.menu.top_nav_menu,menu)
//        return true
//    }

    fun signina(view: View){
        val name = findViewById<EditText>(R.id.editTextText11)
        if(name.text.toString()=="goutham"){
            val home= Intent(this,HomeDocter::class.java)
            startActivity(home);
        } else if(name.text.toString()=="akhil"){
            val home= Intent(this,HomePharmacy::class.java)
            startActivity(home);
        }else if(name.text.toString()=="indudar"){
            val home= Intent(this,HomePatient::class.java)
            startActivity(home);
        }else if(name.text.toString()==""){
            val home= Intent(this,HomeDocter::class.java)
            startActivity(home);
        }

    }
    fun settings(view: View){
        val settings = Intent(this,settings::class.java)
        startActivity(settings);
    }

    fun signup(view: View){
        val signup = Intent(this,signup::class.java)
        startActivity(signup);
    }

     fun signin(view : View){
        val name = findViewById<EditText>(R.id.editTextText11)
        val password = findViewById<EditText>(R.id.editTextText12)

        val urlapi ="https://medical-0pb7.onrender.com/api/signin"
        val data :JSONObject =  JSONObject();
        data.put("name",name.text.toString())
        data.put("password",password.text.toString())

        val reQueue :RequestQueue = Volley.newRequestQueue(this);
        val request  =  JsonObjectRequest(Request.Method.POST,urlapi,data , { response ->

            if(response.getString("login") =="successful") {
                val editor = sharedPreferences.edit()
                editor.putString(shared_key,response.getString("id"))
                editor.putString(type,response.getString("type"))

                editor.apply()
                if (response.getString("type") == "docter") {
                    val home = Intent(this, HomeDocter::class.java)
                    startActivity(home);
                } else if (response.getString("type") == "pharmacy") {
                    val home = Intent(this, HomePharmacy::class.java)
                    startActivity(home);
                } else if (response.getString("type") == "patient") {
                    val home = Intent(this, HomePatient::class.java)
                    startActivity(home);
                }
            }else {
                Toast.makeText(this,"Please enter valid Email and Password",Toast.LENGTH_SHORT).show()

            }
        },  {error->
            Toast.makeText(this,"Please enter valid Email and Password",Toast.LENGTH_SHORT).show()
        })
        reQueue.add(request)

    }
}