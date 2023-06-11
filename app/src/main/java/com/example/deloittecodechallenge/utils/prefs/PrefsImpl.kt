package com.example.deloittecodechallenge.utils.prefs

import android.content.SharedPreferences
import androidx.core.content.edit
import java.util.Locale

class PrefsImpl(
    private val sharedPreferences: SharedPreferences,
) : Prefs {

    override var getCurrentLanguage: String
        get() = sharedPreferences.getString(
            "list_preference_fragment_more_language", Locale.getDefault().language
        ) ?: ""
        set(value) {
            sharedPreferences.edit { putString("list_preference_fragment_more_language", value) }
        }
}
