package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.webview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentWebViewVetmedicalBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.VETMEDICAL_URL
import com.pet.animal.formula.dose.health.veterinary.cure.utils.settings.SettingsImpl
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class VetMedicalViewFragment:
    BaseFragment<FragmentWebViewVetmedicalBinding>(FragmentWebViewVetmedicalBinding::inflate) {
    /** Задание переменных */ //region
    // Стартовый URL
    private val loadUrl: String = VETMEDICAL_URL
    // ViewModel
    private lateinit var viewModel: VetMedicalViewFragmentViewModel
    // ShowVetMedicalViewFragmentScope
    private lateinit var showVetMedicalViewFragmentScope: Scope
    // SettingsImpl
    private val settings: SettingsImpl = KoinJavaComponent.getKoin().get()
    // newInstance для данного класса
    companion object {
        fun newInstance(): VetMedicalViewFragment = VetMedicalViewFragment()
    }
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showVetMedicalViewFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            FragmentScope.SHOW_VETMEDICAL_VIEW_FRAGMENT_SCOPE,
            named(FragmentScope.SHOW_VETMEDICAL_VIEW_FRAGMENT_SCOPE)
        )
    }
    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showVetMedicalViewFragmentScope.close()
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

    // Инициализация ViewModel
    private fun initViewModel() {
        val _viewModel: VetMedicalViewFragmentViewModel by showVetMedicalViewFragmentScope.inject()
        viewModel = _viewModel
    }

    // Настройка WebView
    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup() {
        with(binding) {
            webViewFragmentVetmedical.webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    view?.visibility = View.INVISIBLE
                    progressbarVetmedical.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    view?.visibility = View.VISIBLE
                    progressbarVetmedical.visibility = View.INVISIBLE
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    // Получение текущей ссылки на элемент,
                    // по которому кликнул пользователь в Vetmedical
                    request?.let{
                        settings.setCurrentVetmedicalUrl(it.url.toString())
                    }
                    return super.shouldOverrideUrlLoading(view, request)
                }
            }
            webViewFragmentVetmedical.apply {
                loadUrl(loadUrl)
                settings.javaScriptEnabled = true
                settings.allowContentAccess = true
            }
        }
    }
}