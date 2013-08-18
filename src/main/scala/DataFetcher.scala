import com.stackmob.newman._
import com.stackmob.newman.dsl._
import java.net.URL

import org.json4s._
import org.json4s.native.JsonMethods._

import java.io.FileWriter
import java.io.File

object DataFetcher {
  implicit val httpClient = new ApacheHttpClient
  implicit val url = new URL("https://api.weibo.com/2/statuses/public_timeline.json?access_token=2.00iBWqyBoRXI9Dd771fe3b78HeRQMC&count=200")

  def fetch = {
    val response = GET(url).executeUnsafe.bodyString
    val json = parse(response)

    val texts = for { JString(text) <- json \\ "text" } yield text
    texts
  }

  def store(texts: List[String]) = {
    val writer = new FileWriter(new File("./src/main/resources/public-timeline.txt"), true)
    try {
        texts.foreach { text => writer.write(text + "\n") }
    } finally {
        writer.close
    }
  }

  def run = store(fetch)

  def batchRun(n: Int) = {
    n.times { run }
  }

  implicit def enrichInt(n: Int): RInt = new RInt(n)
}

class RInt(wrapped: Int) {
  def times(block: => Unit)  {
    (0 to wrapped).foreach { n => 
      print(".")
      block 
    }
  }
}
