package com.learningandroid.log

import android.text.TextUtils
import android.util.Log
import timber.log.Timber

/**
 * デバッグ用カスタムログ出力
 * 参考：https://qiita.com/shiraji/items/5815bfe667d042051119
 */
class AppTree : Timber.DebugTree() {

    companion object {
        private const val MAX_LOG_LENGTH = 4000
        private const val CALLER_INFO_FORMAT = " at %s(%s:%s)"
    }
    private var callerInfo: String? = null

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        callerInfo = getCallerInfo(Throwable().stackTrace)
        if (message.length < MAX_LOG_LENGTH) {
            printSingleLine(priority, tag, message + callerInfo)
        } else {
            printMultipleLines(priority, tag, message)
        }
    }

    private fun printMultipleLines(priority: Int, tag: String?, message: String) {
        var i = 0
        val length = message.length
        while (i < length) {
            var newline = message.indexOf('\n', i)
            newline = if (newline != -1) newline else length
            do {
                val end = Math.min(newline, i + MAX_LOG_LENGTH)
                val part = message.substring(i, end)
                printSingleLine(priority, tag, part)
                i = end
            } while (i < newline)
            i++
        }
        if (!TextUtils.isEmpty(callerInfo)) {
            printSingleLine(priority, tag, callerInfo)
        }
    }

    private fun printSingleLine(priority: Int, tag: String?, message: String?) {
        if (priority == Log.ASSERT) {
            Timber.wtf(message)
        } else {
            message?.let { Log.println(priority, tag, it) }
        }
    }

    private fun getCallerInfo(stacks: Array<StackTraceElement>?): String =
        if (stacks == null || stacks.size < 5) "" else formatForLogCat(stacks[5])

    private fun formatForLogCat(stack: StackTraceElement): String {
        val className = stack.className
        val packageName = className.substring(0, className.lastIndexOf("."))
        return String.format(CALLER_INFO_FORMAT, packageName, stack.fileName, stack.lineNumber)
    }
}