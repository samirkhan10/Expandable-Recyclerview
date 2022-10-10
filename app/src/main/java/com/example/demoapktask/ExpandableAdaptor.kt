package com.example.demoapktask

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ExpandableAdaptor(val context: Context, val myList: ArrayList<ExpandableModel>) :
    RecyclerView.Adapter<ExpandableAdaptor.ViewHolder>() {
    lateinit var myAdaptor: myAdaptor
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val View = LayoutInflater.from(parent.context)
            .inflate(R.layout.expandable_rec_item, parent, false)
        return ViewHolder(View)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // here when i  clicked Expended Rec will show
        if (myList[position].isExpanded) {
            holder.rec_new.visibility = View.VISIBLE
            holder.availableSlots.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_baseline_arrow_upward_24,
                0
            )
        } else {
            holder.rec_new.visibility = View.GONE
            holder.availableSlots.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_baseline_arrow_downward_24,
                0
            )
        }
        holder.linear.setOnClickListener {
            myList[position].isExpanded = !myList[position].isExpanded  //here we are getting inverted property of expended(Returns the inverse of this boolean)
            notifyDataSetChanged()
        }

        if (myList[position].data.size > 0) {
            holder.title_new.setText(myList[position].title)
            holder.availableSlots.setText("${myList[position].availableSlots} Slots Available")
            myAdaptor = com.example.demoapktask.myAdaptor(myList[position].data)
            linearLayoutManager = LinearLayoutManager(context)
            holder.rec_new.layoutManager = linearLayoutManager
            holder.rec_new.adapter = myAdaptor
        }

    }


    override fun getItemCount(): Int {
        return myList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val availableSlots: TextView = itemView.findViewById(R.id.available_slots)
        val title_new: TextView = itemView.findViewById(R.id.tv)
        val rec_new: RecyclerView = itemView.findViewById(R.id.rec_new)
        val linear: LinearLayout = itemView.findViewById(R.id.root)


    }
}


