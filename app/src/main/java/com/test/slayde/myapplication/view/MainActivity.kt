package com.test.slayde.myapplication.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.gson.GsonBuilder
import com.test.slayde.myapplication.BitcoinApi
import com.test.slayde.myapplication.R
import com.test.slayde.myapplication.model.Devise
import com.test.slayde.myapplication.model.RestBitcoinResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.gson.Gson
import com.test.slayde.myapplication.model.time


class MainActivity : AppCompatActivity() {

    private lateinit var restResponse : RestBitcoinResponse
    private lateinit var gson : Gson

    val URL = "https://api.coindesk.com/v1/bpi/currentprice.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonEUR.isEnabled = false
        buttonUSD.isEnabled = false

        loadData()

        buttonEUR.setOnClickListener{
            goTo(restResponse.bpi.EUR)
        }

        buttonUSD.setOnClickListener {
            goTo(restResponse.bpi.USD)
        }

    }

    private fun goTo(devise: Devise) {
        val fragment = EURFragment()
        val args = Bundle()
        args.putString("devise", gson.toJson(devise))
        fragment.arguments = args


        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, fragment)
        ft.commit()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return super.onCreateOptionsMenu(menu)
    }


    private fun loadData() {
        runOnUiThread() {
            progressBar.visibility = View.VISIBLE
        }

        gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.coindesk.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val bitcoinApi = retrofit.create(BitcoinApi::class.java)
        bitcoinApi.getCurrentPrice().enqueue(object : Callback<RestBitcoinResponse> {
            override fun onFailure(call: retrofit2.Call<RestBitcoinResponse>?, t: Throwable?) {
            }

            override fun onResponse(
                call: retrofit2.Call<RestBitcoinResponse>?,
                response: retrofit2.Response<RestBitcoinResponse>
            ) {
                restResponse = response.body()
                goTo(restResponse.bpi.EUR)

                }
        })

        buttonEUR.isEnabled = true
        buttonUSD.isEnabled = true
        progressBar.visibility = View.GONE
    }
}
