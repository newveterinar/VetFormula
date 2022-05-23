package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentWebViewBinding

class WebViewFragment : BaseFragment<FragmentWebViewBinding>(FragmentWebViewBinding::inflate) {

    private lateinit var viewModel: WebViewFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        webViewSetup()
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup() {
        binding.webViewFragment.webViewClient = WebViewClient()
        binding.webViewFragment.apply {
            loadUrl("https://www.google.ru/")
            settings.javaScriptEnabled = true
        }
    }


    // Инициализация ViewModel
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(WebViewFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): WebViewFragment = WebViewFragment()
    }
}