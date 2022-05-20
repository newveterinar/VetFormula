package com.pet.animal.formula.dose.health.veterinary.cure.vetformula.view

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.BackButtonListener
import com.pet.animal.formula.dose.health.veterinary.cure.utils.MAIN_ACTIVITY_NAME
import com.pet.animal.formula.dose.health.veterinary.cure.utils.SLIDER_MAX_DIFFERENT_VALUE
import com.pet.animal.formula.dose.health.veterinary.cure.utils.SLIDER_MAX_VALUE
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
        setContentView(binding.root)

        // Создание Scope для MainActivity
        val viewModel: MainViewModel by mainActivityScope.inject()
        this.viewModel = viewModel

        // Установка слайдера
        guideLine = binding.horizontalGuideline
        params = guideLine.layoutParams as ConstraintLayout.LayoutParams

        if (savedInstanceState == null) {
            this.viewModel.router.navigateTo(this.viewModel.screens.mainScreen())
        }

        val fabMain: FloatingActionButton = findViewById(R.id.fab_main)
        val fabWebView: FloatingActionButton = findViewById(R.id.fab_web_view)
        val fabTextView: FloatingActionButton = findViewById(R.id.fab_text_view)

        fabMain.setOnClickListener {
            onAddButtonClicked()
        }

        fabWebView.setOnClickListener {
            Toast.makeText(this, "WebView Button Clicked", Toast.LENGTH_SHORT).show()
        }

        fabTextView.setOnClickListener {
            Toast.makeText(this, "TextView Button Clicked", Toast.LENGTH_SHORT).show()

        }
    }

    private fun onAddButtonClicked() {
//        Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {

        val fabWebView: FloatingActionButton = findViewById(R.id.fab_web_view)
        val fabTextView: FloatingActionButton = findViewById(R.id.fab_text_view)

        if (!clicked) {
            fabWebView.visibility = View.VISIBLE
            fabTextView.visibility = View.VISIBLE
        } else {
            fabWebView.visibility = View.INVISIBLE
            fabTextView.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {

        val fabMain: FloatingActionButton = findViewById(R.id.fab_main)
        val fabWebView: FloatingActionButton = findViewById(R.id.fab_web_view)
        val fabTextView: FloatingActionButton = findViewById(R.id.fab_text_view)

        if (!clicked) {
            fabWebView.startAnimation(fromBottom)
            fabTextView.startAnimation(fromBottom)
            fabMain.startAnimation(rotateOpen)
        } else {
            fabWebView.startAnimation(toBottom)
            fabTextView.startAnimation(toBottom)
            fabMain.startAnimation(rotateClose)
        }
    }

    private fun setClickable(clicked: Boolean) {

        val fabWebView: FloatingActionButton = findViewById(R.id.fab_web_view)
        val fabTextView: FloatingActionButton = findViewById(R.id.fab_text_view)

        if (!clicked) {
            fabWebView.isClickable = true
            fabTextView.isClickable = true
        } else {
            fabWebView.isClickable = false
            fabTextView.isClickable = false
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
