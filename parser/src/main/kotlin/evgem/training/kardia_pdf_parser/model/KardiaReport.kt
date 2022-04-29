package evgem.training.kardia_pdf_parser.model

data class KardiaReport(
    val dateTime: String,
    val heartRate: String,
    val duration: String,
    val notes: String,
    val determination: String,
)
