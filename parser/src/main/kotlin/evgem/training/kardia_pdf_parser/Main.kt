package evgem.training.kardia_pdf_parser

import com.google.gson.Gson
import sun.misc.Unsafe
import java.io.File
import java.lang.reflect.Field

fun main(args: Array<String>) {
    disableWarning()
    if (args.isEmpty()) {
        return println("Please provide path to a PDF file")
    }
    val file = File(args.first())
    if (!file.exists()) {
        return println("File does not exist")
    }
    val parser = KardiaParserImpl()
    val report = parser.parse(file)
    println(Gson().toJson(report))
}

private fun disableWarning() {
    try {
        val theUnsafe: Field = Unsafe::class.java.getDeclaredField("theUnsafe")
        theUnsafe.setAccessible(true)
        val u: Unsafe = theUnsafe.get(null) as Unsafe
        val cls = Class.forName("jdk.internal.module.IllegalAccessLogger")
        val logger: Field = cls.getDeclaredField("logger")
        u.putObjectVolatile(cls, u.staticFieldOffset(logger), null)
    } catch (e: Exception) {
        // ignore
    }
}
