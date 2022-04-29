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
        ),
        getSample(
            "ecg-20210704-220531",
            KardiaReport(
                dateTime = "Sunday, 4 July 2021 at 22:05:31",
                heartRate = "--- BPM",
                duration = "30s",
                notes = "",
                determination = "Unreadable",
            ),
        ),
        getSample(
            "ecg-20211123-153747",
            KardiaReport(
                dateTime = "Tuesday, 23 November 2021 at 15:37:47",
                heartRate = "59 BPM",
                duration = "30s",
                notes = "",
                determination = "Normal Sinus Rhythm",
            ),
        ),
        getSample(
            "ecg-20220308-051823",
            KardiaReport(
                dateTime = "Tuesday, 8 March 2022 at 05:18:23",
                heartRate = "95 BPM",
                duration = "30s",
                notes = "",
                determination = "Normal Sinus Rhythm",
            ),
        ),
    )

    private fun getSample(
        name: String,
        expectedReport: KardiaReport
    ) = Sample(name, getFile("$name.pdf"), expectedReport)

    private fun getFile(name: String): File {
        return File(javaClass.classLoader.getResource(name).file)
    }
}