package com.github.lee.gradle.plugins.pgyer

import com.android.build.gradle.AppExtension
import com.github.lee.gradle.plugins.pgyer.extension.PgyerExtension
import com.github.lee.gradle.plugins.pgyer.task.PgyerUploadTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class PgyerUploadPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.extensions.create(
            "pgyer",
            PgyerExtension::class.java
        )
        target.afterEvaluate {
            val appExtension = it.extensions.getByType(
                AppExtension::class.java
            )

            appExtension.applicationVariants.forEach { variant ->
                var buildName = variant.name
                if (buildName.isNullOrEmpty()) {
                    return@forEach
                }
                buildName = buildName.substring(0, 1).toUpperCase() + buildName.substring(1)
                val task = it.tasks.create("pgyerUpload$buildName", PgyerUploadTask::class.java)
                task.buildName = buildName.toLowerCase()
                task.variant = variant

                variant.assembleProvider.get()
                    .dependsOn(target.tasks.findByName("clean"))
                task.dependsOn(variant.assembleProvider.get())
            }

        }
    }
}

