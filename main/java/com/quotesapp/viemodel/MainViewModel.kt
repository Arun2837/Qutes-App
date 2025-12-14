package com.quotesapp.viemodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.quotesapp.data.Quotes
import java.nio.charset.Charset

class MainViewModel(val context: Context): ViewModel() {
    private var quoteList: Array<Quotes> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuoteFromAsset()
    }

    fun loadQuoteFromAsset(): Array<Quotes> {
        val inputStream = context.assets.open("hindi.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quotes>::class.java)
    }

    fun getQuote() = quoteList[index]

    fun nextQuote() = quoteList[++index]

    fun previousQuote() = quoteList[--index]
}