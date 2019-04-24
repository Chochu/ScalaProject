

object HelperClass {
  
  /***
   * Remove quote from name
   */
  def removeSingleQuote(name: String): String = {
    return name.replace("'", "")
  }

  /***
   * Convert boolean to string
   */
  def StringifyBoolean(booleanVar: Boolean): String = {
    if (booleanVar)
      return "true"
    else
      return "false"
  }
  
}