package com.pet.animal.formula.dose.health.veterinary.cure.vetformula.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.edittext.EditTextFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.webview.VetMedicalViewFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.webview.WsavaViewFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.BackButtonListener
import com.pet.animal.formula.dose.health.veterinary.cure.utils.*
import com.pet.animal.formula.dose.health.veterinary.cure.utils.language.LocaleHelper
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

    // Переменная для сохранения признака текущей темы приложения (тёмная или светлая)
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
        AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim)
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim)
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            this, R.anim.from_bottom_anim
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim)
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
        // Установка темы приложения
        setApplicationTheme()
        // Отслеживание первого или последующего запусков MainActivity
        if (savedInstanceState != null) {
            // Установка текущего экрана приложения
            navigatorHolder.setNavigator(navigator)
        } else {
            // Установка начального экрана приложения
            this.viewModel.router.navigateTo(this.viewModel.screens.mainScreen())
            // Заполнение базы данных, если приложение запущено в первый раз
            this.viewModel.writeDataToBDAtFirstRun()
        }
        // Установка событий при нажатии на кнопки FAB
        onClickFab()
        // Отображение содержимого окна
        setContentView(binding.root)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        val sharedPreferences: SharedPreferences =
            getSharedPreferences(
                SHARED_PREFERENCES_KEY,
                AppCompatActivity.MODE_PRIVATE
            )
        val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
        sharedPreferencesEditor.putBoolean(SHARED_PREFERENCES_THEME_KEY, isTheme)
        sharedPreferencesEditor.apply()
    }

    // Функция - слушатель нажатий по FAB
    @SuppressLint("ResourceType")
    private fun onClickFab() {
        with(binding) {
            fabMain.setOnClickListener {
                setAnimation()
            }

            fabWebViewVetmedical.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.bottom_activity_fragments_container,
                        VetMedicalViewFragment(), TAG_VETMEDICAL_BOTTOM_WINDOW
                    )
                    .commit()
            }
            fabWebViewWsava.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.bottom_activity_fragments_container,
                        WsavaViewFragment(), TAG_WSAVA_BOTTOM_WINDOW
                    )
                    .commit()
            }
            fabTextView.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.bottom_activity_fragments_container,
                        EditTextFragment(), TAG_NOTE_BOTTOM_WINDOW
                    )
                    .commit()
            }
        }
    }

    //Настройка анимации всех FAB
    private fun setAnimation() {
        if (clicked) {
            with(binding) {
                fabMain.startAnimation(rotateOpen)
                fabTextView.startAnimation(toBottom)
                fabWebViewVetmedical.startAnimation(toBottom)
                fabWebViewWsava.startAnimation(toBottom)

                fabTextView.isClickable = false
                fabWebViewVetmedical.isClickable = false
                fabWebViewWsava.isClickable = false

                fabWebViewVetmedical.visibility = View.INVISIBLE
                fabWebViewWsava.visibility = View.INVISIBLE
                fabTextView.visibility = View.INVISIBLE
            }
        } else {
            with(binding) {
                fabMain.startAnimation(rotateClose)
                fabTextView.startAnimation(fromBottom)
                fabWebViewVetmedical.startAnimation(fromBottom)
                fabWebViewWsava.startAnimation(fromBottom)

                fabTextView.isClickable = true
                fabWebViewVetmedical.isClickable = true
                fabWebViewWsava.isClickable = true

                fabWebViewVetmedical.visibility = View.VISIBLE
                fabWebViewWsava.visibility = View.VISIBLE
                fabTextView.visibility = View.VISIBLE
            }
        }
        clicked = !clicked
    }

    /** Методы для настройки навигатора */ //region
    override fun onResume() {
        super.onResume()
        LocaleHelper.onResume(this)
        // Установка навигатора
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        LocaleHelper.onPause()
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
        navigatorHolder.setNavigator(navigator)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

    override fun createConfigurationContext(overrideConfiguration: Configuration): Context {
        val context = super.createConfigurationContext(overrideConfiguration)
        return LocaleHelper.onAttach(context)
    }

    // Установка темы приложения
    private fun setApplicationTheme() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences(SHARED_PREFERENCES_KEY, MODE_PRIVATE)
        isTheme = sharedPreferences.getBoolean(
            SHARED_PREFERENCES_THEME_KEY, true
        )
        if (!isTheme) {
            setTheme(R.style.Splash_LightTheme)
        } else {
            setTheme(R.style.Splash_DarkTheme)
        }
    }
}
