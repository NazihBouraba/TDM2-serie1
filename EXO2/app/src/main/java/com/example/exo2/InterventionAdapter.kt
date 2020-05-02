package com.example.exo2

import android.R.array
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.intervention_item.view.*
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class InterventionAdapter (ls : ArrayList<Intervention> , cont : Context) : RecyclerView.Adapter<ViewHolder>()  {


    var liste_intevention = ls
    val context = cont

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val ite = layoutInflater.inflate(R.layout.intervention_item, parent, false)
        return ViewHolder(ite)
    }

    override fun getItemCount(): Int {
       return liste_intevention.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.numero_tv.text = liste_intevention[position].numero.toString()
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        holder.itemView.date_tv.text = sdf.format(liste_intevention[position].date.getTime())
        holder.itemView.nom_plombier_tv.text = liste_intevention[position].NomPlombier
        holder.itemView.type_tv.text = liste_intevention[position].type

        // des quand clicke sur une intervention on va lance une activité pour la modifier
        //on doit envoyer toute la liste en extra puis modification activity va mettre a jour
        // le fichier qui contient toutes les activitée


        holder.itemView.setOnClickListener(){

         var intent = Intent(context,Modify::class.java)
         var bundle = Bundle()
          bundle.putSerializable("Liste", liste_intevention as Serializable? )
            intent.putExtra("position",position)
          intent.putExtras(bundle)
          context.startActivity(intent)


        }

    }
}