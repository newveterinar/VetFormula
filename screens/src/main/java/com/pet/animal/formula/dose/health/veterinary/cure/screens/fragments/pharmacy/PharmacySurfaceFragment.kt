package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacySurfaceBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import org.koin.java.KoinJavaComponent

class PharmacySurfaceFragment: Fragment() {
    /** Задание переменных */ //region
    // Binding
    private var _binding: FragmentPharmacySurfaceBinding? = null
    private val binding: FragmentPharmacySurfaceBinding
        get() {
            return _binding!!
        }
    // Навигация
    private val screens: AppScreensImpl = KoinJavaComponent.getKoin().get()
    private val router: Router = KoinJavaComponent.getKoin().get()
    lateinit var buttonToPharmacySurfaceScreen: ConstraintLayout
    lateinit var buttonToPharmacySurfaceResultScreen: Button
    lateinit var buttonToAboutScreen: ImageView
    // ViewModel
    lateinit var model: ViewModel
    //endregion

    companion object {
        fun newInstance(): PharmacySurfaceFragment = PharmacySurfaceFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPharmacySurfaceBinding.inflate(inflater, container, false)

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
        buttonToPharmacySurfaceScreen = binding.pharmacyPreviousButtonContainer
        buttonToPharmacySurfaceScreen.setOnClickListener {
            requireActivity().onBackPressed()
        }
        buttonToPharmacySurfaceResultScreen = binding.pharmacyCalculateButton
        buttonToPharmacySurfaceResultScreen.setOnClickListener {
            router.navigateTo(screens.pharmacySurfaceResultScreen())
        }
        buttonToAboutScreen = binding.pharmacyAboutButton
        buttonToAboutScreen.setOnClickListener {
            router.navigateTo(screens.aboutScreen())
        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        model = ViewModelProvider(this)
            .get(PharmacySurfaceFragmentViewModel::class.java)
    }
}