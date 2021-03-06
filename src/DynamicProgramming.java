import java.util.Scanner;

public class DynamicProgramming {
    public static void main(String[] args) {
//        System.out.println(Fibonacci());
//        MakeItOne();
//        RectangleTiling();
        RectangleTilingTopDown();
    }

    public static long Fibonacci() {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
//        int[] memo = new int[input+1];
//        return Fibonacci(input, memo);
        return FibonacciBottomUp(input);
    }

    public static int Fibonacci(int n, int[] memo) {
        if (n <= 1 ) {
            return n;
        } else if (memo[n] > 0) {
            return memo[n];
        } else {
            return memo[n] = Fibonacci(n-1, memo) + Fibonacci( n-2, memo);
        }
    }

//2748
    public static long FibonacciBottomUp (int n) {
        long [] d = new long[n+1];
        d[0] = 0;
        d[1] = 1;
        for (int i=0; i <= n-2; i++) {
            d[i+2] = d[i] + d[i+1];
        }
        return d[n];
    }

//1463
    public static void MakeItOne () {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        int[] memo = new int[input+1];
        System.out.println(MakeItOneBottomUp(input));
    }

    public static int MakeItOne (int n, int[] d) {
        if (d[n]> 0) return d[n];
        if (n == 1) return 0;

        // d[n] = minimum number of calculation to make number n into 1.

        // case of deducting by 1
        d[n] = MakeItOne(n-1, d) + 1;

        // case of deductible by 3
        if ( n % 3 == 0) {
            int temp = MakeItOne(n/3, d) + 1;
            if ( d[n] > temp) {
                d[n] = temp;
            }
        }

        // case of deductible by 2
        if (n % 2 == 0) {
            int temp = MakeItOne(n/2, d) + 1;
            if (d[n] > temp) {
                d[n] = temp;
            }
        }
        return d[n];
    }

    public static int MakeItOneBottomUp (int n) {
        int[] d = new int[n+1];
        d[1] = 0;
        for (int i = 2; i<= n;i++) {
            d[i] = d[i-1] + 1;
            if (i % 3 == 0) {
                int temp =  d[i/3] + 1;
                if (d[i] > temp) {
                    d[i] = temp;
                }
            }
            if (i % 2 == 0) {
                int temp = d[i/2] + 1;
                if (d[i] > temp) {
                    d[i] = temp;
                }
            }
        }
        return d[n];
    }

    public static void RectangleTiling() {
        //Filling 2 x n rectangle with 2 x 1 rectangles, count all possibility

        //first step =>  1 vertical or  2 in horizontal
        // d[n] = d[n-1] + d[n-2]

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n==0) System.out.println(1);

        int[] d = new int[n+1];

        // filling 2 x 0 rectangle can be done in 1 way, with putting empty rectangle (like empty set).
        d[0] = 1;

        d[1] = 1;
        for (int i=2;  i <= n; i ++) {
            d[i] = d[i-1] + d[i-2];
        }

        System.out.println(d[n]);
    }

    public static void RectangleTilingTopDown () {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] memo = new int[n+1];
        System.out.println(RectangleTilingTopDown(n, memo));
    }

    public static int RectangleTilingTopDown (int n, int[] d) {
        if (n <= 1) return 1;
        if (d[n] > 0) return d[n];
        return d[n] = (RectangleTilingTopDown(n-1, d) + RectangleTilingTopDown(n-2,d))%10007;

    }
}
