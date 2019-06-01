package com.example.lottovariaciok.myException

class NumberLimitException(mess: String = "Unkown error") : RuntimeException() {

    override val message: String? = mess

}