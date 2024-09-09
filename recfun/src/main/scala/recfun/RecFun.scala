package recfun

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do
        print(s"${pascal(col, row)} ")
      println()

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = 
    if (c == 0) 1
    else if (c == r) 1
    else pascal(c-1,r-1) + pascal(c,r-1)

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = 
    def check(chars: List[Char], count: Int): Boolean =
      if (chars.isEmpty)
        if (count != 0) false  
        else true
      else 
        if (count < 0) false 
        else 
          if (chars.head == '(')
            check(chars.tail, count + 1)
          else if (chars.head == ')')
            check(chars.tail, count - 1)
          else
            check(chars.tail, count )



    check(chars, 0)
  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int =
    if (money == 0) 1
    else if (money < 0) 0
    else if (coins.isEmpty == true) 0
    else 
      countChange(money - coins.head, coins) + countChange(money, coins.tail)


