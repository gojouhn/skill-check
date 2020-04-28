package q003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/**
 * Q003 集計と並べ替え
 *
 * 以下のデータファイルを読み込んで、出現する単語ごとに数をカウントし、アルファベット辞書順に並び変えて出力してください。
 * resources/q003/data.txt
 * 単語の条件は以下となります
 * - "I"以外は全て小文字で扱う（"My"と"my"は同じく"my"として扱う）
 * - 単数形と複数形のように少しでも文字列が異れば別単語として扱う（"dream"と"dreams"は別単語）
 * - アポストロフィーやハイフン付の単語は1単語として扱う（"isn't"や"dead-end"）
 *
 * 出力形式:単語=数
 *
[出力イメージ]
（省略）
highest=1
I=3
if=2
ignorance=1
（省略）

 * 参考
 * http://eikaiwa.dmm.com/blog/4690/
 */
public class Q003 {
    /**
     * データファイルを開く
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q003.class.getResourceAsStream("data.txt");
    }

    private static String readFromInputStream(InputStream inputStream) {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        } catch (IOException exception) {
            return "";
        }
        return resultStringBuilder.toString();
    }

    public static void main(String[] args){
        InputStream inputStream = openDataFile();
        String data = readFromInputStream(inputStream);
        data = data.replace(".", "");
        data = data.replace(",", "");
        data = data.replace(";", "");
        data = data.replace(" – ", " ");
        data = data.replace("\n", " ");
        String[] words = data.split(" ", 0);
        Map<String, Integer> wordMap = new TreeMap<>();
        for (String s : words) {
            String word = s.toLowerCase();
            if (wordMap.containsKey(word)) {
                Integer count = wordMap.get(word);
                wordMap.put(word, count + 1);
            } else {
                wordMap.put(word, 1);
            }
        }

        for (String key : wordMap.keySet()) {
            if (key.equals("i")) {
                System.out.println("I" + ":" + wordMap.get(key));
            } else {
                System.out.println(key + ":" + wordMap.get(key));
            }
        }
    }
}
// 完成までの時間: 0時間 40分