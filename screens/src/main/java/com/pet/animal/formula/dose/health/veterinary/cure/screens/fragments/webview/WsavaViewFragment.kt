package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.webview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentWebViewWasaBinding

class WsavaViewFragment :
    BaseFragment<FragmentWebViewWasaBinding>(FragmentWebViewWasaBinding::inflate) {

    private val loadUrl = "https://wsava.org/"
    private lateinit var viewModel: WsavaViewFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        webViewSetup()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup() {
        with(binding) {
            webViewFragmentWasa.webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    view?.visibility = View.INVISIBLE
                    progressbarWsava.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    view?.visibility = View.VISIBLE
                    progressbarWsava.visibility = View.INVISIBLE
                }
            }
            webViewFragmentWasa.apply {
                loadUrl(loadUrl)
                settings.javaScriptEnabled = true
                settings.allowContentAccess = true
            }
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