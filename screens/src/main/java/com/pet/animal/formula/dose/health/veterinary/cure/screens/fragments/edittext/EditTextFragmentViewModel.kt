package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.edittext

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseViewModelForNavigation
import com.pet.animal.formula.dose.health.veterinary.cure.repo.NoteEntity
import com.pet.animal.formula.dose.health.veterinary.cure.repo.Repository
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent
import java.lang.StringBuilder

class EditTextFragmentViewModel : BaseViewModelForNavigation() {

    var stringBuilder = StringBuilder()

    private var noteId:Long?=null
    private var noteName:String=""

    private var  mNoteList:MutableLiveData<List<NoteEntity>> = MutableLiveData()
    var noteList:LiveData<List<NoteEntity>> = mNoteList

    private val _urlLiveData = MutableLiveData<Pair<String, String>>()
    val urlLiveData: LiveData<Pair<String, String>> = _urlLiveData

    private val _notesLiveData = MutableLiveData<String>()
    val notesLiveData: LiveData<String> = _notesLiveData

    private val repositoryImpl: Repository = KoinJavaComponent.getKoin().get()

    fun setUrlLiveData(pair: Pair<String, String>) {
        _urlLiveData.value = pair
    }



    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    fun addUrlAsNote(string: String?){
        stringBuilder
            .append("\n")
            .append(string)
        _notesLiveData.value = stringBuilder.toString()
    }



    fun saveNote(name: String,note:String) {
        val noteEntity = NoteEntity(id=null,name = name,note=note)
        viewModelCoroutineScope.launch {
            noteId = repositoryImpl.saveNote(noteEntity)
            noteName = name
        }

    }

    fun loadNote(id:Long){
        viewModelCoroutineScope.launch {
            val notes = repositoryImpl.loadNote(id)
            if (notes.isNotEmpty()){
                _notesLiveData.postValue(notes[0].note)
                noteId = notes[0].id
                noteName = notes[0].name
            }
        }
    }

    fun getNoteList(){
        viewModelCoroutineScope.launch{
            mNoteList.postValue(repositoryImpl.getNotesList())
        }
    }

    fun deleteNote(){
        noteId?.let{id->
            viewModelCoroutineScope.launch {
                repositoryImpl.deleteNote(id)
                _notesLiveData.postValue("")
                noteId=null
            }
        }
    }

    fun handleError(error: Throwable) {
        Log.d("DBLOAD",error.message.toString())
    }
}