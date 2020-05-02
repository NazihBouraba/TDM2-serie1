package com.example.exo3

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast

class InterventionListAdapter(private val _ctx: Context, rId: Int, private val produits: MutableList<InterventionEntity>) : ArrayAdapter<InterventionEntity>(_ctx, rId, produits) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = _ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.intervention_item, parent, false)
        val txtnumero = rowView.findViewById<TextView>(R.id.txtnumero)
        val txtdate = rowView.findViewById<TextView>(R.id.txtdate)
        val txtnom_p = rowView.findViewById<TextView>(R.id.txtnom_p)
        val type = rowView.findViewById<TextView>(R.id.txttype)
        var btnEdit = rowView.findViewById<TextView>(R.id.btnEdit)
        var btnRemove = rowView.findViewById<TextView>(R.id.btnRemove)
        val p = produits[position]
        txtnumero.text = p.numero.toString()
        txtdate.text = p.date
        txtnom_p.text = p.npm_plombier
        type.text= p.type
        btnEdit.setOnClickListener({
            val intent = Intent(_ctx, EditActivity::class.java)
            intent.putExtra("mode", "modif")
            intent.putExtra("numero", p.numero)
            _ctx.startActivity(intent)
        })
        btnRemove.setOnClickListener({
            supprimerProduit(_ctx, p)
        })


        return rowView
    }

    fun supprimerProduit(ctx: Context, p: InterventionEntity) {
        var adapter = this
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                val db = InterventionDB.getInstance(ctx)
                val dao = db?.InterventionDAO()
                dao?.supprimer(p)
                return null
            }


            override fun onPostExecute(result: Void?) {
                Toast.makeText(_ctx,"intervention supprimée",Toast.LENGTH_SHORT).show()
                adapter.remove(p)

            }
        }.execute()
    }

}