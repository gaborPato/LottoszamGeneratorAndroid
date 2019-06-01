package com.example.lottovariaciok.myControler

import com.example.lottovariaciok.myException.MultiSameNumbersExc
import com.example.lottovariaciok.myException.NumberLimitException

fun chkFixNumbers(fixN: List<String>, lottoType: String): MutableList<Int> {


    if (fixN.isEmpty())
        throw NoSuchElementException("legalább egy fix szám kell!!!")
    val fixNSet = sameNumbersChk(fixN)
    numberLimitCHK(fixNSet, lottoType)
    return ArrayList<Int>(fixNSet)
}

private fun numberLimitCHK(fixNSet: MutableSet<Int>, lottoType: String) {
    val maxLimit: Int
    if (lottoType.equals(MyStrings.six_lotto_type_str))
        maxLimit = MyIntegers.six_lotto_total_numbers
    else if (lottoType.equals(MyStrings.seven_lotto_type_str))
        maxLimit = MyIntegers.seven_lotto_total_numbers
    else
        maxLimit= MyIntegers.five_lotto_total_numbers

    fixNSet.forEach { el ->
        if (el < 1 || el > maxLimit)
            throw NumberLimitException("nincs ilyen szám a szelvényen!!!")
    }

}

private fun sameNumbersChk(fixN: List<String>): MutableSet<Int> {
    val fixIntSet = listFixInt(fixN)
    if (fixIntSet.size < fixN.size)
        throw MultiSameNumbersExc("A megadott fix számoknak külömbözőnek kell lennie!!!")
    return fixIntSet
}

private fun listFixInt(fixN: List<String>): MutableSet<Int> {
    val result = mutableSetOf<Int>()

    fixN.forEach { element -> result.add(element.toInt()) }
    return result

}
