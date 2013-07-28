import spark.SparkContext
import spark.SparkContext._

object SimpleJob extends Application {
  val logFile = "/var/log/system.log"
    val sc = new SparkContext("local[4]", "Simple Job", "/Users/twer/sdks/spark-0.7.3", 
        List("target/scala-2.9.2/spark_sample_2.9.2-0.1.jar"))
    val logData = sc.textFile(logFile).cache
    val numAs = logData.filter(line => line.contains("error")).count()
    val numBs = logData.filter(line => line.contains("warn")).count()
    println("============================================================")
    println("Lines with error: %s, Lines with warn: %s".format(numAs, numBs))
}
