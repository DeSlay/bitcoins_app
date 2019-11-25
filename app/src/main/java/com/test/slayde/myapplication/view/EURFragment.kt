package com.test.slayde.myapplication.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.test.slayde.myapplication.R
import com.test.slayde.myapplication.model.Devise
import com.test.slayde.myapplication.model.RestBitcoinResponse
import kotlinx.android.synthetic.main.fragment_eur.*

class EURFragment : Fragment() {

    //TODO Voir ajouter des arguments dans un fragment via bundle

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return LayoutInflater.from(container?.context).inflate(R.layout.fragment_eur,container,false)
    }
}
