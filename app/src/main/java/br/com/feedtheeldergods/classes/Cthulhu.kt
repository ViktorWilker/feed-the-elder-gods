// Cthulhu.kt
package Classes

class Cthulhu : Creature("Cthulhu") {
    override fun Feed(opc: Int): String {
        super.Feed(opc)
        return when (opc) {
            1 -> "Yummy yummy souls! Cthulhu wants more!!"
            2 -> "Sanity? Overrated. Delicious though."
            3 -> "Nightmares are like candy. Spooky candy."
            4 -> "Dreams taste better when they're about puppies. Weird but true."
            5 -> "Starlight! Cthulhu's favorite snack since before your sun existed."
            6 -> "Cultists again... they really should stop volunteering for this."
            else -> "..."
        }
    }

    override fun ToPlay(opc: Int): String {
        super.ToPlay(opc)
        return when (opc) {
            1 -> "RAWR!! Cthulhu go SMASH!! ...okay that was fun."
            2 -> "Boo! Hehe, their faces when they wake up screaming is priceless."
            3 -> "Cthulhu made a big storm!! The fishies are scared. Cute."
            4 -> "They're running in circles now. Cthulhu is basically a dog toy for cultists."
            else -> "..."
        }
    }

    override fun Rest(hours: Int): String {
        super.Rest(hours)
        return when {
            hours <= 2 -> "Blub blub... Cthulhu sleepy now. Don't wake up or world goes boom."
            hours <= 5 -> "Zzz... Ph'nglui... zzz... mglw'nafh... zzz..."
            else -> "Floating in the void... so peaceful... no cultists here..."
        }
    }

    override fun Bathe(): String {
        super.Bathe()
        return "Cthulhu hates baths. The cultists who suggested it are gone now."
    }

    override fun Bathroom(): String {
        super.Bathroom()
        return "The ocean got a little more eldritch today. Sorry fish."
    }
}