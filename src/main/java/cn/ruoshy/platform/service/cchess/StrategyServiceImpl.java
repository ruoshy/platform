package cn.ruoshy.platform.service.cchess;

import cn.ruoshy.platform.entity.cchess.ChineseChess;
import cn.ruoshy.platform.entity.cchess.Round;

public class StrategyServiceImpl implements StrategyService {

    private Round round;

    private ChineseChess chess;

    private int oldX, oldY, newX, newY;

    public StrategyServiceImpl() {
    }

    public StrategyServiceImpl(Round round, ChineseChess chess) {
        this.round = round;
        this.chess = chess;
        this.oldX = round.getOldX();
        this.oldY = round.getOldY();
        this.newX = round.getNewX();
        this.newY = round.getNewY();
    }

    @Override
    public boolean verify() {
        if (oldX == newX && oldY == newY) {
            return false;
        }
        int[][] chessData = chess.getChessData();
        int id = chessData[round.getOldY()][round.getOldX()];
        int nid = chessData[newY][newX];
        if (nid > 20 && id > 20 || nid > 0 && nid < 20 && id < 20) {
            return false;
        }
        switch (id) {
            case 1:
            case 21:
                return kingMove(id);
            case 2:
            case 3:
            case 22:
            case 23:
                return chariotMove(id);
            case 4:
            case 5:
            case 24:
            case 25:
                return cannonMove(id);
            case 6:
            case 7:
            case 26:
            case 27:
                return horseMove(id);
            case 8:
            case 9:
            case 28:
            case 29:
                return elephantMove(id);
            case 10:
            case 11:
            case 30:
            case 31:
                return adviserMove(id);
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
                return pawnMove(id);
        }
        return false;
    }

    @Override
    public boolean execute() {
        int[][] chessData = chess.getChessData();
        if (chessData[newY][newX] == 21) {
            chess.setEnd(1);  // 红
        } else if (chessData[newY][newX] == 1) {
            chess.setEnd(2);  // 黑
        }
        chessData[newY][newX] = chessData[oldY][oldX];
        chessData[oldY][oldX] = 0;
        return true;
    }

    // 车
    @Override
    public boolean chariotMove(int id) {
        int[][] chessData = chess.getChessData();
        if (oldY != newY && oldX != newX) {
            return false;
        }
        if (oldY == newY) {
            // 左右移动
            int df = oldX - newX;
            for (int x = (df > 0 ? newX : oldX) + 1; x < (df > 0 ? oldX : newX); x++) {
                if (chessData[oldY][x] != 0) {
                    return false;
                }
            }
            return true;
        } else {
            // 上下移动
            int df = oldY - newY;
            for (int y = (df > 0 ? newY : oldY) + 1; y < (df > 0 ? oldY : newY); y++) {
                if (chessData[y][oldX] != 0) {
                    return false;
                }
            }
            return true;
        }
    }

    // 炮
    @Override
    public boolean cannonMove(int id) {
        int[][] chessData = chess.getChessData();
        if (chessData[newY][newX] == 0) {
            return chariotMove(id);
        }
        int interval = 0;
        if (oldY == newY) {
            // 左右移动
            int df = oldX - newX;
            for (int x = (df > 0 ? newX : oldX) + 1; x < (df > 0 ? oldX : newX); x++) {
                if (chessData[oldY][x] != 0) {
                    interval++;
                }
            }
        } else {
            // 上下移动
            int df = oldY - newY;
            for (int y = (df > 0 ? newY : oldY) + 1; y < (df > 0 ? oldY : newY); y++) {
                if (chessData[y][oldX] != 0) {
                    interval++;
                }
            }
        }
        return interval == 1;
    }

    // 马
    @Override
    public boolean horseMove(int id) {
        int dx = Math.abs(oldX - newX);
        int dy = Math.abs(oldY - newY);
        if (dx == 1 && dy == 2 || dx == 2 && dy == 1) {
            int[][] chessData = chess.getChessData();
            if (dy == 2) {           // 上下
                return chessData[oldY - (oldY > newY ? 1 : -1)][oldX] == 0;
            } else {                 // 左右
                return chessData[oldY][oldX - (oldX > newX ? 1 : -1)] == 0;
            }
        }
        return false;
    }

    // 兵(卒)
    @Override
    public boolean pawnMove(int id) {
        if (id > 20) {
            // black
            // 只能前进
            if (oldY > newY) {
                return false;
            }
            if (oldY > 4) {
                // 过河后
                if (Math.abs(oldX - newX + oldY - newY) == 1) {
                    return true;
                }
            } else {
                if (oldX == newX && newY - oldY == 1) {
                    return true;
                }
            }
        } else {
            // red
            // 只能前进
            if (oldY < newY) {
                return false;
            }
            if (oldY < 5) {
                // 过河后
                if (Math.abs(oldX - newX + oldY - newY) == 1) {
                    return true;
                }
            } else {
                if (oldX == newX && oldY - newY == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    // 将(帅)
    @Override
    public boolean kingMove(int id) {
        if (newX > 2 && newX < 6 && (newY > 6 && newY < 10 || newY > -1 && newY < 3)) {
            // 新位置与旧位置同列，判断新位置与旧位置行距
            if (oldX == newX && Math.abs(oldY - newY) == 1) {
                return true;
            }
            // 新位置与旧位置同行，判断新位置与旧位置列距
            else if (oldY == newY && Math.abs(oldX - newX) == 1) {
                return true;
            }
        }
        int[][] chessData = chess.getChessData();
        if (oldX == newX && (chessData[newY][newX] == 1 || chessData[newY][newX] == 21)) {
            for (int y = oldY + 1; y < newY; y++) {
                if (chessData[y][oldX] != 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // 士(仕)
    @Override
    public boolean adviserMove(int id) {
        if (newX > 2 && newX < 6 && (newY > 6 && newY < 10 || newY > -1 && newY < 3)) {
            if (Math.abs(oldX - newX) == 1 && Math.abs(oldY - newY) == 1) {
                return true;
            }
        }
        return false;
    }

    // 象(相)
    @Override
    public boolean elephantMove(int id) {
        if (id < 20 && newY < 5) {
            return false;
        } else if (id > 20 && newY > 4) {
            return false;
        }
        int[][] chessData = chess.getChessData();
        int dx = oldX > newX ? oldX : newX;
        int dy = oldY > newY ? oldY : oldX;
        if (chessData[dy - 1][dx - 1] == 0 && Math.abs(oldX - newX) == 2 && Math.abs(oldY - newY) == 2) {
            return true;
        }
        return false;
    }
}
