package com.github.lee.plugin.utils.ext

import org.gradle.api.Project
import org.gradle.api.logging.LogLevel


fun Project.d(msg: Any?) {
    logger.log(LogLevel.DEBUG, msg.toString())
}

fun Project.d(msg: String?, vararg args: Any) {
    if (args.isEmpty()) {
        logger.log(LogLevel.DEBUG, msg)
    } else {
        logger.log(LogLevel.DEBUG, msg, *args)
    }
}

fun Project.d(msg: String?, t: Throwable) {
    logger.log(LogLevel.DEBUG, msg, t)
}
//===Desc:INFO=====================================================================================

fun Project.i(msg: Any?) {
    logger.log(LogLevel.INFO, msg.toString())
}

fun Project.i(msg: String?, vararg args: Any) {
    if (args.isEmpty()) {
        logger.log(LogLevel.INFO, msg)
    } else {
        logger.log(LogLevel.INFO, msg, *args)
    }
}

fun Project.i(msg: String?, t: Throwable) {
    logger.log(LogLevel.INFO, msg, t)
}
//===Desc:LIFECYCLE=====================================================================================

fun Project.l(msg: Any?) {
    logger.log(LogLevel.LIFECYCLE, msg.toString())
}

fun Project.l(msg: String?, vararg args: Any) {
    if (args.isEmpty()) {
        logger.log(LogLevel.LIFECYCLE, msg)
    } else {
        logger.log(LogLevel.LIFECYCLE, msg, *args)
    }
}

fun Project.l(msg: String?, t: Throwable) {
    logger.log(LogLevel.LIFECYCLE, msg, t)
}
//===Desc:WARN=====================================================================================

fun Project.w(msg: Any?) {
    logger.log(LogLevel.WARN, msg.toString())
}

fun Project.w(msg: String?, vararg args: Any) {
    if (args.isEmpty()) {
        logger.log(LogLevel.WARN, msg)
    } else {
        logger.log(LogLevel.WARN, msg, *args)
    }
}

fun Project.w(msg: String?, t: Throwable) {
    logger.log(LogLevel.WARN, msg, t)
}

//===Desc:QUIET=====================================================================================

fun Project.q(msg: Any?) {
    logger.log(LogLevel.QUIET, msg.toString())
}

fun Project.q(msg: String?, vararg args: Any) {
    if (args.isEmpty()) {
        logger.log(LogLevel.QUIET, msg)
    } else {
        logger.log(LogLevel.QUIET, msg, *args)
    }
}

fun Project.q(msg: String?, t: Throwable) {
    logger.log(LogLevel.QUIET, msg, t)
}

//===Desc:ERROR=====================================================================================
fun Project.e(msg: Any?) {
    logger.log(LogLevel.ERROR, msg.toString())
}

fun Project.e(msg: String?, vararg args: Any) {
    if (args.isEmpty()) {
        logger.log(LogLevel.ERROR, msg)
    } else {
        logger.log(LogLevel.ERROR, msg, *args)
    }
}

fun Project.e(msg: String?, t: Throwable) {
    logger.log(LogLevel.ERROR, msg, t)
}
