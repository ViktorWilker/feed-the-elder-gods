
package Classes

class Cthulhu : Creature("Cthulhu") {
    override fun Feed(opc: Int): String {
        super.Feed(opc)
        return when (opc) {
            1 -> "Alminhas gostosas! Cthulhu quer mais!!"
            2 -> "Sanidade? Superestimada. Mas deliciosa."
            3 -> "Pesadelos são como balas. Balas assustadoras."
            4 -> "Sonhos ficam melhores quando são sobre cachorrinhos. Estranho mas verdade."
            5 -> "Luz estelar! O petisco favorito de Cthulhu desde antes do seu sol existir."
            6 -> "Cultistas de novo... eles realmente deveriam parar de se voluntariar pra isso."
            else -> "..."
        }
    }

    override fun ToPlay(opc: Int): String {
        super.ToPlay(opc)
        return when (opc) {
            1 -> "RAAWR!! Cthulhu vai DESTRUIR!! ...tá, foi divertido."
            2 -> "Buu! Hehe, as caras deles quando acordam gritando não tem preço."
            3 -> "Cthulhu fez uma tempestade enorme!! Os peixinhos estão com medo."
            4 -> "Estão rodando em círculos agora. Cthulhu é basicamente um brinquedo pra cultistas."
            else -> "..."
        }
    }


    override fun Rest(hours: Int): String {
        super.Rest(hours)
        return when {
            hours <= 2 -> "Blub blub... Cthulhu com sono. Não acordar ou o mundo explode."
            hours <= 5 -> "Zzz... Ph'nglui... zzz... mglw'nafh... zzz..."
            else -> "Flutuando no vazio... tão tranquilo..."
        }
    }


    override fun Bathe(): String {
        super.Bathe()
        return "Cthulhu odeia banho. Aqueles que sugeriram sumiram."
    }


    override fun Bathroom(): String {
        super.Bathroom()
        return "O oceano ficou um pouco mais eldritchiano hoje. Desculpa, peixes."
    }

}