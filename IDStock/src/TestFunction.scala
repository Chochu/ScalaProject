
//https://stackoverflow.com/questions/51680206/parse-json-array-in-scala
object TestFunction {
    def testListst(paraname: String ,paraList: List[String]) {
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

    val sql = """SELECT public."""" + paraname + """"(""" + result + ");"
    println(sql)
  }

}
