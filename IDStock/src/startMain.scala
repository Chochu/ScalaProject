import scala.util

object startMain {

  def main(args: Array[String]): Unit = {
    var token = "pk_1e367983fe2f4475b05f0cfbf33756a3"
    var webadd = "https://cloud.iexapis.com/beta/stock/aapl/quote?token=YOUR_TOKEN_HERE"

    try {

      //var returnTxt = get(webadd.replace("YOUR_TOKEN_HERE", token))
      var returnTxt = """[
    {
        "mic": "ARCX",
        "name": "NYSE Arca",
        "longName": "NYSE ARCA",
        "tapeId": "P",
        "oatsId": "XP",
        "type": "equities"
    },
    {
        "mic": "EDGX",
        "name": "Cboe EDGX",
        "longName": "Cboe EDGX US Equities Exchange",
        "tapeId": "K",
        "oatsId": "XK",
        "type": "equities"
    },
    {
        "mic": "BATS",
        "name": "Cboe BZX",
        "longName": "Cboe BZX US Equities Exchange",
        "tapeId": "Z",
        "oatsId": "XZ",
        "type": "equities"
    },
    {
        "mic": "BATY",
        "name": "Cboe BYX",
        "longName": "Cboe BYX US Equities Exchange",
        "tapeId": "Y",
        "oatsId": "XY",
        "type": "equities"
    },
    {
        "mic": "EDGA",
        "name": "Cboe EDGA",
        "longName": "Cboe EDGA US Equities Exchange",
        "tapeId": "J",
        "oatsId": "XJ",
        "type": "equities"
    },
    {
        "mic": "IEXG",
        "name": "IEX",
        "longName": "Investors Exchange",
        "tapeId": "V",
        "oatsId": "XV",
        "type": "equities"
    },
    {
        "mic": "XBOS",
        "name": "Nasdaq OMX BX",
        "longName": "Nasdaq OMX BX",
        "tapeId": "B",
        "oatsId": "XB",
        "type": "equities"
    },
    {
        "mic": "XPHL",
        "name": "Nasdaq OMX PHLX",
        "longName": "Nasdaq OMX PHLX",
        "tapeId": "X",
        "oatsId": "XX",
        "type": "equities"
    },
    {
        "mic": "XCHI",
        "name": "NYSE Chicago",
        "longName": "NYSE Chicago",
        "tapeId": "M",
        "oatsId": "XM",
        "type": "equities"
    },
    {
        "mic": "XASE",
        "name": "NYSE American",
        "longName": "NYSE American",
        "tapeId": "A",
        "oatsId": "XA",
        "type": "equities"
    },
    {
        "mic": "XCIS",
        "name": "NYSE National",
        "longName": "NYSE National",
        "tapeId": "C",
        "oatsId": "XC",
        "type": "equities"
    },
    {
        "mic": "XNYS",
        "name": "NYSE",
        "longName": "New York Stock Exchange",
        "tapeId": "N",
        "oatsId": "XN",
        "type": "equities"
    },
    {
        "mic": "XNGS",
        "name": "Nasdaq",
        "longName": "Nasdaq",
        "tapeId": "T",
        "oatsId": "XQ",
        "type": "equities"
    },
    {
        "mic": "OTCM",
        "name": "US OTC",
        "longName": "OTC Markets",
        "tapeId": "",
        "oatsId": "",
        "type": "otc"
    },
    {
        "mic": "TRF",
        "name": "Off Exchange",
        "tapeId": "",
        "oatsId": "",
        "type": "equities"
    }
]"""

      var sd = JSON.parseFull(returnTxt)
      
//      sd.foreach(element => )
      
      var sdassd = "asd"
      //var sd2: List[Map[String, String]] = sd.headOption

      //     val jsonString: String = """[
      //{"device":"Samsung S8","android":true},
      //{"device":"iPhone8","android":false},
      //{"device":"MacBook Air Pro","android":false},
      //{"device":"Dell XPS","android":false}]"""

      //      val jsonList: List[Map(String, String)] = Json.parse(jsonString).as[List[JsValue]]
      //val filteredList: List[JsValue] = jsonList.filter(json => (json \ "android").as[Boolean])

      //prt(sd.get.asInstanceOf[Map[String, Any]]("symbol").asInstanceOf[String])
      println("LOL")

    } catch {
      case ioe: java.io.IOException             => prt("IO Exception")
      case ste: java.net.SocketTimeoutException => prt("SocketTimeout Exception")
      case _: Throwable                         => println("Got some other kind of exception")
    }
    //    DataConnector.connectionClass
  }
  
  def flatten(ls: List[Any]): List[Any] = ls flatMap {
    case i: List[_] => flatten(i)
    case e => List(e)
  }

  def prt(str: String): Unit = {
    println(str) // Hello, James
  }

  /**
   * Returns the text (content) from a REST URL as a String.
   * Inspired by http://matthewkwong.blogspot.com/2009/09/scala-scalaiosource-fromurl-blockshangs.html
   * and http://alvinalexander.com/blog/post/java/how-open-url-read-contents-httpurl-connection-java
   *
   * The `connectTimeout` and `readTimeout` comes from the Java URLConnection
   * class Javadoc.
   * @param url The full URL to connect to.
   * @param connectTimeout Sets a specified timeout value, in milliseconds,
   * to be used when opening a communications link to the resource referenced
   * by this URLConnection. If the timeout expires before the connection can
   * be established, a java.net.SocketTimeoutException
   * is raised. A timeout of zero is interpreted as an infinite timeout.
   * Defaults to 5000 ms.
   * @param readTimeout If the timeout expires before there is data available
   * for read, a java.net.SocketTimeoutException is raised. A timeout of zero
   * is interpreted as an infinite timeout. Defaults to 5000 ms.
   * @param requestMethod Defaults to "GET". (Other methods have not been tested.)
   *
   * @example get("http://www.example.com/getInfo")
   * @example get("http://www.example.com/getInfo", 5000)
   * @example get("http://www.example.com/getInfo", 5000, 5000)
   */
  @throws(classOf[java.io.IOException])
  @throws(classOf[java.net.SocketTimeoutException])
  def get(
    url:            String,
    connectTimeout: Int    = 5000,
    readTimeout:    Int    = 5000,
    requestMethod:  String = "GET") =
    {
      import java.net.{ URL, HttpURLConnection }
      val connection = (new URL(url)).openConnection.asInstanceOf[HttpURLConnection]
      connection.setConnectTimeout(connectTimeout)
      connection.setReadTimeout(readTimeout)
      connection.setRequestMethod(requestMethod)
      val inputStream = connection.getInputStream
      val content = io.Source.fromInputStream(inputStream).mkString
      if (inputStream != null) inputStream.close
      content
    }

}