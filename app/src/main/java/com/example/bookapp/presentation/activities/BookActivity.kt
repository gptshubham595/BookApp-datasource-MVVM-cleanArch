package com.example.bookapp.presentation.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookapp.R
import com.example.bookapp.databinding.ActivityBookBinding
import com.example.bookapp.presentation.fragments.BookFragment

class BookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intFragments()
    }


    private fun intFragments() {
        val bookFragment = BookFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainer, bookFragment)
        transaction.addToBackStack("BookFragment")
        transaction.commit()
    }


}