package com.github.lee.gradle.plugins.pgyer.extension

import org.gradle.api.Project

open class PgyerExtension {
    var userKey: String? = ""
    var apiKey: String? = ""

    /**
     * (选填)应用安装方式
     * 值为(1,2,3，默认为1 公开安装)。
     * 1：公开安装，2：密码安装，3：邀请安装
     */
    var installType: Int = 1

    /**
     * (选填) 设置App安装密码，密码为空时默认公开安装
     */
    var password: String? = ""

    /**
     * (选填) 版本更新描述，请传空字符串，或不传。
     */
    var updateDescription: String? = ""

    /**
     * (选填)是否设置安装有效期，
     * 值为：1 设置有效时间， 2 长期有效，如果不填写不修改上一次的设置
     */
    var installDate: Int? = 1

    /**
     * (选填)安装有效期开始时间，字符串型，如：2018-01-01
     */
    var installStartDate: String? = ""

    /**
     *(选填)安装有效期结束时间，字符串型，如：2018-12-31
     */
    var installEndDate: String? = ""

    /**
     *(选填)所需更新的指定渠道的下载短链接，只可指定一个渠道，字符串型，如：abcd
     */
    var channelShortcut: String? = ""


}

fun getPgyerExtension(project: Project): PgyerExtension? {
    return project.extensions.getByType(PgyerExtension::class.java)
}