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
