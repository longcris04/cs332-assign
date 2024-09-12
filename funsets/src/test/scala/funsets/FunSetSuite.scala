package funsets

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite extends munit.FunSuite:

  import FunSets.*

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets:
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s12 = union(s1,s2)
    val s13 = union(s1,s3)
    val s23 = union(s2,s3)
    val s123 = union(s1, s23)
    val s4 = singletonSet(4)
    val s0 = singletonSet(0)
    val s024 = union(s0,union(s2,s4)) 
  /**
   * This test is currently disabled (by using .ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remove the
   * .ignore annotation.
   */

    test("singleton set one contains one") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets:
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton 1")
      assert(contains(s2, 2), "Singleton 2")
      assert(contains(s3, 3), "Singleton 3")
  }

  test("union contains all elements of each set") {
    new TestSets:
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
  }

  test("test for intersect and diff") {
    new TestSets:
     
      val s6 = intersect(s12, s13) 
      assert(contains(s6, 1), "s6 must contain 1")
      val s7 = diff(s12, s23)
      assert(contains(s7, 1), "s7 must contains 1")
      
     
  }

  test("test for filter") {
    new TestSets:
      val s8 = filter(s123, x => (x%2 == 0))
      assert(contains(s8,2), "s8 must contains 2 using filter devisible by 2")
  }
 
  test("test for forall") {
    new TestSets:
      
      
      assert(forall(s024, x => ((x%2) == 0)), "s024 must contains all even number")
  }

  test("test for exists") {
    new TestSets:
      assert(exists(s123, x => (x%2 == 0)), "s123 must contains even number")
  }
  
  test("test for map") {
    new TestSets:
      val s_map = map(s123, x => x*x)
      assert((contains(s_map,1) && contains(s_map,4) && contains(s_map,9)), "s123 contain 1,4,9")
  }

  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
