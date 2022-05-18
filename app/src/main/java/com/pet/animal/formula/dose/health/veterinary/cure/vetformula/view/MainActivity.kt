package com.pet.animal.formula.dose.health.veterinary.cure.vetformula.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.CalcInteractorImpl
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.BackButtonListener
import com.pet.animal.formula.dose.health.veterinary.cure.utils.MAIN_ACTIVITY_NAME
import com.pet.animal.formula.dose.health.veterinary.cure.vetformula.R
import com.pet.animal.formula.dose.health.veterinary.cure.vetformula.databinding.ActivityMainBinding
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class MainActivity : AppCompatActivity() {
    /** Задание переменных */ //region
    // Переменная для FAB
    private var isExpanded = false

    // Навигация
    private val navigator =
        AppNavigator(this@MainActivity, R.id.activity_fragments_container)
    private val navigatorHolder: NavigatorHolder = KoinJavaComponent.getKoin().get()

    // ViewModel
    private val mainActivityScope: Scope = KoinJavaComponent.getKoin().getOrCreateScope(
        MAIN_ACTIVITY_NAME, named(MAIN_ACTIVITY_NAME)
    )
    private lateinit var model: MainViewModel

    // Binding
    private lateinit var binding: ActivityMainBinding

    // Калькулятор (интерактор для работы с калькулятором)
    private val calcInteractor: CalcInteractorImpl = CalcInteractorImpl()
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Подключение Binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Создание Scope для MainActivity
        val viewModel: MainViewModel by mainActivityScope.inject()
        model = viewModel

        if (savedInstanceState == null) {
            model.router.navigateTo(model.screens.mainScreen())
        }

        setFAB()
    }

    /** Методы для настройки навигатора */ //region
    override fun onResume() {
        super.onResume()
        // Установка навигатора
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        // Удаление навигатора
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        model.router.exit()
    }

    /** FAB **/
    //region
    private fun setFAB() {
        setInitialState()

        binding.fab.setOnClickListener {
            if (isExpanded) {
                collapseFAB()
            } else {
                expandFAB()
            }
        }
    }

    private fun setInitialState() {
        binding.activityFragmentsContainer.apply {
            alpha = 0f
        }
        binding.optionOneContainer.apply {
            alpha = 0f
            isClickable = false
        }
        binding.optionTwoContainer.apply {
            alpha = 0f
            isClickable = false
        }
    }

    private fun expandFAB() {
        isExpanded = true
        ObjectAnimator.ofFloat(binding.fabImageview, "rotation", 0f, 255f).start()
        ObjectAnimator.ofFloat(binding.optionOneContainer, "translationY", -130f).start()
        ObjectAnimator.ofFloat(binding.optionTwoContainer, "translationY", -250f).start()

        binding.optionTwoContainer.animate()
            .alpha(1f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionTwoContainer.isClickable = true
                    binding.optionTwoContainer.setOnClickListener {
                        Toast.makeText(this@MainActivity, "Option 2", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        binding.optionOneContainer.animate()
            .alpha(1f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    binding.optionOneContainer.isClickable = true
                    binding.optionOneContainer.setOnClickListener {
                        Toast.makeText(this@MainActivity, "Option 1", Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

    private fun collapseFAB() {
        isExpanded = false
        ObjectAnimator.ofFloat(binding.fabImageview, "rotation", 0f, -180f).start()
        ObjectAnimator.ofFloat(binding.optionOneContainer, "translationY", 0f).start()
        ObjectAnimator.ofFloat(binding.optionTwoContainer, "translationY", 0f).start()

        binding.optionTwoContainer.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionTwoContainer.isClickable = false
                    binding.optionTwoContainer.setOnClickListener(null)
                }
            })
        binding.optionOneContainer.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionOneContainer.isClickable = false
                }
            })
        binding.activityFragmentsContainer.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.activityFragmentsContainer.isClickable = false
                }
            })

    }

    //endregion
    override fun onDestroy() {
        // Удаление скоупа для данной активити
        mainActivityScope.close()
        super.onDestroy()
    }
}