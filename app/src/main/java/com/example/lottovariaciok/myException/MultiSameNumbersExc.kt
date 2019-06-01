package com.example.lottovariaciok.myException

class MultiSameNumbersExc(m: String = "Unknow error") : RuntimeException() {

    override val message: String? = m


}