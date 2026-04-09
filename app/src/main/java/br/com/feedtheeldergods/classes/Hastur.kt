package Classes

class Hastur : Creature("Hastur") {
    override fun Feed() {
        println("[1] Souls - [2] Sanity - [3] Nightmares - [4] Dreams - [5] Starlight - [6] Cultist")
        var opc = readln().toInt()
        when (opc) {
            1 -> println("Souls! My favorite! Don't tell Cthulhu I said that.")
            2 -> println("Teehee. They thought they were fine. They were not fine.")
            3 -> println("Borrowed this nightmare. Will NOT be returning it.")
            4 -> println("This dream had a plot twist. Hastur added that part.")
            5 -> println("Starlight from Carcosa! Tastes like home and existential dread!")
            6 -> println("They said my name three times! I didn't ask them to but okay!")
        }
        super.Feed()
    }

    override fun ToPlay() {
        println("[1] Stage a Play - [2] Whisper Madness - [3] Corrupt a King - [4]   Reality")
        var opc = readln().toInt()
        when (opc) {
            1 -> println("The play was a hit! Nobody survived the standing ovation though.")
            2 -> println("Pspsps... just one little whisper... hehe... there it goes.")
            3 -> println("The king is acting weird now. Hastur helped with that.")
            4 -> println("Hastur changed Tuesday. Nobody noticed yet. So funny.")
        }
        super.ToPlay()
    }

    override fun Rest() {
        println("[1] Sit upon the Throne of Carcosa - [2] Vanish into the Yellow Sign - [3] Watch from the Lake of Hali")
        var opc = readln().toInt()
        when (opc) {
            1 -> println("Throne time!! The Yellow King needs his beauty sleep okay?")
            2 -> println("Hastur is gone! ...or is he? (he is, but still spooky)")
            3 -> println("Lake of Hali is so pretty... two suns setting... perfect nap spot.")
            else -> println("...")
        }
        super.Rest()
    }
}