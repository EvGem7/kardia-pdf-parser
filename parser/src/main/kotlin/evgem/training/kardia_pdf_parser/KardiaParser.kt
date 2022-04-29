package evgem.training.kardia_pdf_parser

import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.parser.PdfTextExtractor
import evgem.training.kardia_pdf_parser.model.KardiaReport
import java.io.File

interface KardiaParser {
    fun parse(file: File): KardiaReport?
}

class KardiaParserImpl : KardiaParser {

    companion object {

        private const val KEY_RECORDED = "Recorded:"
        private const val KEY_NOTES = "Notes:"
        private const val KEY_HEART_RATE = "Heart Rate:"
        private const val KEY_DETERMINATION = "Kardia Determination:|Kardia Advanced"
        private const val KEY_DURATION = "Duration:"

        // junk that must be filtered out
        private const val KEY_JUNK = "\\*Kardia|Determination:"

        private const val KEYS = "$KEY_RECORDED|$KEY_NOTES|$KEY_HEART_RATE|$KEY_DETERMINATION|$KEY_DURATION"
    }

    override fun parse(file: File): KardiaReport? {
        val reader = PdfReader(file.name)
        try {
            for (pageNumber in 1..reader.numberOfPages) {
                val pageText = PdfTextExtractor.getTextFromPage(reader, pageNumber)
                parsePage(pageText)?.let {
                    return it
                }
            }
        } finally {
            reader.close()
        }
        return null
    }

    private fun parsePage(text: String): KardiaReport? {
        val simplified = text
            .replace(Regex("$KEYS|$KEY_JUNK")) { "\n${it.value}" }
        val regex = Regex("^($KEYS)(.+)$", RegexOption.MULTILINE)
        val map = HashMap<String, String>()
        regex.findAll(simplified).forEach {
            map[it.groupValues[1]] = it.groupValues[2].trim()
        }
        return KardiaReport(
            dateTime = map.getByKey(KEY_RECORDED),
            heartRate = map.getByKey(KEY_HEART_RATE),
            duration = map.getByKey(KEY_DURATION),
            notes = map.getByKey(KEY_NOTES),
            determination = map.getByKey(KEY_DETERMINATION),
        )
    }

    private fun Map<String, String>.getByKey(key: String): String {
        val keys = key.split('|')
        for (k in keys) {
            get(k)?.let { return it }
        }
        return ""
    }
}
