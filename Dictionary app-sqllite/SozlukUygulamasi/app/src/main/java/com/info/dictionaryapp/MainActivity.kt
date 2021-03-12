package com.info.dictionaryapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener {

    private lateinit var kelimelerListe: ArrayList<Kelimeler>
    private lateinit var adapter: KelimelerAdapter
    private lateinit var vt: Veritabani

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.setTitle("Sözlük Uygulaması")
        setSupportActionBar(toolbar)

        vt = Veritabani(this)

        veritabaniKopyala()

        rv.setHasFixedSize(true)
        rv.setLayoutManager(LinearLayoutManager(this))

        kelimelerListe = KelimelerDao().tumKelimeler(vt)

        adapter = KelimelerAdapter(this, kelimelerListe)

        rv.setAdapter(adapter)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        Log.e("Gönderilen arama", query)
        aramaYap(query)
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        Log.e("Harf girdikçe", newText)
        aramaYap(newText)
        return false
    }

    fun veritabaniKopyala() {
        val copyHelper = DatabaseCopyHelper(this)
        try {
            copyHelper.createDataBase()

            copyHelper.openDataBase()

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun aramaYap(aramaKelime: String) {
        kelimelerListe = KelimelerDao().kelimeAra(vt, aramaKelime)
        adapter = KelimelerAdapter(this, kelimelerListe)
        rv.adapter = adapter
    }

}
