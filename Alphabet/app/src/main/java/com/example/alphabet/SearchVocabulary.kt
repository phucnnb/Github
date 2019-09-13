package com.example.alphabet

class SearchVocabulary {

    companion object {
        fun getVocabulary(id : Int) : Vocabulary {
            var vietnamese = ""
            var pronounce  = ""
            var typeface  = ""
            when(id) {
                0 -> {
                    vietnamese = "Bàn Chân"
                    pronounce = "a-shi"
                    typeface = "あし"
                }

                1 -> {
                    vietnamese = "Vỏ Sò"
                    pronounce = "ka-i (kai)"
                    typeface = "かい"
                }

                2 -> {
                    vietnamese = "Con Bò"
                    pronounce = "U-shi"
                    typeface = "うし"
                }

                3 -> {
                    vietnamese = "Nhà Ga"
                    pronounce = "e-ki"
                    typeface = "えき"
                }

                4 -> {
                    vietnamese = "Muối"
                    pronounce = "shi-o"
                    typeface = "しお"
                }

                5 -> {
                    vietnamese = "Cây Dù"
                    pronounce = "ka-sa"
                    typeface = "かさ"
                }

                6 -> {
                    vietnamese = "Cây Lược"
                    pronounce = "ku-shi"
                    typeface = "くし"
                }

                7 -> {
                    vietnamese = "Hồ Cá"
                    pronounce = "i-ke"
                    typeface = "いけ"
                }

                8 -> {
                    vietnamese = "Phía Bên Này"
                    pronounce = "ko-ko"
                    typeface = "ここ"
                }

                9 -> {
                    vietnamese = "Phía Bên Kia"
                    pronounce = "so-ko"
                    typeface = "そこ"
                }

                10 -> {
                    vietnamese = "Dưa Hấu"
                    pronounce = "su-i-ka (sui-ka)"
                    typeface = "すいか"
                }

                11 -> {
                    vietnamese = "Mồ Hôi"
                    pronounce = "a-ke"
                    typeface = "あけ"
                }

                12 -> {
                    vietnamese = "Phía Bên Kia"
                    pronounce = "a-so-ko"
                    typeface = "あそこ"
                }

                13 -> {
                    vietnamese = "Cái Trống"
                    pronounce = "ta-i-ko (tai-ko)"
                    typeface = "たいこ"
                }

                14 -> {
                    vietnamese = "Căn Nhà"
                    pronounce = "u-chi"
                    typeface = "うち"
                }

                15 -> {
                    vietnamese = "Đôi Giày"
                    pronounce = "ku-tsu"
                    typeface = "くつ"
                }

                16 -> {
                    vietnamese = "Bàn Tay"
                    pronounce = "te"
                    typeface = "て"
                }

                17 -> {
                    vietnamese = "Sợi Chỉ"
                    pronounce = "i-to"
                    typeface = "い と"
                }

                18 -> {
                    vietnamese = "Con Cá"
                    pronounce = "sa-ka-na"
                    typeface = "さかな"
                }

                19 -> {
                    vietnamese = "Con Cua"
                    pronounce = "ka-ni"
                    typeface = "かに"
                }

                20 -> {
                    vietnamese = "Con Chó"
                    pronounce = "i-nu"
                    typeface = "いぬ"
                }

                21 -> {
                    vietnamese = "Con Mèo"
                    pronounce = "ne-ko"
                    typeface = "ねこ"
                }

                22 -> {
                    vietnamese = "Cái Sừng"
                    pronounce = "tsu-no"
                    typeface = "つの"
                }

                23 -> {
                    vietnamese = "Cái Cầu"
                    pronounce = "ha-shi"
                    typeface = "はし"
                }

                24 -> {
                    vietnamese = "Lửa"
                    pronounce = "hi"
                    typeface = "ひ"
                }

                25 -> {
                    vietnamese = "Cây Sáo"
                    pronounce = "Fu-e"
                    typeface = "ふえ"
                }

                26 -> {
                    vietnamese = "Bức Tường"
                    pronounce = "he-i"
                    typeface = "へ い"
                }

                27 -> {
                    vietnamese = "Ngôi Sao"
                    pronounce = "ho-shi"
                    typeface = "ほし"
                }

                28 -> {
                    vietnamese = "Con Ngựa"
                    pronounce = "u-ma"
                    typeface = "うま"
                }

                29 -> {
                    vietnamese = "Cái Tai"
                    pronounce = "mi-mi"
                    typeface = "みみ"
                }

                30 -> {
                    vietnamese = "Con Mắt"
                    pronounce = "me"
                    typeface = "め"
                }

                31 -> {
                    vietnamese = "Đám Mây"
                    pronounce = "ku-mo"
                    typeface = "くも"
                }
            }

            return Vocabulary(vietnamese,pronounce,typeface)
        }
    }
}