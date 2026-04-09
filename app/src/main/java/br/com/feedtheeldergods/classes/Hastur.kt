// Hastur.kt
package Classes

class Hastur : Creature("Hastur") {
    override fun Feed(opc: Int): String {
        super.Feed(opc)
        return when (opc) {
            1 -> "Souls! My favorite! Don't tell Cthulhu I said that."
            2 -> "Teehee. They thought they were fine. They were not fine."
            3 -> "Borrowed this nightmare. Will NOT be returning it."
            4 -> "This dream had a plot twist. Hastur added that part."
            5 -> "Starlight from Carcosa! Tastes like home and existential dread!"
            6 -> "They said my name three times! I didn't ask them to but okay!"
            else -> "..."
        }
    }

    override fun ToPlay(opc: Int): String {
        super.ToPlay(opc)
        return when (opc) {
            1 -> "The play was a hit! Nobody survived the standing ovation though."
            2 -> "Pspsps... just one little whisper... hehe... there it goes."
            3 -> "The king is acting weird now. Hastur helped with that."
            4 -> "Hastur changed Tuesday. Nobody noticed yet. So funny."
            else -> "..."
        }
    }

    override fun Rest(hours: Int): String {
        super.Rest(hours)
        return when {
            hours <= 2 -> "Throne time!! The Yellow King needs his beauty sleep okay?"
            hours <= 5 -> "Hastur is gone! ...or is he? (he is, but still spooky)"
            else -> "Lake of Hali is so pretty... two suns setting... perfect nap spot."
        }
    }

    override fun Bathe(): String {
        super.Bathe()
        return "The Yellow King does NOT do baths. This is beneath him. ...fine."
    }

    override fun Bathroom(): String {
        super.Bathroom()
        return "Even cosmic horror has its biological obligations. Undignified."
    }
}