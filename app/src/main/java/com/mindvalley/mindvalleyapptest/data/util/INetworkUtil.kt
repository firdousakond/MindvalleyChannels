package com.mindvalley.mindvalleyapptest.data.util

import android.content.Context

interface INetworkUtil {
    fun isOnline(context: Context) : Boolean
}