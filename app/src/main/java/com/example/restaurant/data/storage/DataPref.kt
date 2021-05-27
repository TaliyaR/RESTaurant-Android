package com.example.restaurant.data.storage

import android.content.Context

class DataPref(private val context: Context) {

    companion object {
        private const val KeyTableId = "key_table_id"
        private const val PrefsName = "prefs_name"
        private const val AuthToken = "auth"
    }

    private fun getPrefs() = context.getSharedPreferences(PrefsName, Context.MODE_PRIVATE)

    fun setTableId(tableId: String) {
        getPrefs().edit().putString(KeyTableId, tableId).apply()
    }

    fun getTableId(): String? = getPrefs().getString(KeyTableId, null)

    fun deleteTableId() {
        getPrefs().edit().putString(KeyTableId, null).apply()
    }

    fun setAuthToken(authCookie: String){
        getPrefs().edit().putString(AuthToken, authCookie).apply()
    }

    fun deleteAuthToken(){
        getPrefs().edit().putString(AuthToken, null).apply()
    }

    fun getAuthToken(): String? {
        return  getPrefs().getString(AuthToken, null)
    }
}