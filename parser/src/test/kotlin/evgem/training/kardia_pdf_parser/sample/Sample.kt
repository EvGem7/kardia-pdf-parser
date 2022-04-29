package evgem.training.kardia_pdf_parser.sample

import evgem.training.kardia_pdf_parser.model.KardiaReport
import java.io.File

data class Sample(
    val name: String,
    val file: File,
    val expectedReport: KardiaReport,
)
