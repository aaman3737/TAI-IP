package com.example.myapplication


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class TaskAdapter(context: Context, tasks: ArrayList<Pair<String, String>>) :
    ArrayAdapter<Pair<String, String>>(context, 0, tasks) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false)
        }

        val task = getItem(position)
        val titleTextView = itemView!!.findViewById<TextView>(android.R.id.text1)
        val descriptionTextView = itemView.findViewById<TextView>(android.R.id.text2)

        titleTextView.text = task?.first
        descriptionTextView.text = task?.second

        return itemView
    }
}
