package com.example.lottovariaciok.myControler

import android.graphics.Color


object MyIntegers {

    //osszes szam a szelvényeken
    const val six_lotto_total_numbers = 45
    const val seven_lotto_total_numbers = 35
    const val five_lotto_total_numbers = 90

    //húzott számok darab
    const val six_lottery_number = 6
    const val seven_lottery_number = 7
    const val five_lottery_number = 5

    //fix szamok mennyisege darab


    const val fix_numbers_max = 4

    object Colors {
        @JvmField
        val toastBackGroundColor: Int = Color.rgb(218, 110, 150)

        @JvmField
        val five_l_background=Color.rgb(154,205,50)

        @JvmField
        val six_l_background=Color.rgb(240,128,128)

        @JvmField
        val seven_l_background=Color.rgb(135,205,235)
    }





    object ViewValues {

        const val lottodata1_items_size:Float = 26f
    }

}

object MyStrings {

    const val six_lotto_type_str = "6/45 Lotto"
    const val seven_lotto_type_str = "7/35 Lotto"
    const val five_lotto_type_str = "5/90 Lotto"

}