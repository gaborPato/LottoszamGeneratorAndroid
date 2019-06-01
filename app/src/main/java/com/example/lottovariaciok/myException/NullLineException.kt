package com.example.lottovariaciok.myException

class NullLineException : RuntimeException() {

    override val message: String? = "Minimum egy sornak lennie kell!!!"

}