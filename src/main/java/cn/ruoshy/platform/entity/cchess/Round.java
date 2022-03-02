package cn.ruoshy.platform.entity.cchess;

public class Round {

    private String chessId;

    private String opponentName;

    private String userName;

    private int[][] chessData;

    private int take;

    private int color;

    private int code;

    private int newX;

    private int newY;

    private int oldX;

    private int oldY;

    private int end;

    private String message;

    public String getChessId() {
        return chessId;
    }

    public void setChessId(String chessId) {
        this.chessId = chessId;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int[][] getChessData() {
        return chessData;
    }

    public void setChessData(int[][] chessData) {
        this.chessData = chessData;
    }

    public int getTake() {
        return take;
    }

    public void setTake(int take) {
        this.take = take;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getNewX() {
        return newX;
    }

    public void setNewX(int newX) {
        this.newX = newX;
    }

    public int getNewY() {
        return newY;
    }

    public void setNewY(int newY) {
        this.newY = newY;
    }

    public int getOldX() {
        return oldX;
    }

    public void setOldX(int oldX) {
        this.oldX = oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public void setOldY(int oldY) {
        this.oldY = oldY;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void swapUser() {
        String temp = getUserName();
        setUserName(getOpponentName());
        setOpponentName(temp);
    }
}
