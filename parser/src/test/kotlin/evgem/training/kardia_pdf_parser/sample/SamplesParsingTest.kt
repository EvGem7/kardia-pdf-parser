package evgem.training.kardia_pdf_parser.sample

import evgem.training.kardia_pdf_parser.KardiaParser
import evgem.training.kardia_pdf_parser.KardiaParserImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SamplesParsingTest {

    private lateinit var parser: KardiaParser

    @BeforeEach
    fun before() {
        // stub
        parser = KardiaParserImpl()
    }

    @Test
    fun `test all samples parsing`() {
        assertAll(
            SamplesProvider.samples.map {
                {
                    val parsed = parser.parse(it.file)
                    assertNotNull(parsed)

                    val msg = "failed to parse sample ${it.name}"
                    assertEquals(it.expectedReport.dateTime, parsed.dateTime, msg)
                    assertEquals(it.expectedReport.heartRate, parsed.heartRate, msg)
                    assertEquals(it.expectedReport.duration, parsed.duration, msg)
                    assertEquals(it.expectedReport.notes, parsed.notes, msg)
                    assertEquals(it.expectedReport.determination, parsed.determination, msg)
                }
            }
        )
    }
}