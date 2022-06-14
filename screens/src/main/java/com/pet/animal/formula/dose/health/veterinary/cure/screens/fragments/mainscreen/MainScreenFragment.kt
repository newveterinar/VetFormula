import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentMainscreenBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.mainscreen.MainScreenFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.utils.NUMBER_NAVIGATION_BUTTONS_ON_MAINSCREEN

class MainScreenFragment :
    BaseFragment<FragmentMainscreenBinding>(FragmentMainscreenBinding::inflate) {
    /** Задание переменных */ //region

    // Навигация
    private val navigationButtons =
        arrayOfNulls<View>(size = NUMBER_NAVIGATION_BUTTONS_ON_MAINSCREEN)

    // ViewModel
    private lateinit var viewModel: MainScreenFragmentViewModel
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация кнопок
        initNavigationButtons()
        // Инициализация ViewModel
        initViewModel()
    }

    // Инициализация кнопок
    private fun initNavigationButtons() {
        binding.apply {
            navigationButtons.also {
                it[0] = this.settingsButton
                it[1] = this.aboutAppButton
                it[2] = this.pharmacySurfaceButtonContainer
                it[3] = this.gasesButtonContainer
                it[4] = this.calculatorSurfaceButtonContainer
                it[5] = this.timerSurfaceButtonContainer
            }
        }

        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> viewModel.router.navigateTo(viewModel.screens.settingsScreen())
                    1 -> viewModel.router.navigateTo(viewModel.screens.aboutScreen())
                    2 -> viewModel.router.navigateTo(viewModel.screens.pharmacyScreen())
                    3 -> viewModel.router.navigateTo(viewModel.screens.gasScreen())
                    4 -> viewModel.router.navigateTo(viewModel.screens.calculatorScreen())
                    5 -> viewModel.router.navigateTo(viewModel.screens.timerScreen())
                    else -> {
                        Toast.makeText(requireContext(),
                            requireActivity().resources.getString(
                            R.string.error_button_is_not_assigned), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainScreenFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): MainScreenFragment = MainScreenFragment()
    }
}