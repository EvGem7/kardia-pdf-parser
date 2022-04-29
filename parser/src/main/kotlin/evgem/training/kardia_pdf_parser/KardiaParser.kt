package evgem.training.kardia_pdf_parser

import evgem.training.kardia_pdf_parser.model.KardiaReport
import java.io.File

interface KardiaParser {
    fun parse(file: File): KardiaReport?
}