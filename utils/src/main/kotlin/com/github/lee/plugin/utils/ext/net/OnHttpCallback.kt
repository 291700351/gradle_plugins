package com.github.lee.gradle.plugins.pgyer.net

import okhttp3.Response

interface OnHttpCallback {

    fun onStart() {}
    fun onProgress(total: Long, current: Long) {}
    fun onFailure(e: Exception) {}
    fun onResponse(response: Response) {}
}