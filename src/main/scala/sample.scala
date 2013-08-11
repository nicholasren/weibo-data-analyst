import spark.SparkContext
import spark.SparkContext._

object SimpleJob extends Application {
  val logFile = "/var/log/system.log"
    val sc = new SparkContext("local[4]", "Simple Job", "/Users/twer/sdks/spark-0.7.3", 
        List("target/scala-2.9.2/spark_sample_2.9.2-0.1.jar"))
    val lines = sc.textFile(logFile).cache

    val counts = lines.flatMap(_.split(" ")).map( word => (word, 1)).reduceByKey(_+_)
    println("word counts =======>" + counts.getClass)
    counts.saveAsTextFile("/tmp/word-counts.txt")
}
