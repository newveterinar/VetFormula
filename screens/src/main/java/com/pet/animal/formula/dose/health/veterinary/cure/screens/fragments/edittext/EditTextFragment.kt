package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.edittext

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentEditTextBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.settings.SettingsImpl
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class EditTextFragment : BaseFragment<FragmentEditTextBinding>(FragmentEditTextBinding::inflate) {
    /** Задание переменных */ //region
    private lateinit var viewModel: EditTextFragmentViewModel

    // Текстовый элемент
    lateinit var editText: TextView
    private var StringBuilder = ""

    // SettingsImpl
    private val settings: SettingsImpl = KoinJavaComponent.getKoin().get()

    // ShowPharmacySurfaceResultFragmentScope
    private lateinit var showEditTextFragmentScope: Scope

    // newInstance для данного класса
    companion object {
        fun newInstance(): EditTextFragment = EditTextFragment()
    }
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showEditTextFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            FragmentScope.SHOW_EDIT_TEXT_FRAGMENT_SCOPE,
            named(FragmentScope.SHOW_EDIT_TEXT_FRAGMENT_SCOPE)
        )
    }

    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showEditTextFragmentScope.close()
        super.onDetach()
    }
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация текстового элемента
        initEditText()
        // Инициализация ViewModel
        initViewModel()
        initButtons()

        viewModel.notesLiveData.observe(viewLifecycleOwner){
            binding.editTextText.setText(it)
        }

        viewModel.urlLiveData.observe(viewLifecycleOwner) { pair ->
            val builder = AlertDialog.Builder(requireContext())
            val inflater = layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_save, null)
            builder.setView(dialogView)
            val dialog = builder.create()
            setAlertDialogButtonListener(dialog, dialogView)

            dialog.show()
        }
    }

    private fun initButtons() {
        binding.apply {
            btnSaveNote.setOnClickListener {
                viewModel.setUrlLiveData(Pair(settings.getCurrentVetmedicalUrl(),
                    settings.getCurrentWsavaUrl()))
            }

            btnLoadNote.setOnClickListener {

            }
        }
    }

    // Инициализация текстового элемента
    @SuppressLint("SetTextI18n")
    private fun initEditText() {
        editText = binding.editTextText
        //editText.text = "${settings.getCurrentVetmedicalUrl()}\n${settings.getCurrentWsavaUrl()}"
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        val _viewModel: EditTextFragmentViewModel by showEditTextFragmentScope.inject()
        viewModel = _viewModel
    }



    private fun setAlertDialogButtonListener(dialog: AlertDialog, dialogView: View) {
        val btnSaveWasa = dialogView.findViewById<Button>(R.id.btn_save_wasa)
        val btnSaveVetmedical =
            dialogView.findViewById<Button>(R.id.btn_save_vetmedical)
        val btnSaveCancle = dialogView.findViewById<Button>(R.id.btn_save_cancle)
        btnSaveVetmedical.setOnClickListener {
            viewModel.addUrlAsNote(viewModel.urlLiveData.value?.first)
            dialog.dismiss()
        }
        btnSaveWasa.setOnClickListener {
            viewModel.addUrlAsNote(viewModel.urlLiveData.value?.second)
            dialog.dismiss()
        }
        btnSaveCancle.setOnClickListener {
            dialog.dismiss()
        }
    }
}