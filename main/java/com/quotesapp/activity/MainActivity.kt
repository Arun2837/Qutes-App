package com.quotesapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.quotesapp.R
import com.quotesapp.data.Quotes
import com.quotesapp.databinding.ActivityMainBinding
import com.quotesapp.viemodel.MainViewModel
import com.quotesapp.viewmodelfactory.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = MainViewModelFactory(application)
        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        setQuote(mainViewModel.getQuote())

        binding.nextArrow.setOnClickListener {
            setQuote(mainViewModel.nextQuote())
        }

        binding.previousArrow.setOnClickListener {
            setQuote(mainViewModel.previousQuote())
        }

        //Log.d("TEST_JSON", mainViewModel.getQuote().description)

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().description)
            startActivity(intent)
        }
    }

    fun setQuote(quotes: Quotes){
        binding.quoteTitle.text = quotes.description
        binding.quoteAuthor.text = quotes.author
    }
}