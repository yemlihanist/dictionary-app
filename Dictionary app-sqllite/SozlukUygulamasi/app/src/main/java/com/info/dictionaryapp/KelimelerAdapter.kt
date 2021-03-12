package com.info.dictionaryapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.info.dictionaryapp.KelimelerAdapter.CardTasarimTutucu

class KelimelerAdapter(
    private val mContext: Context,
    private val kelimelerListe: List<Kelimeler>) : RecyclerView.Adapter<CardTasarimTutucu>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_tasarim, parent, false)
        return CardTasarimTutucu(view)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val kelime = kelimelerListe.get(position)
        holder.textViewIngilizce.text = kelime.ingilizce
        holder.textViewTurkce.text = kelime.turkce

        holder.kelime_card.setOnClickListener {
            val intent = Intent(mContext, DetayActivity::class.java)
            intent.putExtra("nesne", kelime)
            mContext.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return kelimelerListe.size
    }

    inner class CardTasarimTutucu(itemView: View) : ViewHolder(itemView) {
         var textViewIngilizce: TextView
         var textViewTurkce: TextView
         var kelime_card: CardView

        init {
            textViewIngilizce = itemView.findViewById(R.id.textViewIngilizce)
            textViewTurkce = itemView.findViewById(R.id.textViewTurkce)
            kelime_card = itemView.findViewById(R.id.kelime_card)
        }
    }

}