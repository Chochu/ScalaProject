import play.api.libs.json._

object startMain {

  def main(args: Array[String]): Unit = {
    var token = "pk_1e367983fe2f4475b05f0cfbf33756a3"
    var webadd = "https://cloud.iexapis.com/beta/ref-data/symbols?token=YOUR_TOKEN_HERE"

    try {
      println("Getting Web Json")
      var returnTxt = get(webadd.replace("YOUR_TOKEN_HERE", token))
      //      var returnTxt = getText2()
      
      println("Parsing Json to List")
      //https://stackoverflow.com/questions/51680206/parse-json-array-in-scala
      val jsonList: List[JsValue] = Json.parse(returnTxt).as[List[JsValue]]
      
      println("Looping List")
      for (acct <- jsonList) {
        //Remove quote from name
        var name: String = acct("name").as[String].replace("'", "")
        //Convert boolean to string
        var isEnabled: String = if (acct("isEnabled").as[Boolean])
          "true"
        else
          "false"
          
        val paraList: List[String] = List(acct("symbol").as[String], acct("exchange").as[String], name, acct("date").as[String], acct("type").as[String], acct("iexId").as[String], acct("region").as[String], acct("currency").as[String], isEnabled, "U")
        //        testListst(paraList)
        DataConnector.connectionClass_GenericSP("addupdatedeletesm", paraList)

      }

      println("Completed")

    } catch {
      case ioe: java.io.IOException             => prt("IO Exception")
      case ste: java.net.SocketTimeoutException => prt("SocketTimeout Exception")
      case _: Throwable                         => println("Got some other kind of exception" + _)
    }

  }

  def testListst(paraList: List[String]) {
    //    var arrlist: List[String] = List("a", "b", "true")
    var parameterstr = StringBuilder.newBuilder;
    for (par <- paraList) {
      if (par == "true" || par == "false")
        parameterstr.append("" + par + "" + ",")
      else
        parameterstr.append("'" + par + "'" + ",")
    }

    var strLen = parameterstr.length

    val result = parameterstr.substring(0, strLen - 1)

    val sql = """SELECT public."""" + "addupdatedeletesm" + """"(""" + result + ");"
    println(sql)
  }

  def getText2(): String = {
    return """[
    {
        "symbol": "A",
        "exchange": "NYS",
        "name": "Agilent Technologies Inc.",
        "date": "2019-04-20",
        "type": "cs",
        "iexId": "IEX_46574843354B2D52",
        "region": "US",
        "currency": "USD",
        "isEnabled": true
    },
    {
        "symbol": "AA",
        "exchange": "NYS",
        "name": "Alcoa Corp.",
        "date": "2019-04-20",
        "type": "cs",
        "iexId": "IEX_4238333734532D52",
        "region": "US",
        "currency": "USD",
        "isEnabled": true
    },
    {
        "symbol": "AAAU",
        "exchange": "PSE",
        "name": "Perth Mint Physical Gold ETF",
        "date": "2019-04-20",
        "type": "et",
        "iexId": "IEX_474B433136332D52",
        "region": "US",
        "currency": "USD",
        "isEnabled": true
    },
    {
        "symbol": "AABA",
        "exchange": "NAS",
        "name": "Altaba Inc.",
        "date": "2019-04-20",
        "type": "cef",
        "iexId": "IEX_4E5434354A302D52",
        "region": "US",
        "currency": "USD",
        "isEnabled": true
    },
    {
        "symbol": "AAC",
        "exchange": "NYS",
        "name": "AAC Holdings Inc.",
        "date": "2019-04-20",
        "type": "cs",
        "iexId": "IEX_4843364642592D52",
        "region": "US",
        "currency": "USD",
        "isEnabled": true
    },
    {
        "symbol": "AADR",
        "exchange": "PSE",
        "name": "AdvisorShares Dorsey Wright ADR ETF",
        "date": "2019-04-20",
        "type": "et",
        "iexId": "IEX_5253355435362D52",
        "region": "US",
        "currency": "USD",
        "isEnabled": true
    },
    {
        "symbol": "AAL",
        "exchange": "NAS",
        "name": "American Airlines Group Inc.",
        "date": "2019-04-20",
        "type": "cs",
        "iexId": "IEX_4353464A535A2D52",
        "region": "US",
        "currency": "USD",
        "isEnabled": true
    },
    {
        "symbol": "AAMC",
        "exchange": "ASE",
        "name": "Altisource Asset Management Corp.",
        "date": "2019-04-20",
        "type": "cs",
        "iexId": "IEX_5442323844432D52",
        "region": "US",
        "currency": "USD",
        "isEnabled": true
    },
    {
        "symbol": "AAME",
        "exchange": "NAS",
        "name": "Atlantic American Corporation",
        "date": "2019-04-20",
        "type": "cs",
        "iexId": "IEX_5737584C53442D52",
        "region": "US",
        "currency": "USD",
        "isEnabled": true
    },
    {
        "symbol": "AAN",
        "exchange": "NYS",
        "name": "Aaron's Inc.",
        "date": "2019-04-20",
        "type": "cs",
        "iexId": "IEX_534D305A30592D52",
        "region": "US",
        "currency": "USD",
        "isEnabled": true
    }]"""
  }
  def getText(): String = {
    return """[
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