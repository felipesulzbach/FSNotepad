package com.felipe.fsnotepad

import android.content.Context
import android.content.SharedPreferences

class AnnotationPreference(private val context: Context) {

    private val RECORD = "annotation.preference"
    private val KEY = "name"
    private val preference: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        preference = context.getSharedPreferences(RECORD, 0)
        editor = preference.edit()
    }

    fun saveAnnotation(annotation: String?) {
        editor.putString(KEY, annotation)
        editor.commit()
    }

    fun getAnnotation(): String? {
        return preference.getString(KEY, "")
    }
}