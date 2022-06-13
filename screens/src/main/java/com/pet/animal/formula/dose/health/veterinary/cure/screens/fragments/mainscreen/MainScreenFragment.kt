import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentMainscreenBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.mainscreen.MainScreenFragmentViewModel

class MainScreenFragment :
    BaseFragment<FragmentMainscreenBinding>(FragmentMainscreenBinding::inflate) {
    /** Задание переменных */ //region

    // Навигация
    private val navigationButtons = arrayOfNulls<View>(size = 8)

    // ViewModel
    private lateinit var viewModel: MainScreenFragmentViewModel
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация кнопок
        initNavigationButtons()
        // Инициализация ViewModel
        initViewModel()

        setToastHintObserver()
    }

    private fun setToastHintObserver() {
        viewModel.toastHint.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    // Инициализация кнопок
    private fun initNavigationButtons() {
        binding.apply {
            navigationButtons.also {
                it[0] = this.pharmacySurfaceButtonContainer
                it[1] = this.fluidsSurfaceButtonContainer
                it[2] = this.hematologySurfaceButtonContainer
                it[3] = this.conversionsSurfaceButtonContainer
                it[4] = this.settingsButton
                it[5] = this.calculatorSurfaceButtonContainer
                it[6] = this.pharmacyAboutButton
                it[7] = this.timerSurfaceButtonContainer
            }

        }

        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> viewModel.router.navigateTo(viewModel.screens.pharmacyScreen())
                    1 -> viewModel.router.navigateTo(viewModel.screens.fluidsScreen())
                    2 -> viewModel.router.navigateTo(viewModel.screens.hematologyScreen())
                    3 -> viewModel.router.navigateTo(viewModel.screens.conversionsScreen())
                    4 -> viewModel.router.navigateTo(viewModel.screens.settingsScreen())
                    5 -> viewModel.router.navigateTo(viewModel.screens.calculatorScreen())
                    6 -> viewModel.router.navigateTo(viewModel.screens.aboutScreen())
                    7 -> viewModel.router.navigateTo(viewModel.screens.timerScreen())
                    else -> {
                        Toast.makeText(requireContext(), requireActivity().resources.getString(
                            R.string.error_button_is_not_assigned), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            button?.setOnLongClickListener {
                when (index) {
                    4 -> {
                        setToastHint(getString(R.string.main_screen_settings_hint))
                        true
                    }
                    6 -> {
                        setToastHint(getString(R.string.main_screen_about_hint))
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }
    }

    private fun setToastHint(hint: String) {
        viewModel.setToastHint(getString(R.string.long_click_hint,
            hint))
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainScreenFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): MainScreenFragment = MainScreenFragment()
    }
}