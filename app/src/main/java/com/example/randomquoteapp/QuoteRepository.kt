package com.example.randomquoteapp

object QuoteRepository {
    private val quotes = listOf(
        Quote("Be yourself; everyone else is already taken.", "Oscar Wilde", R.drawable.quote1),
        Quote("Simplicity is the ultimate sophistication.", "Leonardo da Vinci", R.drawable.quote2),
        Quote("In the middle of difficulty lies opportunity.", "Albert Einstein", R.drawable.quote3),
        Quote("The only journey is the one within.", "Rainer Maria Rilke", R.drawable.quote4),
        Quote("What we think, we become.", "Buddha", R.drawable.quote5),
        Quote("The purpose of our lives is to be happy.", "Dalai Lama", R.drawable.quote6),
        Quote("Life is what happens when you're busy making other plans.", "John Lennon", R.drawable.quote7),
        Quote("Get busy living or get busy dying.", "Stephen King", R.drawable.quote8),
        Quote("You have within you right now, everything you need to deal with whatever the world can throw at you.", "Brian Tracy", R.drawable.quote9),
        Quote("Believe you can and you're halfway there.", "Theodore Roosevelt", R.drawable.quote10),
        Quote("Maganda si Zaira", "Matt", R.drawable.quote11)
    )

    fun getRandomQuote(): Quote {
        return quotes.random()
    }
}