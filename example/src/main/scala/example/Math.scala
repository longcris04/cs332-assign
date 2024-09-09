package example

// import example.Math

object Math:

    def sqrt(x: Double) = {


        def FindSqrt(guess:Double): Double =
            if (SmallEnough(guess)) guess
            else FindSqrt(Update(guess))

        def SmallEnough(guess: Double): Boolean =
            if (math.abs(guess * guess - x) < 0.0001) true
            else false

        def Update(guess: Double): Double =
            (guess * guess + 2)/(2 * guess)

        FindSqrt(1.0)
    }