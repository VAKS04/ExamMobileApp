package com.example.lazycolumn.model

enum class Country(val displayName:String){
    ALL("Все"),
    UNKNOWN("Неизвестно"),
    GREECE("Греция"),
    INDIA("Индия"),
    CHINA("Китай"),
    ROME("Рим"),
    FRANCE("Франция"),
    IRELAND("Ирландия"),
    KAZAKHSTAN("Казахстан"),
    SAMANID_STATE("Самандия"),
    ITALY("Италия");

    companion object {
        fun fromName(name:String):Country{
            return entries.find { it.displayName.equals(name,ignoreCase = true) }?:UNKNOWN
        }
    }


}