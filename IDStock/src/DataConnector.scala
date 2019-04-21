import java.sql._

object DataConnector {

  val JDBC_DRIVER = "org.postgresql.Driver"
  val DB_URL = "jdbc:postgresql://localhost:5432/IDStock"

  // database credentials
  val USER = "postgres"
  val PASS = "1234"

  def connectionClass_GenericSP(SpName: String, SpParam: List[String]) {
    // jdbc driver name and database URL
    
    //Build Parameter Str
    var parameterstr = StringBuilder.newBuilder;
    for (par <- SpParam) {
      if (par == "true" || par == "false")
        parameterstr.append("" + par + "" + ",")
      else
        parameterstr.append("'" + par + "'" + ",")
    }

    var strLen = parameterstr.length

    val pararesult = parameterstr.substring(0, strLen - 1)

    //Build Exec Str
    val sql = """SELECT public."""" + SpName + """"(""" + pararesult + ");"

    var conn: Connection = null
    var stmt: Statement = null

    try {
      Class.forName(JDBC_DRIVER)
      conn = DriverManager.getConnection(DB_URL, USER, PASS)

      stmt = conn.createStatement
      val rs: ResultSet = stmt.executeQuery(sql)
//      while (rs.next) {
//        val id = rs.getString(SpName)
//        println(s"ReturnCode: $id")
//      }

      // cleanup
      stmt.close
      conn.close
    } catch {
      case se: SQLException => se.printStackTrace
      case e: Exception     => e.printStackTrace
    } finally {
      try {
        if (stmt != null) stmt.close
      } catch {
        case se2: SQLException => // nothing we can do
      }
      try {
        if (conn != null) conn.close
      } catch {
        case se: SQLException => se.printStackTrace
      } //end finally-try
    } //end try

  }

  def connectionClass_SecurityMaster(SpName: String, SpParam: List[String]) {
    // jdbc driver name and database URL

    val sql = """SELECT "Name", "StockType" FROM public."SecurityMaster";"""

    var conn: Connection = null
    var stmt: Statement = null

    try {
      Class.forName(JDBC_DRIVER)
      conn = DriverManager.getConnection(DB_URL, USER, PASS)

      stmt = conn.createStatement
      val rs: ResultSet = stmt.executeQuery(sql)
      while (rs.next) {
        val id = rs.getString("Name")
        val first = rs.getString("StockType")
        println(s"$id, $first")
      }

      // cleanup
      stmt.close
      conn.close
    } catch {
      case se: SQLException => se.printStackTrace
      case e: Exception     => e.printStackTrace
    } finally {
      try {
        if (stmt != null) stmt.close
      } catch {
        case se2: SQLException => // nothing we can do
      }
      try {
        if (conn != null) conn.close
      } catch {
        case se: SQLException => se.printStackTrace
      } //end finally-try
    } //end try

  }

}