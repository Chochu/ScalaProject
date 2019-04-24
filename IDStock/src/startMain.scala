import play.api.libs.json._

object startMain {

  var token = "pk_1e367983fe2f4475b05f0cfbf33756a3"

  def main(args: Array[String]): Unit = {

    try {
      println("Launch SecurityMaster")
      ExecUpdateFunc(UpdateSecurityMaster)
      println("Launch SecurityMaster OTC")
      ExecUpdateFunc(UpdateSecurityMasterOTC)
      println("Launch Exchange Master")
      ExecUpdateFunc(UpdateExchangeMaster)

    } catch {
      case ioe: java.io.IOException             => prt("IO Exception")
      case ste: java.net.SocketTimeoutException => prt("SocketTimeout Exception")
      case _: Throwable                         => println("Got some other kind of exception" + _)
    }

  }
  
  //Execute func as a Thread
  def ExecUpdateFunc(callback:() => Unit){
   new Thread(new Runnable{
        def run{
          callback()
        }
      }).start()
  }
  

  def UpdateExchangeMaster() {
    var webstr = "https://cloud.iexapis.com/beta/ref-data/exchanges?token=YOUR_TOKEN_HERE"

    var returnTxt = get(webstr.replace("YOUR_TOKEN_HERE", token))

    println("UpdateExchangeMaster: Parsing Json to List")
   
    val jsonList: List[JsValue] = Json.parse(returnTxt).as[List[JsValue]]

    println("UpdateExchangeMaster: Looping List")
    for (acct <- jsonList) {
      val paraList: List[String] = List(acct("exchange").as[String], 
                                        acct("region").as[String], 
                                        acct("description").as[String], 
                                        "U")
     
      DataConnector.connectionClass_GenericSP("addupdatedeleteem", paraList)

    }
    println("UpdateExchangeMaster: Function Completed")
  }
  
  def UpdateSecurityMasterOTC() {
    println("UpdateSecurityMasterOTC: Getting Web Json")
    var webstr = "https://cloud.iexapis.com/beta/ref-data/otc/symbols?token=YOUR_TOKEN_HERE"

    var returnTxt = get(webstr.replace("YOUR_TOKEN_HERE", token))

    println("UpdateSecurityMasterOTC: Parsing Json to List")
    
    val jsonList: List[JsValue] = Json.parse(returnTxt).as[List[JsValue]]

    println("UpdateSecurityMasterOTC: Looping List")
    for (acct <- jsonList) {      
      val paraList: List[String] = List(acct("symbol").as[String], 
                                        acct("exchange").as[String], 
                                        HelperClass.removeSingleQuote(acct("name").as[String]), 
                                        acct("date").as[String], 
                                        acct("type").as[String], 
                                        acct("iexId").as[String], 
                                        acct("region").as[String], 
                                        acct("currency").as[String], 
                                        HelperClass.StringifyBoolean(acct("isEnabled").as[Boolean]), 
                                        "U")
                                        
      DataConnector.connectionClass_GenericSP("addupdatedeletesm", paraList)
    }
    println("UpdateSecurityMasterOTC: Function Completed")
  }

  def UpdateSecurityMaster() {
    println("UpdateSecurityMaster: Getting Web Json")
    var webstr = "https://cloud.iexapis.com/beta/ref-data/symbols?token=YOUR_TOKEN_HERE"

    var returnTxt = get(webstr.replace("YOUR_TOKEN_HERE", token))

    println("UpdateSecurityMaster: Parsing Json to List")   
    val jsonList: List[JsValue] = Json.parse(returnTxt).as[List[JsValue]]

    println("UpdateSecurityMaster: Looping List")
    for (acct <- jsonList) {
      val paraList: List[String] = List(acct("symbol").as[String], 
                                        acct("exchange").as[String], 
                                        HelperClass.removeSingleQuote(acct("name").as[String]), 
                                        acct("date").as[String], 
                                        acct("type").as[String], 
                                        acct("iexId").as[String], 
                                        acct("region").as[String], 
                                        acct("currency").as[String], 
                                        HelperClass.StringifyBoolean(acct("isEnabled").as[Boolean]),
                                        "U")
     
      DataConnector.connectionClass_GenericSP("addupdatedeletesm", paraList)
    }
    println("UpdateSecurityMaster: Function Completed")
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