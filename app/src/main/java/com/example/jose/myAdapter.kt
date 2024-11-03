package com.example.jose;

import android.app.Activity;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.jose.R


class myAdapter(private val context :Activity, private val arrayList:ArrayList<User>) :
    ArrayAdapter<User>(context, R.layout.listview,arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View =inflater.inflate(R.layout.listview, null)

        val name : TextView = view.findViewById(R.id.textView3)
        val status : TextView = view.findViewById(R.id.textView4)

        name.text= arrayList[position].name
        status.text= arrayList[position].status

        return view
    }
}
