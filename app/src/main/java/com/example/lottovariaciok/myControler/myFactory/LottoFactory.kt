package com.example.lottovariaciok.myControler.myFactory

import com.example.lottovariaciok.myControler.MyStrings

class LottoFactory(val fixNumList: MutableList<Int>, val lottoType: String, val line: Int) {

    fun makeFullLotto(): MutableList<LottoAbs> {

        var userFixList = ArrayList<Int>(fixNumList)
        var result = mutableListOf<LottoAbs>()

        for (i in 0 until line) {

            if (lottoType.equals(MyStrings.six_lotto_type_str))
                result.add(SixLotto(userFixList))
            else if (lottoType.equals(MyStrings.seven_lotto_type_str))
                result.add(SevenLotto(userFixList))
            else
                result.add(FiveLotto(userFixList))
        }
        return result

    }


}