import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentMainscreenBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.mainscreen.MainScreenFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.utils.NUMBER_NAVIGATION_BUTTONS_ON_MAINSCREEN
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class MainScreenFragment :
    BaseFragment<FragmentMainscreenBinding>(FragmentMainscreenBinding::inflate) {
    /** Задание переменных */ //region

    // Навигация
    private val navigationButtons =
        arrayOfNulls<View>(size = NUMBER_NAVIGATION_BUTTONS_ON_MAINSCREEN)

    // ViewModel
    private lateinit var viewModel: MainScreenFragmentViewModel

    // ShowPharmacySurfaceResultFragmentScope
    private lateinit var showMainScreenFragmentScope: Scope

    // newInstance для данного класса
    companion object {
        fun newInstance(): MainScreenFragment = MainScreenFragment()
    }
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showMainScreenFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            FragmentScope.SHOW_MAIN_SCREEN_FRAGMENT_SCOPE,
            named(FragmentScope.SHOW_MAIN_SCREEN_FRAGMENT_SCOPE)
        )
    }

    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showMainScreenFragmentScope.close()
        super.onDetach()
    }
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
        viewModel.toastHint.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    // Инициализация кнопок
    private fun initNavigationButtons() {
        binding.apply {
            navigationButtons.also {
                it[0] = this.settingsButton
                it[1] = this.aboutAppButton
                it[2] = this.pharmacySurfaceButtonContainer
                it[3] = this.gasesButtonsContainer
                it[4] = this.calculatorSurfaceButtonContainer
                it[5] = this.timerSurfaceButtonContainer
            }
        }

        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> viewModel.router.navigateTo(viewModel.screens.settingsScreen())
                    1 -> viewModel.router.navigateTo(viewModel.screens.infoScreen())
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
        viewModel.setToastHint(
            getString(
                R.string.long_click_hint,
                hint
            )
        )
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        val _viewModel: MainScreenFragmentViewModel by showMainScreenFragmentScope.inject()
        viewModel = _viewModel
    }
}