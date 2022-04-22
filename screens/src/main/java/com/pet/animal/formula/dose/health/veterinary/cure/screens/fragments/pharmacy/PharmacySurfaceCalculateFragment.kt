package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacySurfaceBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacySurfaceCalculateBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import org.koin.java.KoinJavaComponent

class PharmacySurfaceCalculateFragment: Fragment() {
    /** Задание переменных */ //region
    // Binding
    private var _binding: FragmentPharmacySurfaceCalculateBinding? = null
    private val binding: FragmentPharmacySurfaceCalculateBinding
        get() {
            return _binding!!
        }
    // Навигация
    private val screens: AppScreensImpl = KoinJavaComponent.getKoin().get()
    private val router: Router = KoinJavaComponent.getKoin().get()
    lateinit var buttonToPharmacySurfaceScreen: ConstraintLayout
    lateinit var buttonToPharmacySurfaceCalculateResultScreen: Button
    // ViewModel
    lateinit var model: ViewModel
    //endregion

    companion object {
        fun newInstance(): PharmacySurfaceCalculateFragment = PharmacySurfaceCalculateFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPharmacySurfaceCalculateBinding.inflate(inflater, container, false)

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
        buttonToPharmacySurfaceCalculateResultScreen = binding.pharmacyCalculateButton
        buttonToPharmacySurfaceCalculateResultScreen.setOnClickListener {
            router.navigateTo(screens.pharmacySurfaceCalculateResultScreen())
        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        model = ViewModelProvider(this)
            .get(PharmacySurfaceCalculateFragmentViewModel::class.java)
    }
}