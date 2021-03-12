package com.info.dictionaryapp

import java.util.*

class KelimelerDao {

    fun tumKelimeler(vt: Veritabani): ArrayList<Kelimeler> {
        val kelimelerArrayList = ArrayList<Kelimeler>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT * FROM kelimeler", null)
        while (c.moveToNext()) {
            val k = Kelimeler(
                c.getInt(c.getColumnIndex("kelime_id"))
                , c.getString(c.getColumnIndex("ingilizce"))
                , c.getString(c.getColumnIndex("turkce"))
            )
            kelimelerArrayList.add(k)
        }
        return kelimelerArrayList
    }

    fun kelimeAra(vt: Veritabani, aramaKelime: String): ArrayList<Kelimeler> {
        val kelimelerArrayList = ArrayList<Kelimeler>()
        val db = vt.writableDatabase
        val c = db.rawQuery(
            "SELECT * FROM kelimeler WHERE ingilizce like '%$aramaKelime%'",
            null
        )
        while (c.moveToNext()) {
            val k = Kelimeler(
                c.getInt(c.getColumnIndex("kelime_id"))
                , c.getString(c.getColumnIndex("ingilizce"))
                , c.getString(c.getColumnIndex("turkce"))
            )
            kelimelerArrayList.add(k)
        }
        return kelimelerArrayList
    }
}