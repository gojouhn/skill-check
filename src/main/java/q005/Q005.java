package q005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Q005 データクラスと様々な集計
 * <p>
 * 以下のファイルを読み込んで、WorkDataクラスのインスタンスを作成してください。
 * resources/q005/data.txt
 * (先頭行はタイトルなので読み取りをスキップする)
 * <p>
 * 読み込んだデータを以下で集計して出力してください。
 * (1) 役職別の合計作業時間
 * (2) Pコード別の合計作業時間
 * (3) 社員番号別の合計作業時間
 * 上記項目内での出力順は問いません。
 * <p>
 * 作業時間は "xx時間xx分" の形式にしてください。
 * また、WorkDataクラスは自由に修正してください。
 * <p>
 * [出力イメージ]
 * 部長: xx時間xx分
 * 課長: xx時間xx分
 * 一般: xx時間xx分
 * Z-7-31100: xx時間xx分
 * I-7-31100: xx時間xx分
 * T-7-30002: xx時間xx分
 * （省略）
 * 194033: xx時間xx分
 * 195052: xx時間xx分
 * 195066: xx時間xx分
 * （省略）
 */
public class Q005 {
    /**
     * データファイルを開く
     * resources/q005/data.txt
     */
    private static InputStream openDataFile() {
        return Q005.class.getResourceAsStream("data.txt");
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

    public static void main(String[] args) {
        InputStream inputStream = openDataFile();
        String dataFile = readFromInputStream(inputStream);
        String[] data = dataFile.split("\n", 0);
        List<WorkData> dataList = new ArrayList();
        for (int i = 1; i < data.length; i++) {
            String[] subData = data[i].split(",", 0);
            WorkData workData = new WorkData(subData[0], subData[1], subData[2], subData[3], Integer.parseInt(subData[4]));
            dataList.add(workData);
        }
        // 役職
        Map<String, Integer> positionMap = new HashMap<>();
        // Pコード
        Map<String, Integer> pCodeMap = new HashMap<>();
        // 社員番号
        Map<String, Integer> numberMap = new HashMap<>();

        for (WorkData workData : dataList) {
            if (positionMap.containsKey(workData.getPosition())) {
                Integer workTime = positionMap.get(workData.getPosition());
                positionMap.put(workData.getPosition(), workTime + workData.getWorkTime());
            } else {
                positionMap.put(workData.getPosition(), workData.getWorkTime());
            }

            if (pCodeMap.containsKey(workData.getpCode())) {
                Integer workTime = pCodeMap.get(workData.getpCode());
                pCodeMap.put(workData.getpCode(), workTime + workData.getWorkTime());
            } else {
                pCodeMap.put(workData.getpCode(), workData.getWorkTime());
            }

            if (numberMap.containsKey(workData.getNumber())) {
                Integer workTime = numberMap.get(workData.getNumber());
                numberMap.put(workData.getNumber(), workTime + workData.getWorkTime());
            } else {
                numberMap.put(workData.getNumber(), workData.getWorkTime());
            }
        }

        for (String key : positionMap.keySet()) {
            System.out.println(key + ":" + timeFormat(positionMap.get(key)));
        }
        for (String key : pCodeMap.keySet()) {
            System.out.println(key + ":" + timeFormat(pCodeMap.get(key)));
        }
        for (String key : numberMap.keySet()) {
            System.out.println(key + ":" + timeFormat(numberMap.get(key)));
        }
    }

    private static String timeFormat(int time) {
        return time / 60 + "時間" + String.format("%02d", time % 60) + "分";
    }
}
// 完成までの時間: 0時間 40分