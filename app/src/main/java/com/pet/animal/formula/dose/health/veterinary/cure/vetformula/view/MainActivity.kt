package com.pet.animal.formula.dose.health.veterinary.cure.vetformula.view

import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.BackButtonListener
import com.pet.animal.formula.dose.health.veterinary.cure.utils.*
import com.pet.animal.formula.dose.health.veterinary.cure.utils.screens.UpAndBottomFramesSizesChanger
import com.pet.animal.formula.dose.health.veterinary.cure.vetformula.R
import com.pet.animal.formula.dose.health.veterinary.cure.vetformula.databinding.ActivityMainBinding
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class MainActivity : AppCompatActivity() {
    /** Задание переменных */ //region
    // Навигация
    private val navigator =
        AppNavigator(this@MainActivity, R.id.activity_fragments_container)
    private val navigatorHolder: NavigatorHolder = KoinJavaComponent.getKoin().get()

    // ViewModel
    private val mainActivityScope: Scope = KoinJavaComponent.getKoin().getOrCreateScope(
        MAIN_ACTIVITY_NAME, named(MAIN_ACTIVITY_NAME)
    )
    private lateinit var viewModel: MainViewModel
    private var isTheme: Boolean = true

    // Binding
    private lateinit var binding: ActivityMainBinding

    // Класс для хранения размеров верхнего и нижнего окон
    private val upAndBottomFramesSizesChanger: UpAndBottomFramesSizesChanger =
        KoinJavaComponent.getKoin().get()

    // Слайдер
    lateinit var guideLine: Guideline
    lateinit var params: ConstraintLayout.LayoutParams

    // FAB
    private var clicked = false

    // Ленивая инициализация анимаций для FAB
    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.rotate_open_anim
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.rotate_close_anim
        )
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.from_bottom_anim
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.to_bottom_anim
        )
    }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Подключение Binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Создание Scope для MainActivity
        val viewModel: MainViewModel by mainActivityScope.inject()
        this.viewModel = viewModel

        // Установка слайдера
        guideLine = binding.horizontalGuideline
        params = guideLine.layoutParams as ConstraintLayout.LayoutParams

        if (savedInstanceState != null) {
            val sharedPreferences: SharedPreferences =
                getSharedPreferences(SHARED_PREFERENCES_KEY, MODE_PRIVATE)
            isTheme = sharedPreferences.getBoolean(
                SHARED_PREFERENCES_THEME_KEY, true)
            if (!isTheme) {
                setTheme(R.style.Splash_LightTheme)
            } else {
                setTheme(R.style.Splash_DarkTheme)
            }
            this.viewModel.router.navigateTo(this.viewModel.screens.mainScreen())
        }
        onClickFab()
        setContentView(binding.root)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        val sharedPreferences: SharedPreferences =
            getSharedPreferences(SHARED_PREFERENCES_KEY,
                AppCompatActivity.MODE_PRIVATE
            )
        var sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
        sharedPreferencesEditor.putBoolean(SHARED_PREFERENCES_THEME_KEY, isTheme)
        sharedPreferencesEditor.apply()
    }

    // Функция - слушатель нажатий по FAB
    private fun onClickFab() {
        binding.fabMain.setOnClickListener {
            onFabMainButtonClicked()
        }

        binding.fabWebView.setOnClickListener {
            Toast.makeText(this, "WebView Button Clicked", Toast.LENGTH_SHORT).show()
        }

        binding.fabTextView.setOnClickListener {
            Toast.makeText(this, "TextView Button Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    // Функция основной FAB
    private fun onFabMainButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    // Настройка показ/не показ выскакивающих FAB
    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            binding.fabWebView.visibility = View.VISIBLE
            binding.fabTextView.visibility = View.VISIBLE
        } else {
            binding.fabWebView.visibility = View.INVISIBLE
            binding.fabTextView.visibility = View.INVISIBLE
        }
    }

    //Настройка анимации всех FAB
    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            binding.fabWebView.startAnimation(fromBottom)
            binding.fabTextView.startAnimation(fromBottom)
            binding.fabMain.startAnimation(rotateOpen)
        } else {
            binding.fabWebView.startAnimation(toBottom)
            binding.fabTextView.startAnimation(toBottom)
            binding.fabMain.startAnimation(rotateClose)
        }
    }

    // Функция, которая убирает "скрытые" клики по выскакивающим FAB
    private fun setClickable(clicked: Boolean) {
        if (!clicked) {
            binding.fabWebView.isClickable = true
            binding.fabTextView.isClickable = true
        } else {
            binding.fabWebView.isClickable = false
            binding.fabTextView.isClickable = false
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
        viewModel.router.exit()
    }

    //endregion
    override fun onDestroy() {
        // Удаление скоупа для данной активити
        mainActivityScope.close()
        super.onDestroy()
    }

    // Изменение размеров верхнего и нижнего окон
    fun changeUpAndBottomFramesSizes(value: Float) {
        upAndBottomFramesSizesChanger.constraintGuidePercent =
            SLIDER_MAX_DIFFERENT_VALUE - value / SLIDER_MAX_VALUE
        params.guidePercent = upAndBottomFramesSizesChanger.constraintGuidePercent
        guideLine.layoutParams = params
        viewModel.router.replaceScreen(viewModel.screens.mainScreen())
    }
}
