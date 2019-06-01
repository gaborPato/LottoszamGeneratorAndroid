package com.example.lottovariaciok


import com.example.lottovariaciok.myControler.myFactory.LottoFactory
import com.example.lottovariaciok.myControler.MyStrings
import com.example.lottovariaciok.myControler.chkFixNumbers

fun main() {

    val fl = mutableListOf<String>("4", "35")

    val lottoType = MyStrings.seven_lotto_type_str

    val chkFixNumbers = chkFixNumbers(fl, lottoType)

    val lottoCoupon = LottoFactory(chkFixNumbers, lottoType, 23).makeFullLotto()
    lottoCoupon.forEach { lo -> println(lo.lottoLine()) }

}