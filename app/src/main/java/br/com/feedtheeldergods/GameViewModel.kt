package br.com.feedtheeldergods

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import Classes.Creature
import Classes.Cthulhu
import Classes.Hastur

class GameViewModel : ViewModel() {

    var currentGod by mutableStateOf<Creature?>(null)
        private set

    var godSelected by mutableStateOf(false)
        private set

    var lastMessage by mutableStateOf("")
        private set

    var deathMessage by mutableStateOf<String?>(null)
        private set

    var isGameOver by mutableStateOf(false)
        private set

    // ChooseGodScreen
    fun selectGod(choice: Int) {
        currentGod = when (choice) {
            1 -> Cthulhu()
            2 -> Hastur()
            else -> return
        }
        godSelected = true
    }

    // HubScreen → FeedScreen
    fun feed(opc: Int) {
        val msg = currentGod?.Feed(opc) ?: return
        lastMessage = msg
        timeCycle()
    }

    // HubScreen → PlayScreen
    fun play(opc: Int) {
        val msg = currentGod?.ToPlay(opc) ?: return
        lastMessage = msg
        timeCycle()
    }

    // HubScreen → RestScreen
    fun rest(hours: Int) {
        val msg = currentGod?.Rest(hours) ?: return
        lastMessage = msg
        timeCycle()
    }

    // ação rápida, sem tela própria
    fun bathe() {
        val msg = currentGod?.Bathe() ?: return
        lastMessage = msg
        timeCycle()
    }

    // ação rápida, sem tela própria
    fun bathroom() {
        val msg = currentGod?.Bathroom() ?: return
        lastMessage = msg
        timeCycle()
    }

    private fun timeCycle() {
        currentGod?.TimeCycle()
        val death = currentGod?.checkDeath()
        if (death != null) {
            deathMessage = death
            isGameOver = true
        }
    }
}
