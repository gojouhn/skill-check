package q009;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Q009 重い処理を別スレッドで実行
 * <p>
 * 標準入力から整数を受け取り、別スレッドで素因数分解して、その整数を構成する2以上の素数を求めるプログラムを記述してください。
 * - 素因数分解は重い処理であるため、別スレッドで実行してください
 * - 標準入力から整数を受け取った後は、再度標準入力に戻ります
 * - 空文字が入力された場合は、素因数分解を実行中の状態を表示します（「実行中」あるいは処理結果を表示）
 * - 素因数分解の効率的なアルゴリズムを求めるのが問題ではないため、あえて単純なアルゴリズムで良い（遅いほどよい）
 * （例えば、2および3以上の奇数で割り切れるかを全部チェックする方法でも良い）
 * - BigIntegerなどを使って、大きな数値も扱えるようにしてください
 * [実行イメージ]
 * 入力) 65536
 * 入力)
 * 65536: 実行中  <-- もし65536の素因数分解が実行中だった場合はこのように表示する
 * 入力) 12345
 * 入力)
 * 65536: 2      <-- 実行が終わっていたら結果を表示する。2の16乗だが、16乗の部分の表示は不要（複数溜まっていた場合の順番は問わない）
 * 12345: 実行中
 * 入力)
 * 12345: 3,5,823
 */
public class Q009 {

    public static void main(String[] args) {
        BigInteger bi;
        String input = "";

        Scanner sc = new Scanner(System.in);

        while (true) {
            input = sc.nextLine();
            if (input.equals("end")) {
                return;
            }
            if (input.isEmpty()) {
                BigInteger inputBi = MultiThread.getMultiThreadInt();
                StringBuilder sb = MultiThread.getSb();
                if (sb == null || sb.length() == 0) {
                    System.out.println(inputBi + ": 実行中");
                } else {
                    System.out.println(inputBi + ": " + sb);
                }
            } else {
                bi = new BigInteger(input);
                MultiThread mt = new MultiThread(bi);
                mt.start();
            }
        }

    }
}

class MultiThread extends Thread {
    //変数の宣言
    private static BigInteger multiThreadInt;
    private static StringBuilder sb;

    public static BigInteger getMultiThreadInt() {
        return multiThreadInt;
    }

    public static StringBuilder getSb() {
        return sb;
    }

    //スレッド作成時に実行される処理
    public MultiThread(BigInteger bi){
        this.multiThreadInt = bi;
        this.sb = new StringBuilder();
    }

    public void run() {
        BigInteger num = this.multiThreadInt;
        Set<BigInteger> numSet = new HashSet<>();

        while (num.remainder(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            num = num.divide(BigInteger.valueOf(2));
            numSet.add(BigInteger.valueOf(2));
        }

        BigInteger insu = BigInteger.valueOf(3);

        while (num.compareTo(BigInteger.ONE) > 0) {
            while (num.remainder(insu).equals(BigInteger.ZERO)) {
                num = num.divide(insu);
                numSet.add(insu);
            }
            insu = insu.add(BigInteger.valueOf(2));
        }
        for (BigInteger a : numSet) {
            sb.append(a.toString()).append(",");
        }
        sb.setLength(sb.length()-1);
    }
}
// 完成までの時間: xx時間 xx分