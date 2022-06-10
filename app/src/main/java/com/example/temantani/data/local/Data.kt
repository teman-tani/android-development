package com.example.temantani.data.local

import com.example.temantani.R
import com.example.temantani.data.model.MenuCategory
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

    const val MENU_DETECTION = "menu_detection"
    const val MENU_TREATMENT = "menu_treatment"
    const val MENU_SHOP = "menu_shop"
    const val MENU_FELTILIZER_CALCULATOR = "menu_fertilizer_calculator"
    const val MENU_ASK_EXPERT = "menu_ask_expert"
    const val MENU_COMMUNITY = "menu_commynity"

}