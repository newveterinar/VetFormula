package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentWebViewVetmedicalBinding

class VetMedicalViewFragment :
    BaseFragment<FragmentWebViewVetmedicalBinding>(FragmentWebViewVetmedicalBinding::inflate) {

    private lateinit var viewModel: VetMedicalViewFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        webViewSetup()
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup() {
        binding.webViewFragmentVetmedical.webViewClient = WebViewClient()
        binding.webViewFragmentVetmedical.apply {
            loadUrl("https://vetmedical.ru/")
            settings.javaScriptEnabled = true
            settings.allowContentAccess = true

        }
    }


    // Инициализация ViewModel
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(VetMedicalViewFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): VetMedicalViewFragment = VetMedicalViewFragment()
    }
}