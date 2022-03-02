package cn.ruoshy.platform.entity.cchess;

import java.util.LinkedList;
import java.util.List;

public class ChineseChess {

    private String redSquareUserName;

    private String blackSquareUserName;

    private int take;

    private int[][] chessData;

    private int end;

    private String name;

    private LinkedList<String> followers;

    public String getRedSquareUserName() {
        return redSquareUserName;
    }

    public void setRedSquareUserName(String redSquareUserName) {
        this.redSquareUserName = redSquareUserName;
    }

    public String getBlackSquareUserName() {
        return blackSquareUserName;
    }

    public void setBlackSquareUserName(String blackSquareUserName) {
        this.blackSquareUserName = blackSquareUserName;
    }

    public int getTake() {
        return take;
    }

    public void setTake(int take) {
        this.take = take;
    }

    public int[][] getChessData() {
        return chessData;
    }

    public void setChessData(int[][] chessData) {
        this.chessData = chessData;
    }

    public int getPiece(int x, int y) {
        return chessData[y][x];
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<String> getFollowers() {
        return followers;
    }

    public void setFollowers(LinkedList<String> followers) {
        this.followers = followers;
    }
}
