How to use:

`java -jar parser.jar path/to/file.pdf`

Example output"

```
$ java -jar parser-1.0-SNAPSHOT-all.jar ~/IdeaProjects/kardia-pdf-parser/parser/src/test/resources/ecg-20210603-141906.pdf 
{"dateTime":"Thursday, 3 June 2021 at 14:19:06","heartRate":"67 BPM","duration":"30s","notes":"Dhubdtb free h c","determination":"Normal Sinus Rhythm"}
```

To build a JAR run the `shadowJar` gradle task:

`./gradlew shadowJar`

You can find the JAR file in the `$projectRoot/parser/build/libs` directory.
