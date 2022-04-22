package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacyBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import org.koin.java.KoinJavaComponent

class PharmacyFragment: Fragment() {
    /** Задание переменных */ //region
        // Binding
    private var _binding: FragmentPharmacyBinding? = null
    private val binding: FragmentPharmacyBinding
        get() {
            return _binding!!
        }
        // Навигация
    private val screens: AppScreensImpl = KoinJavaComponent.getKoin().get()
    private val router: Router = KoinJavaComponent.getKoin().get()
    lateinit var buttonToPharmacySurfaceScreen: ConstraintLayout
        // ViewModel
    lateinit var model: ViewModel
    //endregion

    companion object {
        fun newInstance(): PharmacyFragment = PharmacyFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPharmacyBinding.inflate(inflater, container, false)

        // Инициализация кнопок
        initButtons()
        // Инициализация ViewModel
        initViewModel()

        return binding.root
    }

    // Очистка Binding при уничтожении фрагмента
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // Инициализация кнопок
    fun initButtons() {
        buttonToPharmacySurfaceScreen = binding.pharmacySurfaceButtonContainer
        buttonToPharmacySurfaceScreen.setOnClickListener {
            router.navigateTo(screens.pharmacySurfaceScreen())
        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        model = ViewModelProvider(this).get(PharmacyFragmentViewModel::class.java)
    }
}