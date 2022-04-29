package evgem.training.kardia_pdf_parser.sample

import evgem.training.kardia_pdf_parser.KardiaParser
import evgem.training.kardia_pdf_parser.model.KardiaReport
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class SamplesParsingTest {

    private lateinit var parser: KardiaParser

    @BeforeEach
    fun before() {
        // stub
        parser = object : KardiaParser {
            override fun parse(file: File): KardiaReport? = null
        }
    }

    @Test
    fun `test all samples parsing`() {
        SamplesProvider.samples.forEach {
            assertEquals(it.expectedReport, parser.parse(it.file), "failed to parse sample ${it.name}")
        }
    }
}