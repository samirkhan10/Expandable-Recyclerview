package com.example.demoapktask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

     var morning_counter:Int = 0
     var all_counter:Int = 0
     var nonn_counter:Int = 0
     var evening_counter:Int = 0
    lateinit var rec: RecyclerView
    var myData = ArrayList<com.example.demoapktask.Response>()
    var morningSlots = ArrayList<com.example.demoapktask.Response>()
    var afterNoonSlot = ArrayList<com.example.demoapktask.Response>()
    var EveningSlots = ArrayList<com.example.demoapktask.Response>()
    var allSlots = ArrayList<com.example.demoapktask.Response>()
    var slots = ArrayList<ExpandableModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rec = findViewById(R.id.rec)
        rec.layoutManager = LinearLayoutManager(this)
        fetchData()
        val myadaptor = myAdaptor(myData)
        rec.adapter = myadaptor

    }

    // here i am getting data from API
    private fun fetchData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.r-ims.com/")
            .build()
            .create(myInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<myModel> {
            override fun onResponse(
                call: Call<myModel>,
                response: Response<myModel>
            ) {
                val body = response.body()
//                Toast.makeText(
//                    applicationContext,
//                    "response" + response.toString(),
//                    Toast.LENGTH_LONG
//                ).show()
                Log.d("respons", body.toString())
                val obj = body
                if (obj != null) {
                    if (obj.response.size > 0) {
                        myData = obj.response
                        sortingArray(myData)
                    }
                }

                //  mtext.setText(body.toString())
            }

            override fun onFailure(call: Call<myModel>, t: Throwable) {
                Log.d("error", t.toString())
                Toast.makeText(applicationContext, "fail" + t, Toast.LENGTH_LONG).show()
                //  mtext.setText(t.toString())
            }
        })

    }

    //sorting date  by sequence (morning,afternoon,Evening and all)
    fun sortingArray(response: ArrayList<com.example.demoapktask.Response>) {
        for (res in response) {
            if (Integer.parseInt(res.slot_start_time.substring(0, 2)) in 7..11) {
                morningSlots.add(res)
                if (res.status==1){
                    morning_counter++
                }
            } else if (Integer.parseInt(res.slot_start_time.substring(0, 2)) in 12..14) {
                afterNoonSlot.add(res)
                if (res.status==1){
                    nonn_counter++
                }
            } else if (Integer.parseInt(res.slot_start_time.substring(0, 2)) in 15..17) {
                EveningSlots.add(res)
                if (res.status==1){
                    evening_counter++
                }
            }
            if (Integer.parseInt(res.slot_start_time.substring(0, 2)) in 6..19) {
                allSlots.add(res)
                if (res.status==1){
                    all_counter++
                }
            }
        }
        // adding all timimg arrayList into Slots adaptor
        slots.add(ExpandableModel("Morning Slots", morningSlots,morning_counter))
        slots.add(ExpandableModel("AfterNoon Slots", afterNoonSlot,nonn_counter))
        slots.add(ExpandableModel("Evening Slots", EveningSlots,evening_counter))
        slots.add(ExpandableModel("All", allSlots,all_counter))


      //  Log.d("morningSlots",morningSlots.toString())

        // setting the adaptor to expandable recyclerView
        val expandableAdaptor = ExpandableAdaptor(this, slots)
        val linearLayoutManager = LinearLayoutManager(this)
        rec.layoutManager = linearLayoutManager
        rec.adapter = expandableAdaptor


      //  Log.d("afternoon", slots[1].toString())
    }

}