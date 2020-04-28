package q002;

import java.util.ArrayList;
import java.util.List;

/**
 * Q002 並べ替える
 *
 * dataListに "ID,名字" の形式で20個のデータがあります。
 * これをID順に並べて表示するプログラムを記述してください。
 *
 * dataListの定義を変更してはいけません。
 *
 *
[出力結果イメージ]
1,伊藤
2,井上
（省略）
9,清水
10,鈴木
11,高橋
（省略）
20,渡辺
 */
public class Q002 {
    /**
     * データ一覧
     */
    private static final String[] dataList = {
            "8,佐藤",
            "10,鈴木",
            "11,高橋",
            "12,田中",
            "20,渡辺",
            "1,伊藤",
            "18,山本",
            "13,中村",
            "5,小林",
            "3,加藤",
            "19,吉田",
            "17,山田",
            "7,佐々木",
            "16,山口",
            "6,斉藤",
            "15,松本",
            "2,井上",
            "4,木村",
            "14,林",
            "9,清水"
    };

    static class MyClass {
        private int no;
        private String name;

        public MyClass(int no, String name) {
            this.no = no;
            this.name = name;
        }

        public int getNo() {
            return no;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args){
        List<MyClass> data = new ArrayList<>();
        for (int i=0;i<dataList.length;i++) {
            String[] classMate = dataList[i].split(",", 0);
            MyClass myClass = new MyClass(Integer.parseInt(classMate[0]), classMate[1]);
            data.add(myClass);
        }
        data.sort((a, b) -> a.no - b.no);
        for (MyClass myClass : data) {
            System.out.println(myClass.getNo() + "," + myClass.getName());
        }
    }
}
// 完成までの時間: 1時間 00分