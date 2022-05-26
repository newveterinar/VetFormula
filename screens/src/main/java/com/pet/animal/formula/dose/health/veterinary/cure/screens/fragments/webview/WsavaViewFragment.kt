package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentWebViewWasaBinding

class WsavaViewFragment :
    BaseFragment<FragmentWebViewWasaBinding>(FragmentWebViewWasaBinding::inflate) {

    private lateinit var viewModel: WsavaViewFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        webViewSetup()
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup() {
        binding.webViewFragmentWasa.webViewClient = WebViewClient()
        binding.webViewFragmentWasa.apply {
            loadUrl("https://wsava.org/")
            settings.javaScriptEnabled = true
            settings.allowContentAccess = true

        }
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(WsavaViewFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): WsavaViewFragment = WsavaViewFragment()
    }
}