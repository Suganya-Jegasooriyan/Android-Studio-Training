package com.example.recyclerview

class MovieModel(title: String, genre: String, year: String) {

    private var title: String
    private var genre: String
    private var year: String
    init {
        this.title = title!!
        this.genre = genre!!
        this.year = year!!
    }
    fun getTitle(): String? {
        return title
    }
    fun setTitle(name: String?) {
        title = name!!
    }
    fun getYear(): String? {
        return year
    }
    fun setYear(year: String?) {
        this.year = year!!
    }
    fun getGenre(): String? {
        return genre
    }
    fun setGenre(genre: String?) {
        this.genre = genre!!
    }
}
