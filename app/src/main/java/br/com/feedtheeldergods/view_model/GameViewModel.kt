package br.com.feedtheeldergods.view_model

import Classes.Creature
import Classes.Cthulhu
import Classes.Hastur
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

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

    fun selectGod(choice: Int) {
        currentGod = when (choice) {
            1 -> Cthulhu()
            2 -> Hastur()
            else -> return
        }
        godSelected = true
    }
    fun feed(opc: Int) {
        val msg = currentGod?.Feed(opc) ?: return
        lastMessage = msg
        timeCycle()
    }

    fun feedSouls(soulsCollected: Int) {
        val msg = currentGod?.FeedSouls(soulsCollected) ?: return
        lastMessage = msg
        timeCycle()
    }

    fun play(opc: Int) {
        val msg = currentGod?.ToPlay(opc) ?: return
        lastMessage = msg
        timeCycle()
    }

    fun rest(hours: Int) {
        val msg = currentGod?.Rest(hours) ?: return
        lastMessage = msg
        timeCycle()
    }

    fun bathe() {
        val msg = currentGod?.Bathe() ?: return
        lastMessage = msg
        timeCycle()
    }

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

    fun feedSanity(drained: Int) {
        val msg = currentGod?.FeedSanity(drained) ?: return
        lastMessage = msg
        timeCycle()
    }
}