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



class MainActivity : AppCompatActivity() {

    private lateinit var restResponse : RestBitcoinResponse

    val URL = "https://api.coindesk.com/v1/bpi/currentprice.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //goTo(EURFragment())
        loadData()

        buttonEUR.setOnClickListener{
            goTo(restResponse.bpi.EUR)

        }

        buttonUSD.setOnClickListener {
            goTo(restResponse.bpi.USD)
        }

    }

    private fun goTo(devise: Devise) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, EURFragment())
        ft.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.Load -> {
                loadData()
                return true

            }
        }
        return super.onOptionsItemSelected(item)
    }



    private fun loadData() {
        runOnUiThread() {
            progressBar.visibility = View.VISIBLE
        }

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.coindesk.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val bitcoinApi = retrofit.create(BitcoinApi::class.java)
        bitcoinApi.getCurrentPrice().enqueue(object : Callback<RestBitcoinResponse> {
            override fun onFailure(call: retrofit2.Call<RestBitcoinResponse>?, t: Throwable?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: retrofit2.Call<RestBitcoinResponse>?,
                response: retrofit2.Response<RestBitcoinResponse>
            ) {
                restResponse = response.body()
                goTo(restResponse.bpi.EUR)
                }
        })
    }
}
