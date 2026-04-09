package Classes

class Cthulhu() : Creature("Cthulhu") {
    override fun Feed() {
        println("[1] Souls - [2] Sanity - [3] Nightmares - [4] Dreams - [5] Starlight - [6] Cultist")
        var opc = readln().toInt()
        when (opc) {
            1 -> println("Yummy yummy souls! Cthulhu wants more!!")
            2 -> println("Sanity? Overrated. Delicious though.")
            3 -> println("Nightmares are like candy. Spooky candy.")
            4 -> println("Dreams taste better when they're about puppies. Weird but true.")
            5 -> println("Starlight! Cthulhu's favorite snack since before your sun existed.")
            6 -> println("Cultists again... they really should stop volunteering for this.")
        }
        super.Feed()
    }

    override fun ToPlay() {
        println("[1] Destroy a City - [2] Haunt Dreams - [3] Summon Storms - [4] Drive Cultists Mad")
        var opc = readln().toInt()
        when (opc) {
            1 -> println("RAWR!! Cthulhu go SMASH!! ...okay that was fun.")
            2 -> println("Boo! Hehe, their faces when they wake up screaming is priceless.")
            3 -> println("Cthulhu made a big storm!! The fishies are scared. Cute.")
            4 -> println("They're running in circles now. Cthulhu is basically a dog toy for cultists.")
            else -> println("...")
        }
        super.ToPlay()
    }

    override fun Rest() {
        println("[1] Sink into the Ocean - [2] Enter Eternal Slumber - [3] Drift through the Void")
        var opc = readln().toInt()
        when (opc) {
            1 -> println("Blub blub... Cthulhu sleepy now. Don't wake up or world goes boom.")
            2 -> println("Zzz... Ph'nglui... zzz... mglw'nafh... zzz...")
            3 -> println("Floating in the void... so peaceful... no cultists here...")
            else -> println("...")
        }
        super.Rest()
    }
}