package leetcode;

import java.util.LinkedList;
import java.util.Queue;

// 529. 扫雷游戏
// https://leetcode-cn.com/problems/minesweeper/
public class UpdateBoard {

    int[] dirX = {0, 1, 0, -1, 1, 1, -1, -1}; //xy模拟，方向：上下左右+东南西北方
    int[] dirY = {1, 0, -1, 0, 1, -1, 1, -1};

    //'M'代表一个 未挖出的 地雷，
    //'E'代表一个 未挖出的 空方块，
    //'B'代表没有相邻（上，下，左，右，和所有4个对角线）地雷的 已挖出的 空白方块，
    //数字（'1' 到 '8'）表示有多少地雷与这块 已挖出的 方块相邻，
    //'X'则表示一个 已挖出的 地雷。

    //1、如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X' 。
    //2、如果一个 没有相邻地雷 的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的 未挖出 方块都应该被递归地揭露。
    //3、如果一个 至少与一个地雷相邻 的空方块（'E'）被挖出，修改它为数字（'1' 到 '8' ），表示相邻地雷的数量。
    //4、如果在此次点击中，若无更多方块可被揭露，则返回盘面。
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        if (board[x][y] == 'M') {
            // 规则 1
            board[x][y] = 'X';
        } else {
            bfs(board, x, y);
        }
        return board;
    }

    public void bfs(char[][] board, int sx, int sy) {
        Queue<int[]> queue = new LinkedList<>(); //走过的点位
        boolean[][] vis = new boolean[board.length][board[0].length]; // 代表点位是否走过
        queue.offer(new int[]{sx, sy}); // 当前位置，存入路径
        vis[sx][sy] = true;
        while (!queue.isEmpty()) { //
            int[] pos = queue.poll(); //取出当前点位
            int cnt = 0, x = pos[0], y = pos[1]; //雷的数量，x\y代表8个方向
            for (int i = 0; i < 8; ++i) {
                int tx = x + dirX[i];
                int ty = y + dirY[i];
                if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length) { //边界跳过
                    continue;
                }
                // 不用判断 M，因为如果有 M 的话游戏已经结束了
                if (board[tx][ty] == 'M') {
                    ++cnt;
                }
            }
            if (cnt > 0) { //有雷
                // 规则 3
                board[x][y] = (char) (cnt + '0'); // 更新为雷的数量
            } else { // 没有雷
                // 规则 2
                board[x][y] = 'B'; //当前位置更新为空白
                for (int i = 0; i < 8; ++i) {
                    int tx = x + dirX[i]; // 搜索8个方向
                    int ty = y + dirY[i];
                    // 这里不需要在存在 B 的时候继续扩展，因为 B 之前被点击的时候已经被扩展过了
                    if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length || board[tx][ty] != 'E' || vis[tx][ty]) {
                        continue; //跳过边界，已走过的也跳过。
                    }
                    queue.offer(new int[]{tx, ty}); //加入走过的队列
                    vis[tx][ty] = true;
                }
            }
        }
    }

}
