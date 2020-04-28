package q004;

/**
 * Q004 ソートアルゴリズム
 * <p>
 * ListManagerクラスをnewして、小さい順に並び変えた上でcheckResult()を呼び出してください。
 * <p>
 * 実装イメージ:
 * ListManager data = new ListManager();
 * // TODO 並び換え
 * data.checkResult();
 * <p>
 * - ListManagerクラスを修正してはいけません
 * - ListManagerクラスの dataList を直接変更してはいけません
 * - ListManagerクラスの比較 compare と入れ替え exchange を使って実現してください
 */
public class Q004 {
    public static void main(String[] args) {
        ListManager data = new ListManager();
        for (int i = 0; i < data.size() - 1; i++) {
            for (int j = 0; j < data.size() - 1 - i; j++) {
                if (data.compare(j, j + 1) == 1) {
                    data.exchange(j, j + 1);
                }
            }
        }
        data.checkResult();
    }
}
// 完成までの時間: 0時間 30分