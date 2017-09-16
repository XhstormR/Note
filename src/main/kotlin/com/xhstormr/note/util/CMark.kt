package com.xhstormr.note.util

object CMark {
    private val COMMAND = arrayOf("D:/Work/BLOG/HUGO/static/uploads/bin/cmark.exe")
    private val PROCESS_BUILDER = ProcessBuilder(*COMMAND).redirectErrorStream(true)

    fun execute(data: List<String>): List<String> {
        val process = PROCESS_BUILDER.start()

        process.outputStream.bufferedWriter().use { out -> data.forEach { out.write(it + "\n") } }

        process.inputStream.bufferedReader().useLines { return it.toList() }
    }
}
