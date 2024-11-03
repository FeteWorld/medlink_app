package com.example.jose

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jose.databinding.ActivityAddPrescriptionBinding
import com.example.jose.databinding.ActivityHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddPrescription : AppCompatActivity() {

//    private lateinit var addbutton: FloatingActionButton;
    private lateinit var userArraylist: ArrayList<prescriptionobj>;
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: myAdapterprescription

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        binding = ActivityAddPrescriptionBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_add_prescription)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        userArraylist = ArrayList()
        userAdapter= myAdapterprescription(this, userArraylist)
        recyclerView=findViewById(R.id.recycleview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter= userAdapter

        val  addbutton = findViewById<Button>(R.id.button7)
        val problem=findViewById<TextView>(R.id.editTextText3)
//        binding.listviewp.adapter = myAdapterprescription(this,userArraylist)

        problem.text="Patient has could and fever"
        addbutton.setOnClickListener{ addnewp()}
    }

    fun addnewp(){
        val inflater = LayoutInflater.from(this);
        val view = inflater.inflate(R.layout.new_prescription,null)
        val name = view.findViewById<EditText>(R.id.editTextText2)

        val add =AlertDialog.Builder(this)
        add.setView(view);
        add.setPositiveButton("ok"){
            dialog, which->
            val nameadd=name.text.toString()

            val user = prescriptionobj(nameadd)
            userArraylist.add(prescriptionobj(nameadd))
            userAdapter.notifyDataSetChanged()
            dialog.dismiss()
           // binding.listviewp.adapter = myAdapterprescription(this,userArraylist)
        }
        add.setNegativeButton("cancel") {
                dialog, which->
        }
        add.create()
        add.show()
    }

    fun submitmedication(view : View){
        val toast = Toast.makeText(this,userArraylist.toString(),Toast.LENGTH_SHORT)
        toast.show()
    }

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