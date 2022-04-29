package evgem.training.kardia_pdf_parser.sample

import evgem.training.kardia_pdf_parser.model.KardiaReport
import java.io.File

object SamplesProvider {

    val samples: List<Sample> = listOf(
        getSample(
            "ecg-20210603-141906",
            KardiaReport(
                dateTime = "Thursday, 3 June 2021 at 14:19:06",
                heartRate = "67 BPM",
                duration = "30s",
                notes = "Dhubdtb free h c",
                determination = "Normal Sinus Rhythm",
            ),
        )
    )

    private fun getSample(
        name: String,
        expectedReport: KardiaReport
    ) = Sample(name, getFile("$name.pdf"), expectedReport)

    private fun getFile(name: String): File {
        return File(javaClass.classLoader.getResource(name).file)
    }
}