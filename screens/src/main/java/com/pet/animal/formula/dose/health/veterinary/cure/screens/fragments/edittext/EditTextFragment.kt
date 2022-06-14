package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.edittext

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.*
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

        viewModel.urlLiveData.observe(viewLifecycleOwner) { _ ->
            val builder = AlertDialog.Builder(requireContext())
            val inflater = layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_save, null)
            builder.setView(dialogView)
            val dialog = builder.create()
            setAlertDialogButtonListener(dialog, dialogView)
            dialog.show()
        }

        viewModel.noteList.observe(viewLifecycleOwner){noteList->


            val elements = arrayOfNulls<String>(noteList.size)
            var pos=0
            for(note in noteList){
                elements[pos]=note.name
                pos++
            }

            val alertDialog =AlertDialog.Builder(context)
            alertDialog
                .setTitle("Select note")
                .setItems(elements,DialogInterface.OnClickListener(){_,pos->
                    val id = noteList[pos].id
                    id?.let {
                        viewModel.loadNote(it)
                    }
                })
            alertDialog.show()
        }
    }

    private fun initButtons() {
        binding.apply {
            btnSaveNote.setOnClickListener {
                val input = EditText(context)
                val lp = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT)
                input.layoutParams = lp

                val alertDialog  = AlertDialog.Builder(context)
                alertDialog
                    .setTitle("Enter note name")
                    .setView(input)
                    .setPositiveButton("Save"){_,_->
                        val text = input.text.toString()
                        val note = binding.editTextText.text.toString()
                        viewModel.saveNote(text,note)
                    }
                alertDialog.show()
            }

            btnLoadNote.setOnClickListener {
                viewModel.getNoteList()
            }

            btnDeleteNote.setOnClickListener{
                viewModel.deleteNote()
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