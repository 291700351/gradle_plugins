/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.github.lee.gradle.plugins

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.tasks.bundling.AbstractArchiveTask

/**
 * A simple 'hello world' plugin.
 */
class PublishMavenCentralPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.afterEvaluate{
            project.plugins.apply("maven-publish")
            project.plugins.apply("signing")

            val byName = project.extensions.getByName("publishing")
        }
        // Register a task
//        MavenPublishBaseExtension
//        project.tasks.register("greeting") { task ->
//            task.doLast {
//                println("Hello from plugin 'com.github.lee.gradle.plugins.greeting'")
//            }
//        }
    }
}
