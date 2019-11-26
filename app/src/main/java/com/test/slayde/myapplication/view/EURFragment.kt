package com.test.slayde.myapplication.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson

import com.test.slayde.myapplication.R
import com.test.slayde.myapplication.model.Devise
import com.test.slayde.myapplication.model.time
import kotlinx.android.synthetic.main.fragment_eur.*

class EURFragment : Fragment() {

    //TODO Voir ajouter des arguments dans un fragment via bundle

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_eur, null)
    }

    override fun onStart() {
        super.onStart()
        val jsonDevise = arguments?.getString("devise")
        val devise = Gson().fromJson(jsonDevise, Devise::class.java)

        //val jsontime = arguments?.getString("time")
        //val time = Gson().fromJson(jsonDevise, time::class.java)

        //txtUpdated.text = time.updated

        txtDevise.text = devise.code
        bitcoinValues.text = devise.rate
        txtDescription.text = devise.description

    }
}
