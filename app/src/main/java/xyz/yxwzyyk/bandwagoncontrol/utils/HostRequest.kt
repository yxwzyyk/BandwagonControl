package xyz.yxwzyyk.bandwagoncontrol.utils

import android.util.Log
import okhttp3.*
import java.util.*

/**
 * Created by yyk on 4/9/16.
 */
class HostRequest(veid: String, key: String) {
    private val mVeid: String
    private val mKey: String
    private val mHttp: OkHttpClient;


    init {
        mVeid = veid
        mKey = key
        mHttp = OkHttpClient.Builder().build()
    }

    fun getInfo(): String? {
        try {
            val body = FormBody.Builder()
                    .add("veid", mVeid)
                    .add("api_key", mKey)
                    .build();
            val request = Request.Builder().url(HttpURL.HOST_URL + HttpURL.GET_INFO).post(body).build()
            val response = mHttp.newCall(request).execute()
            return response.body().string()
        } catch (e: Exception) {
            return null
        }
    }

    fun start(): String? {
        try {
            val body = FormBody.Builder()
                    .add("veid", mVeid)
                    .add("api_key", mKey)
                    .build();
            val request = Request.Builder().url(HttpURL.HOST_URL + HttpURL.START).post(body).build()
            val response = mHttp.newCall(request).execute()
            return response.body().string()
        } catch (e: Exception) {
            return null
        }
    }

    fun reboot(): String? {
        try {
            val body = FormBody.Builder()
                    .add("veid", mVeid)
                    .add("api_key", mKey)
                    .build();
            val request = Request.Builder().url(HttpURL.HOST_URL + HttpURL.REBOOT).post(body).build()
            val response = mHttp.newCall(request).execute()
            return response.body().string()
        } catch (e: Exception) {
            return null
        }

    }

    fun stop(): String? {
        try {
            val body = FormBody.Builder()
                    .add("veid", mVeid)
                    .add("api_key", mKey)
                    .build();
            val request = Request.Builder().url(HttpURL.HOST_URL + HttpURL.STOP).post(body).build()
            val response = mHttp.newCall(request).execute()
            return response.body().string()
        } catch (e: Exception) {
            return null
        }
    }

    fun basicShell(command: String): String? {
        try {
            val body = FormBody.Builder()
                    .add("veid", mVeid)
                    .add("api_key", mKey)
                    .add("command", command)
                    .build();
            val request = Request.Builder().url(HttpURL.HOST_URL + HttpURL.BASIC_SHELL).post(body).build()
            val response = mHttp.newCall(request).execute()
            return response.body().string()
        } catch (e: Exception) {
            return null
        }
    }

    fun resetRootPassword(): String? {
        try {
            val body = FormBody.Builder()
                    .add("veid", mVeid)
                    .add("api_key", mKey)
                    .build()
            val request = Request.Builder().url(HttpURL.HOST_URL + HttpURL.RESET_ROOT_PASSWORD).post(body).build()
            val response = mHttp.newCall(request).execute()
            return response.body().string()
        } catch (e: Exception) {
            return null
        }
    }

    fun getAvailableOS(): String? {
        try {
            val body = FormBody.Builder()
                    .add("veid", mVeid)
                    .add("api_key", mKey)
                    .build()
            val request = Request.Builder().url(HttpURL.HOST_URL + HttpURL.GET_AVAILABLE_OS).post(body).build()
            val response = mHttp.newCall(request).execute()
            return response.body().string()
        } catch (e: Exception) {
            return null
        }
    }

    fun reinstallOS(os: String): String? {
        try {
            val body = FormBody.Builder()
                    .add("veid", mVeid)
                    .add("api_key", mKey)
                    .add("os", os)
                    .build()
            val request = Request.Builder().url(HttpURL.HOST_URL + HttpURL.REINSTALL_OS).post(body).build()
            val response = mHttp.newCall(request).execute()
            return response.body().string()
        } catch (e: Exception) {
            return null
        }
    }

    fun getLocations(): String? {
        try {
            val body = FormBody.Builder()
                    .add("veid", mVeid)
                    .add("api_key", mKey)
                    .build()
            val request = Request.Builder().url(HttpURL.HOST_URL + HttpURL.GET_LOCATIONS).post(body).build()
            val response = mHttp.newCall(request).execute()
            return response.body().string()
        } catch (e: Exception) {
            return null
        }
    }

    fun setLocations(location: String): String? {
        try {
            val body = FormBody.Builder()
                    .add("veid", mVeid)
                    .add("api_key", mKey)
                    .add("location", location)
                    .build()
            val request = Request.Builder().url(HttpURL.HOST_URL + HttpURL.SET_LOCATIONS).post(body).build()
            val response = mHttp.newCall(request).execute()
            return response.body().string()
        } catch (e: Exception) {
            return null
        }
    }
}