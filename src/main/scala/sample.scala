import spark.SparkContext
import spark.SparkContext._
import java.util.regex._

object SimpleJob {
  def tagIn = { line: String =>
      val regex = Pattern.compile(".*#([^#]+)#.+")
      val m = regex.matcher(line)
      if(m.find()) m.group(1) else ""
  }


  def main(args: Array[String]) = {
    val logFile = "src/main/resources/public-timeline.txt"
    //val logFile = "src/main/resources/sample-timeline.txt"
    val sc = new SparkContext("local[8]", "Simple Job", "/Users/twer/sdks/spark-0.7.3",
        List("target/scala-2.9.2/spark_sample_2.9.2-0.1.jar"))

    val lines = sc.textFile(logFile).cache
    val tags =  lines.map(tagIn(_)).filter(! _.isEmpty).map(tag => (tag, 1)).reduceByKey(_ + _)

    tags.saveAsTextFile("weibo-tags")

    System.exit(0)
  }
}
