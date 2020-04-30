package q007;

import java.util.ArrayList;

/**
 * q007 最短経路探索
 * <p>
 * 壁を 'X' 通路を ' ' 開始を 'S' ゴールを 'E' で表現された迷路で、最短経路を通った場合に
 * 何歩でゴールまでたどり着くかを出力するプログラムを実装してください。
 * もし、ゴールまで辿り着くルートが無かった場合は -1 を出力してください。
 * なお、1歩は上下左右のいずれかにしか動くことはできません（斜めはNG）。
 * <p>
 * 迷路データは MazeInputStream から取得してください。
 * 迷路の横幅と高さは毎回異なりますが、必ず長方形（あるいは正方形）となっており、外壁は全て'X'で埋まっています。
 * 空行が迷路データの終了です。
 * <p>
 * <p>
 * [迷路の例]
 * XXXXXXXXX
 * XSX    EX
 * X XXX X X
 * X   X X X
 * X X XXX X
 * X X     X
 * XXXXXXXXX
 * <p>
 * [答え]
 * 14
 */
public class Q007 {
    public static void main(String[] args) {
        MazeInputStream mazeInputStream = new MazeInputStream();
        Integer[][] data = new Integer[50][50];
        int i = 0;
        int j = 0;
        ArrayList<Integer[]> position = new ArrayList<>();
        while (true) {
            int maze = mazeInputStream.read();
            if (maze < 0) {
                break;
            }
            switch ((char) maze) {
                case 'X':
                    data[i][j] = -1;
                    i++;
                    break;
                case 'S':
                    data[i][j] = 0;
                    Integer[] pos = {i, j};
                    position.add(pos);
                    i++;
                    break;
                case 'E':
                    data[i][j] = 999;
                    i++;
                    break;
                case '\n':
                    j++;
                    i = 0;
                    break;
                default:
                    i++;
                    break;
            }
        }

        for (int step = 1; step < 999; step++) {
            ArrayList<Integer[]> nextPosition = new ArrayList<>();
            for (Integer[] pos : position) {
                Integer dataPos1 = data[pos[0] + 1][pos[1]];
                Integer dataPos2 = data[pos[0]][pos[1] + 1];
                Integer dataPos3 = data[pos[0] - 1][pos[1]];
                Integer dataPos4 = data[pos[0]][pos[1] - 1];
                if ((data[pos[0] + 1][pos[1]] != null && data[pos[0] + 1][pos[1]] == 999)
                        || (data[pos[0] - 1][pos[1]] != null && data[pos[0] - 1][pos[1]] == 999)
                        || (data[pos[0]][pos[1] + 1] != null && data[pos[0]][pos[1] + 1] == 999)
                        || (data[pos[0]][pos[1] - 1] != null && data[pos[0]][pos[1] - 1] == 999)) {
                    System.out.println(step);
                    return;
                }
                if (data[pos[0] + 1][pos[1]] == null) {
                    data[pos[0] + 1][pos[1]] = step;
                    Integer[] nextPos = {pos[0] + 1, pos[1]};
                    nextPosition.add(nextPos);
                }
                if (data[pos[0]][pos[1] + 1] == null) {
                    data[pos[0]][pos[1] + 1] = step;
                    Integer[] nextPos = {pos[0], pos[1] + 1};
                    nextPosition.add(nextPos);
                }
                if (data[pos[0] - 1][pos[1]] == null) {
                    data[pos[0] - 1][pos[1]] = step;
                    Integer[] nextPos = {pos[0] - 1, pos[1]};
                    nextPosition.add(nextPos);
                }
                if (data[pos[0]][pos[1] - 1] == null) {
                    data[pos[0]][pos[1] - 1] = step;
                    Integer[] nextPos = {pos[0], pos[1] - 1};
                    nextPosition.add(nextPos);
                }
            }
            if (nextPosition.isEmpty()) {
                System.out.println("-1");
                return;
            }
            position = (ArrayList<Integer[]>) nextPosition.clone();
        }

    }

}
// 完成までの時間: xx時間 xx分