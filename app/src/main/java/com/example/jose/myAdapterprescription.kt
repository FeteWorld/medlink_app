package com.example.jose;

import android.app.Activity;
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class myAdapterprescription( val c:Context , val  arrayList:ArrayList<prescriptionobj>) :
    RecyclerView.Adapter<myAdapterprescription.UserAdapter>() {

        inner class UserAdapter(val v : View):RecyclerView.ViewHolder(v){

            val name : TextView
            val button : ImageView

            init {
                 name = v.findViewById<TextView>(R.id.textViewadd)
                 button = v.findViewById<ImageView>(R.id.button11)
                button.setOnClickListener{ deleteitem()}
            }
            private fun deleteitem(){
                arrayList.removeAt(adapterPosition)
                notifyDataSetChanged()
                AlertDialog.Builder(c)
            }
        }

//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//
//        val inflater : LayoutInflater = LayoutInflater.from(context)
//        val view : View =inflater.inflate(R.layout.prescriptionlistview, null)
//
//        val name : TextView = view.findViewById(R.id.textViewadd)
//        val delete : TextView = view.findViewById(R.id.button11)
//
//        name.text= arrayList[position].name
//
//        return view
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val  view =inflater.inflate(R.layout.prescriptionlistview,parent,false )
        return UserAdapter(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: UserAdapter, position: Int) {
        val newlist = arrayList[position]
        holder.name.text=newlist.name
    }
}
