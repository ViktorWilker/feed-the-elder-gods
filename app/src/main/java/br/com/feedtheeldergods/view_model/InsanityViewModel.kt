package br.com.feedtheeldergods.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.com.feedtheeldergods.classes.Persona
import br.com.feedtheeldergods.classes.PersonaRepository
import java.lang.reflect.Array.set

class InsanityViewModel : ViewModel() {

    private lateinit var repo: PersonaRepository

    var currentPersona by mutableStateOf<Persona?>(null)
        private set
    var currentGrid by mutableStateOf<List<String>>(emptyList())
        private set
    var selectedElements by mutableStateOf<List<String>>(emptyList())
        private set
    var sanityDrained by mutableStateOf(0)
        private set
    var onCorrect: (() -> Unit)? = null


    fun init(context: Context) {
        if (!::repo.isInitialized)
            repo = PersonaRepository(context)
    }

    fun startGame() {
        sanityDrained = 0
        loadNext()
    }

    fun loadNext() {
        val persona = repo.random()
        currentPersona = persona
        currentGrid = repo.buildGrid(persona.fear)
        selectedElements = emptyList()
    }

    fun selectElement(element: String) {
        if (element in selectedElements) return
        val next = selectedElements + element
        if (next.size < 2) {
            selectedElements = next
            return
        }
        val correct = currentPersona?.fear?.elements ?: return
        if (next.toSet() == correct.toSet()){
            sanityDrained += 15
            onCorrect?.invoke()
        }
        selectedElements = emptyList()
        loadNext()
    }
}
