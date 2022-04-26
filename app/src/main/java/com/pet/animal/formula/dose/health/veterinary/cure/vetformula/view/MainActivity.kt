package com.pet.animal.formula.dose.health.veterinary.cure.vetformula.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.BackButtonListener
import com.pet.animal.formula.dose.health.veterinary.cure.utils.Constants
import com.pet.animal.formula.dose.health.veterinary.cure.vetformula.R
import com.pet.animal.formula.dose.health.veterinary.cure.vetformula.databinding.ActivityMainBinding
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class MainActivity: AppCompatActivity() {
    /** Задание переменных */ //region
        // Навигация
    private val navigator =
        AppNavigator(this@MainActivity, R.id.activity_fragments_container)
    private val screens: AppScreensImpl = KoinJavaComponent.getKoin().get()
    private val router: Router = KoinJavaComponent.getKoin().get()
    private val navigatorHolder: NavigatorHolder = KoinJavaComponent.getKoin().get()
        // ViewModel
    private val mainActivityScope: Scope = KoinJavaComponent.getKoin().getOrCreateScope(
        Constants.MAIN_ACTIVITY_NAME, named(Constants.MAIN_ACTIVITY_NAME)
    )
    lateinit var model: ViewModel
        // Binding
    lateinit var binding: ActivityMainBinding
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
            router.navigateTo(screens.pharmacyScreen())
        }
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
        router.exit()
    }
    //endregion

    override fun onDestroy() {
        // Удаление скоупа для данной активити
        mainActivityScope.close()
        super.onDestroy()
    }
}