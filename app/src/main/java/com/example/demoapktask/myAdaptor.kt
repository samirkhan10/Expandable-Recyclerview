package com.example.demoapktask

import android.R.*
import android.R.color.*
import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapktask.R.color.teal_200
import java.text.SimpleDateFormat

class myAdaptor(val myList: ArrayList<Response>) : RecyclerView.Adapter<myAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val View = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_row, parent, false)
        return ViewHolder(View)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // here i am formatting start date
        var dateFormat24 = SimpleDateFormat("HH:mm")
        var date24 = dateFormat24.parse(myList[position].slot_start_time)
        var dateFormat12 = SimpleDateFormat("h:mma")

        var startTime = dateFormat12.format(date24)


        // here i am formatting end date
        var endDateFormat24 = SimpleDateFormat("HH:mm")
        var endDate24 = endDateFormat24.parse(myList[position].slot_end_time)
        var endDateFormat12 = SimpleDateFormat("h:mma")

        var endTime = endDateFormat12.format(endDate24)

        holder.title.text =
            " ${ startTime } - ${ endTime } "
        if (myList[position].status == 0) {
            holder.card.setBackgroundColor(Color.parseColor("#C0C0C0"))
        } else {
            holder.card.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }



    }

    override fun getItemCount(): Int {
        return myList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val title: TextView = itemView.findViewById(R.id.title)
        val card: CardView = itemView.findViewById(R.id.card)


    }
}


