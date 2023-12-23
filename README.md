## Part 1
We run the program with inputs (4,7,9) and after profiling, we realize that `temp` function takes most of processing time.

<img width="1728" alt="se-lab8-1" src="https://github.com/alirezaei1380/SE-LAB-8/assets/59190480/177aba3f-34f0-48fa-838f-cd0d3ca44139">
<img width="1728" alt="se-lab8-2" src="https://github.com/alirezaei1380/SE-LAB-8/assets/59190480/5fcbcd59-ed93-445f-bf0b-c771c4bc4221">

After further investigations, it seems like we spend significant time in `add` function for our dynamic array.

<img width="1728" alt="se-lan8-3" src="https://github.com/alirezaei1380/SE-LAB-8/assets/59190480/5c9bafa7-2fd7-4060-9bb1-d5a8a77a559b">

So we change the code to allocate ram in the beginning:

```java
ArrayList a = new ArrayList(10000 * 20000);
```

<img width="1728" alt="se-lab8-4" src="https://github.com/alirezaei1380/SE-LAB-8/assets/59190480/23c71da9-895b-4afa-86e3-17f16f00f6a1">
<img width="1728" alt="se-lab8-5" src="https://github.com/alirezaei1380/SE-LAB-8/assets/59190480/02e2cca6-5362-4ecc-8bcb-cdd823f60c21">

We use a simple fixed size array for further improvement.

```java
int[] a = new int[10000 * 20000];
int index = 0;
for (int i = 0; i < 10000; i++)
{
    for (int j = 0; j < 20000; j++) {
        a[index] = i + j;
        index++;
    }
}
```

<img width="1728" alt="se-lab8-6" src="https://github.com/alirezaei1380/SE-LAB-8/assets/59190480/d25a39c1-c62f-4aae-81d8-11424a1d9717">
<img width="1728" alt="se-lab8-7" src="https://github.com/alirezaei1380/SE-LAB-8/assets/59190480/13bfb691-0e9a-4b72-93cc-503738e5bba0">

As you can see in the pictures, cpu time has improved significantly.


## Part 2

For this part, we are using a code that calculates prime numbers in the 1 to 10000 range. Here is the initial code we implemented:

```java
public class PrimeCalculator {
    public static void main(String[] args) {
        int limit = 10000;
        System.out.println("First prime numbers up to " + limit + ":");
        for (int i = 2; i <= limit; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        // Check from 2 to number-1 (inefficient on purpose)
        for (int i = 2; i < number; i++) {
            // Inner loop to add unnecessary complexity
            for (int j = 1; j <= i; j++) {
                if (i * j == number) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

So, after running this code using our profiler app, we have this result:
<img width="1398" alt="Screen Shot 1402-10-02 at 21 41 35" src="https://github.com/alirezaei1380/SE-LAB-8/assets/60629485/26faa183-9dec-4ff1-afa5-a62ccac26320">
And here is CPU usage:
<img width="1398" alt="Screen Shot 1402-10-02 at 21 41 22" src="https://github.com/alirezaei1380/SE-LAB-8/assets/60629485/4b34134f-4033-42a2-ab3a-5717e2907198">


Now, we try to optimize it by adding a list of booleans that handles a mapping of <number as an index, if it is prime>. This means we set the prime boolean false or all multiplication of values we iterate, By doing this we won't recheck the values.
Here is the updated code:

```java
import java.util.Arrays;

public class PrimeCalculator {
    public static void main(String[] args) {
        int limit = 10000;
        boolean[] isPrime = sieveOfEratosthenes(limit);

        System.out.println("Prime numbers up to " + limit + ":");
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                System.out.print(i + " ");
            }
        }
    }

    public static boolean[] sieveOfEratosthenes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        return isPrime;
    }
}
```
And now, here is the result by using the profiler app. We have a significant reduction in CPU usage:

<img width="1398" alt="Screen Shot 1402-10-02 at 21 43 34" src="https://github.com/alirezaei1380/SE-LAB-8/assets/60629485/32216296-009a-4a62-a817-0a76e8fa5fa2">

<img width="1398" alt="Screen Shot 1402-10-02 at 21 43 27" src="https://github.com/alirezaei1380/SE-LAB-8/assets/60629485/f57ba1d2-c6fb-48c8-b460-d457c864947c">

