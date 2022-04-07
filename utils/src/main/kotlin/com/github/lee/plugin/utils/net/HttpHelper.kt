package com.github.lee.plugin.utils.net

import com.github.lee.gradle.plugins.pgyer.net.OnHttpCallback
import okhttp3.*
import okio.Buffer
import okio.BufferedSink
import okio.source
import java.io.File
import java.time.Duration

object HttpHelper {
    private var client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(Duration.ofSeconds(30))
        .readTimeout(Duration.ofHours(2))
        .build()


    //===Desc:=====================================================================================

    fun uploadFile(
        url: String, cb: OnHttpCallback? = null,
        vararg params: Pair<String, Any>
    ) {
        val builder = MultipartBody.Builder()
            .setType(MultipartBody.FORM)

        params.forEach {
            val key = it.first
            val value = it.second
            if (value is File) {
                val body = createUploadRequestBody(value, cb)
                builder.addFormDataPart(key, value.name, body)
            } else {
                builder.addFormDataPart(key, value.toString())
            }
        }
        val requestBody = builder.build()
        cb?.onStart()
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            cb?.onFailure(RuntimeException(response.message))
            return
        }
        cb?.onResponse(response)
    }


    //===Desc:=====================================================================================

    private fun createUploadRequestBody(file: File, cb: OnHttpCallback? = null): RequestBody {

        return object : RequestBody() {
            override fun contentType(): MediaType =
                MultipartBody.FORM

            override fun contentLength(): Long =
                file.length()

            override fun writeTo(sink: BufferedSink) {
                val source = file.source()
                val buffer = Buffer()
                val total = contentLength()

                var current = 0L

                var readCount: Long
                do {
                    readCount = source.read(buffer, 2048)
                    if (readCount == -1L) {
                        break
                    }
                    sink.write(buffer, readCount)
                    current += readCount
                    cb?.onProgress(total, current)

                } while (readCount != -1L)


//                Source source;
//                try {
//                    for (long readCount; (readCount = source.read(buf, 2048)) != -1; ) {
//                        sink.write(buf, readCount);
//                        listener.onProgress(contentLength(), remaining -= readCount, remaining == 0);
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        }

    }
}

