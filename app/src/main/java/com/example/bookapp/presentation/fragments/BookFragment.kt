package com.example.bookapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentBookBinding
import com.example.bookapp.presentation.viewModels.BookViewModel
import java.util.zip.Inflater

class BookFragment : Fragment() {

    private lateinit var viewModel: BookViewModel
    private lateinit var binding: FragmentBookBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val layout = inflater.inflate(R.layout.fragment_book, container, false)
        binding = FragmentBookBinding.bind(layout)

        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[BookViewModel::class.java]
        initView()
        initListener()
        initObserver()
    }

    private fun initObserver() {
        viewModel.bookList.observe(requireActivity(), Observer {
            binding.tvFragment.text = it[0].author
        })
    }

    private fun initListener() {
        viewModel.getBooks()
    }

    private fun initView() {

    }
}