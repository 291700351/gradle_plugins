package com.github.lee.gradle.plugins.pgyer.task

import com.android.build.gradle.api.ApplicationVariant
import com.github.lee.gradle.plugins.pgyer.extension.getPgyerExtension
import com.github.lee.gradle.plugins.pgyer.net.OnHttpCallback
import com.github.lee.plugin.utils.ext.i

import com.github.lee.plugin.utils.net.HttpHelper
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import okhttp3.Response
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.math.BigDecimal

private const val UPLOAD_URL = "https://www.pgyer.com/apiv2/app/upload"

private const val GROUP = "Pgyer"

open class PgyerUploadTask : DefaultTask() {

    @Input
    var buildName = ""

    @Input
    lateinit var variant: ApplicationVariant


    init {
        group = GROUP
        description =
            "Compile and upload the apk file of the ${buildName.toLowerCase()} environment to the Dandelion server"
    }


    @TaskAction
    fun pgyerUpload() {
        val extension = getPgyerExtension(project)
        variant.outputs.all {
            val apiKey = extension?.apiKey
            if (apiKey?.isBlank() == true) {
                throw RuntimeException("Please set apiKey...")
            }
            val outputFile = it.outputFile
            HttpHelper.uploadFile(
                UPLOAD_URL,
                object : OnHttpCallback {
                    private var oldProgress = 0.0

                    override fun onStart() {
                        super.onStart()
                        logger.quiet("Start uploading the file $outputFile to the Pgyer server")
                    }

                    override fun onProgress(total: Long, current: Long) {
                        val progress = (current * 1.0 / total) * 100
                        if (current >= total) {
                            logger.quiet("Upload progress is : 100%")
                            logger.quiet("Upload file success, Waiting for response")
                        }
                        if (progress - oldProgress >= 0.5) {
                            val decimal = BigDecimal(progress)
                            val value = decimal.setScale(2, BigDecimal.ROUND_UP).toFloat()
                            logger.quiet("Upload progress is : $value")
                            oldProgress = progress
                        }
                    }

                    override fun onResponse(response: Response) {
                        super.onResponse(response)
                        val jo = JsonParser.parseString(response.body?.string())
                            .asJsonObject
                        val json = GsonBuilder().setPrettyPrinting().create()
                            .toJson(jo)
                        logger.quiet(json)
                    }

                    override fun onFailure(e: Exception) {
                        super.onFailure(e)
                        logger.error("Upload File Error:", e)
                    }
                },
                Pair("_api_key", apiKey ?: ""),
                Pair("file", outputFile),
                Pair("buildInstallType", extension?.installType ?: 1),
                Pair("buildPassword", extension?.password ?: ""),
                Pair("buildUpdateDescription", extension?.updateDescription ?: ""),
                Pair("buildInstallStartDate", extension?.installStartDate ?: ""),
                Pair("buildInstallEndDate", extension?.installEndDate ?: ""),
                Pair("buildChannelShortcut", extension?.channelShortcut ?: "")
            )
        }
    }
}