package com.info.dictionaryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.card_tasarim.*

class DetayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detay)

        val kelime = intent.getSerializableExtra("nesne") as Kelimeler

        textViewIngilizce.text = kelime.ingilizce
        textViewTurkce.text = kelime.turkce
    }
}
