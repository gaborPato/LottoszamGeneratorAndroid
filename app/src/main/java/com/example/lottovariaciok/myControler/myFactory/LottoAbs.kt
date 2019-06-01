package com.example.lottovariaciok.myControler.myFactory


import com.example.lottovariaciok.myControler.MyIntegers
import java.io.Serializable
import kotlin.random.Random


abstract class LottoAbs(fixNumbers: MutableList<Int>) : Serializable {

    protected abstract val _lottery_number: Int
    protected abstract val _max_number_of_coupon: Int
    private var lottoLine: ArrayList<Int>
    fun lottoLine(): String {

        var sb = StringBuilder()

        lottoLine.forEachIndexed { index, i ->
            sb.append(i.toString())
            if (index < lottoLine.size - 1)
                sb.append(" ,")
        }


        return sb.toString()
    }

    init {

        lottoLine = ArrayList(fixNumbers)


    }

    protected fun makeLottoLine() {

        var i = lottoLine.size
        while (i < _lottery_number) {
            val rnd = Random.nextInt(1, _max_number_of_coupon + 1)
            if (!lottoLine.contains(rnd)) {
                lottoLine.add(rnd)
                i++
            }
        }
        lottoLine.sort()

    }
}


class SixLotto(fixNumbers: MutableList<Int>) : LottoAbs(fixNumbers) {
    override val _lottery_number: Int = MyIntegers.six_lottery_number

    override val _max_number_of_coupon: Int = MyIntegers.six_lotto_total_numbers


    init {

        makeLottoLine()
    }


}

class SevenLotto(fixNumbers: MutableList<Int>) : LottoAbs(fixNumbers) {
    override val _lottery_number: Int = MyIntegers.seven_lottery_number

    override val _max_number_of_coupon: Int = MyIntegers.seven_lotto_total_numbers


    init {

        makeLottoLine()
    }


}
class FiveLotto(fixNumbers: MutableList<Int>): LottoAbs(fixNumbers) {
    override val _lottery_number: Int = MyIntegers.five_lottery_number
    override val _max_number_of_coupon: Int= MyIntegers.five_lotto_total_numbers

    init {
        makeLottoLine()
    }

}