package com.example.exo4

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class NoteListAdapter(private val _ctx: Context, rId: Int, private val Notes: MutableList<Note>) : ArrayAdapter<Note>(_ctx, rId, Notes) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = _ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.note_item, parent, false)
        val titre_txt = rowView.findViewById<TextView>(R.id.titre_txt)
        val date_txt = rowView.findViewById<TextView>(R.id.date_txt)
        val contenu_txt = rowView.findViewById<TextView>(R.id.contenu_txt)
        val root = rowView.findViewById<LinearLayout>(R.id.root)
        var btnEdit = rowView.findViewById<TextView>(R.id.btnEdit)
        var btnRemove = rowView.findViewById<TextView>(R.id.btnRemove)
        val p = Notes[position]
        titre_txt.text = p.titre
        date_txt.text = p.date
        contenu_txt.text = p.contenu
        root.setBackgroundColor(p.couleur)

        btnEdit.setOnClickListener({
            val intent = Intent(_ctx, EditActivity::class.java)
            intent.putExtra("mode", "modif")
            intent.putExtra("numero", p.id)
            _ctx.startActivity(intent)
        })
        btnRemove.setOnClickListener({
            supprimerProduit(_ctx, p)
        })


        return rowView
    }


    fun supprimerProduit(ctx: Context, p: Note) {
        var adapter = this
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                val db = NoteDB.getInstance(ctx)
                val dao = db?.NoteDAO()
                dao?.supprimer(p)
                return null
            }


            override fun onPostExecute(result: Void?) {
                Toast.makeText(_ctx,"Note supprimée", Toast.LENGTH_SHORT).show()
                adapter.remove(p)

            }
        }.execute()
    }

}