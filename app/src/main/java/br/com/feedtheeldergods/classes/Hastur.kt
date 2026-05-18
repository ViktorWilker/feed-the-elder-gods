
package Classes

class Hastur : Creature("Hastur") {
    override fun Feed(opc: Int): String {
        super.Feed(opc)
        return when (opc) {
            1 -> "Almas! Meu favorito! Não conta pro Cthulhu que eu disse isso."
            2 -> "Hehehe. Eles acharam que estavam bem. Não estavam."
            3 -> "Peguei esse pesadelo emprestado. NÃO vou devolver."
            4 -> "Esse sonho tinha um plot twist. Hastur adicionou essa parte."
            5 -> "Luz estelar de Carcosa! Tem gosto de lar e pavor existencial!"
            6 -> "Disseram meu nome três vezes! Não pedi mas tudo bem!"
            else -> "..."
        }
    }


    override fun ToPlay(opc: Int): String {
        super.ToPlay(opc)
        return when (opc) {
            1 -> "A peça foi um sucesso! Ninguém sobreviveu à ovação de pé."
            2 -> "Psiu... só um sussurro... hehe... lá se foi."
            3 -> "O rei tá agindo estranho agora. Hastur ajudou com isso."
            4 -> "Hastur mudou a terça-feira. Ninguém percebeu ainda. Hilário."
            else -> "..."
        }
    }

    override fun Rest(hours: Int): String {
        super.Rest(hours)
        return when {
            hours <= 2 -> "Hora do trono!! O Rei Amarelo precisa do soninho tá bom?"
            hours <= 5 -> "Hastur foi embora! ...ou não?"
            else -> "O Lago de Hali é tão lindo... dois sóis se pondo... lugar perfeito pra um cochilo."
        }
    }

    override fun Bathe(): String {
        super.Bathe()
        return "O Rei Amarelo NÃO toma banho. Isso é humilhante... tá bom."
    }

    override fun Bathroom(): String {
        super.Bathroom()
        return "Até o horror cósmico tem suas obrigações biológicas. Indigno."
    }
}