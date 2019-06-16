package com.sandeep.scannertest.services

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object PreferenceUtils {
    fun getPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun getString(key: String, defaultValue: String, context: Context): String {
        return getPreferences(context).getString(key, defaultValue)
    }

    fun getStringSet(key: String, defaultValue: Set<String>, context: Context): Set<String>? {
        return getPreferences(context).getStringSet(key, defaultValue)
    }

    fun getInt(key: String, defaultValue: Int, context: Context): Int {
        return getPreferences(context).getInt(key, defaultValue)
    }

    fun getLong(key: String, defaultValue: Long, context: Context): Long {
        return getPreferences(context).getLong(key, defaultValue)
    }

    fun getFloat(key: String, defaultValue: Float, context: Context): Float {
        return getPreferences(context).getFloat(key, defaultValue)
    }

    fun getBoolean(key: String, defaultValue: Boolean, context: Context): Boolean {
        return getPreferences(context).getBoolean(key, defaultValue)
    }

    fun getEditor(context: Context): SharedPreferences.Editor {
        return getPreferences(context).edit()
    }

    fun putString(key: String, value: String?, context: Context) {
        getEditor(context).putString(key, value).apply()
    }

    fun putStringSet(key: String, value: Set<String>, context: Context) {
        getEditor(context).putStringSet(key, value).apply()
    }

    fun putInt(key: String, value: Int, context: Context) {
        getEditor(context).putInt(key, value).apply()
    }

    fun putLong(key: String, value: Long, context: Context) {
        getEditor(context).putLong(key, value).apply()
    }

    fun putFloat(key: String, value: Float, context: Context) {
        getEditor(context).putFloat(key, value).apply()
    }

    fun putBoolean(key: String, value: Boolean, context: Context) {
        getEditor(context).putBoolean(key, value).apply()
    }

    fun remove(key: String, context: Context) {
        getEditor(context).remove(key).apply()
    }

    fun clear(context: Context) {
        getEditor(context).clear().apply()
    }
}