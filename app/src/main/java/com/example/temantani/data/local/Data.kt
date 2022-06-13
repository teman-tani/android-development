package com.example.temantani.data.local

import com.example.temantani.R
import com.example.temantani.data.model.MenuCategory
import com.example.temantani.data.model.Recommendation
import com.example.temantani.utils.Strings

object Data {
    fun getMenuCategory() : Map<String, MenuCategory> {
        return mapOf(
            MENU_DETECTION to MenuCategory(
                thumbnail = R.drawable.img_menu,
                title = Strings.get(R.string.detection)
            ) ,
            MENU_TREATMENT to MenuCategory(
                thumbnail = R.drawable.img_menu,
                title = Strings.get(R.string.treatment)
            ),
            MENU_SHOP to MenuCategory(
                thumbnail = R.drawable.img_menu,
                title = Strings.get(R.string.shop)
            ),
            MENU_FELTILIZER_CALCULATOR to MenuCategory(
                thumbnail = R.drawable.img_menu,
                title = Strings.get(R.string.calculator)
            ),
            MENU_ASK_EXPERT to MenuCategory(
                thumbnail = R.drawable.img_menu,
                title = Strings.get(R.string.ask)
            ),
            MENU_COMMUNITY to MenuCategory(
                thumbnail = R.drawable.img_menu,
                title = Strings.get(R.string.community)
            ),

            )
    }

    fun getRecommendation() : List<Recommendation> {
        return listOf(
            Recommendation(
                tempat = "Kota Kediri",
                nama = "Filia 525SE 250ml Obat Hawar Daun dan Blast",
                image_src = "https://images.tokopedia.net/img/cache/100-square/VqbcmM/2021/10/6/cb6e9da4-c048-48be-b677-14e731fe69de.jpg",
                product_link = "https://www.tokopedia.com/saraagro/filia-525se-250ml-obat-hawar-daun-dan-blast?extParam=ivf%3Dfalse%26src%3Dsearch"
            ),
            Recommendation(
                tempat = "Kab. Probolinggo",
                nama = "Filia 525SE 250ml Obat Hawar Daun dan Blast",
                image_src = "https://images.tokopedia.net/img/cache/100-square/VqbcmM/2021/1/30/81155430-380e-487c-9e07-13b0d6ba2d9a.jpg",
                product_link = "https://www.tokopedia.com/finariapertanian/filia-525se-250ml-obat-hawar-daun-dan-blast?extParam=ivf%3Dfalse%26src%3Dsearch"
            ),
            Recommendation(
                tempat = "Jakarta Pusat",
                nama = "Filia 525SE 250ml Obat Hawar Daun dan Blast",
                image_src = "https://images.tokopedia.net/img/cache/100-square/VqbcmM/2022/3/28/8bed6007-f84f-4bcd-9e7c-c759b34e03cc.jpg",
                product_link = "https://www.tokopedia.com/kaslan/filia-525se-250ml-obat-hawar-daun-dan-blast-terlaris?extParam=ivf%3Dfalse%26src%3Dsearch"
            ),
            Recommendation(
                tempat = "Kota Depok" ,
                nama = "Terlaris Filia 525Se 250Ml Obat Hawar Daun Dan Blast",
                image_src = "https://images.tokopedia.net/img/cache/100-square/VqbcmM/2022/5/14/f6b8df14-86f1-409d-b4eb-4ee54311616c.jpg",
                product_link = "https://www.tokopedia.com/arinstore8/terlaris-filia-525se-250ml-obat-hawar-daun-dan-blast?extParam=ivf%3Dfalse%26src%3Dsearch",

            ),
            Recommendation(
                tempat = "Kota Depok",
                nama = "Terlaris Filia 525Se 250Ml Obat Hawar Daun Dan Blast",
                image_src = "https://images.tokopedia.net/img/cache/100-square/VqbcmM/2022/5/14/f6b8df14-86f1-409d-b4eb-4ee54311616c.jpg",
                product_link = "https://www.tokopedia.com/arinstore8/terlaris-filia-525se-250ml-obat-hawar-daun-dan-blast?extParam=ivf%3Dfalse%26src%3Dsearch",
            ),
            Recommendation(
                tempat = "Jakarta Pusat",
                nama = "Filia 525Se 250Ml Obat Hawar Daun Dan Blast Terlaris",
                image_src= "https://images.tokopedia.net/img/cache/100-square/VqbcmM/2022/3/22/0858a5b1-f35e-43eb-aa1a-5c80ca7ed7d8.jpg",
                product_link = "https://www.tokopedia.com/yoly-1/filia-525se-250ml-obat-hawar-daun-dan-blast-terlaris?extParam=ivf%3Dfalse%26src%3Dsearch",
            ),
        )
    }



    const val MENU_DETECTION = "menu_detection"
    const val MENU_TREATMENT = "menu_treatment"
    const val MENU_SHOP = "menu_shop"
    const val MENU_FELTILIZER_CALCULATOR = "menu_fertilizer_calculator"
    const val MENU_ASK_EXPERT = "menu_ask_expert"
    const val MENU_COMMUNITY = "menu_commynity"

}