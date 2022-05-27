package com.example.umtyl

object ConstantWords {
    fun getCardWord(): ArrayList<CardWord>{
        val cardWordList = ArrayList<CardWord>()

        val cardWord1 = CardWord(
            1, "қанат", "wings"
        )
        cardWordList.add(cardWord1)

        val cardWord2 = CardWord(
            2, "қол", "hand"
        )
        cardWordList.add(cardWord2)

        val cardWord3 = CardWord(
            3, "ай", "moon"
        )
        cardWordList.add(cardWord3)

        val cardWord4 = CardWord(
            4, "жасыл", "green"
        )
        cardWordList.add(cardWord4)

        val cardWord5 = CardWord(
            5, "бас", "head"
        )
        cardWordList.add(cardWord5)

        val cardWord6 = CardWord(
            6, "шелек", "bucket"
        )
        cardWordList.add(cardWord6)

        val cardWord7 = CardWord(
            7, "у", "яд"
        )
        cardWordList.add(cardWord7)

        val cardWord8 = CardWord(
            8, "су", "water"
        )
        cardWordList.add(cardWord8)

        val cardWord9 = CardWord(
            9, "ту", "flag"
        )
        cardWordList.add(cardWord9)

        val cardWord10 = CardWord(
            10, "бу", "пар"
        )
        cardWordList.add(cardWord10)

        return cardWordList
    }
}