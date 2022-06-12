package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.webview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentWebViewWasaBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.WSAVA_URL
import com.pet.animal.formula.dose.health.veterinary.cure.utils.settings.SettingsImpl
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class WsavaViewFragment:
    BaseFragment<FragmentWebViewWasaBinding>(FragmentWebViewWasaBinding::inflate) {
    /** Задание переменных */ //region
    // Стартовый URL
    private val loadUrl = WSAVA_URL
    // ViewModel
    private lateinit var viewModel: WsavaViewFragmentViewModel
    // ShowVetMedicalViewFragmentScope
    private lateinit var showWsavaViewFragmentScope: Scope
    // SettingsImpl
    private val settings: SettingsImpl = KoinJavaComponent.getKoin().get()
    // newInstance для данного класса
    companion object {
        fun newInstance(): WsavaViewFragment = WsavaViewFragment()
    }
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showWsavaViewFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            FragmentScope.SHOW_WSAVA_VIEW_FRAGMENT_SCOPE,
            named(FragmentScope.SHOW_WSAVA_VIEW_FRAGMENT_SCOPE)
        )
    }
    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showWsavaViewFragmentScope.close()
        super.onDetach()
    }
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация вьюмодели
        initViewModel()
        // Настройка WebView
        webViewSetup()
    }

    // Настройка WebView
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

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    // Получение текущей ссылки на элемент,
                    // по которому кликнул пользователь в Wsava
                    request?.let{
                        settings.setCurrentWsavaUrl(it.url.toString())
                    }
                    return super.shouldOverrideUrlLoading(view, request)
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
        val _viewModel: WsavaViewFragmentViewModel by showWsavaViewFragmentScope.inject()
        viewModel = _viewModel
    }
}