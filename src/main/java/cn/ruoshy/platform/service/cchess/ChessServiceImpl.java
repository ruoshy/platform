package cn.ruoshy.platform.service.cchess;

import cn.ruoshy.platform.entity.cchess.Chess;
import cn.ruoshy.platform.entity.cchess.ChineseChess;
import cn.ruoshy.platform.entity.cchess.Round;
import cn.ruoshy.platform.util.Result;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChessServiceImpl implements ChessService {

    private ConcurrentHashMap<String, ChineseChess> chessMap = new ConcurrentHashMap<>();

    /**
     * 创建棋盘
     *
     * @return
     */
    @Override
    public String create(String name) {
        String chessId = UUID.randomUUID().toString().replace("-", "");
//        String chessId = "0385307f773b462ea5f1df041de6ad68";
        ChineseChess chess = new ChineseChess();
        int[][] chessData = new int[][]{
                {22, 26, 28, 30, 21, 31, 29, 27, 23},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 24, 0, 0, 0, 0, 0, 25, 0},
                {32, 0, 33, 0, 34, 0, 35, 0, 36},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {12, 0, 13, 0, 14, 0, 15, 0, 16},
                {0, 4, 0, 0, 0, 0, 0, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {2, 6, 8, 10, 1, 11, 9, 7, 3},
        };
        chess.setChessData(chessData);
        chess.setName(name);
        chessMap.put(chessId, chess);
        return chessId;
    }

    /**
     * 棋局绑定
     *
     * @param userName 用户名称
     * @return
     */
    @Override
    public Result<Round> bind(String chessId, String userName) {
        Result<Round> result = new Result<>();
        ChineseChess chess = chessMap.get(chessId);
        Round round = new Round();
        if (chess == null) {
            result.setMsg("房间不存在!");
            result.setCode(44);
        } else if (userName.equals(chess.getRedSquareUserName())
                || userName.equals(chess.getBlackSquareUserName())) {
            String redName = chess.getRedSquareUserName();
            String blackName = chess.getBlackSquareUserName();
            if (redName.equals(userName) || blackName.equals(userName)) {
                round.setUserName(userName);
                round.setOpponentName(redName.equals(userName) ? blackName : redName);
                round.setColor(redName.equals(userName) ? 1 : 0);
                round.setChessData(chess.getChessData());
                round.setTake(chess.getTake());
                result.setMsg("重新加入房间!");
                result.setCode(40);
                result.setData(round);
            } else {
                result.setMsg("房间已满!");
                result.setCode(41);
            }
        } else if (chess.getRedSquareUserName() == null) {
            chess.setRedSquareUserName(userName);
            chess.setTake(1);
            round.setColor(1);
            round.setTake(1);
            result.setData(round);
            result.setCode(100);
            result.setMsg("红方入场!");
        } else if (chess.getBlackSquareUserName() == null) {
            chess.setBlackSquareUserName(userName);
            round.setUserName(userName);
            round.setOpponentName(chess.getRedSquareUserName());
            result.setData(round);
            result.setMsg("游戏开始!");
            result.setCode(101);
        }
        return result;
    }

    @Override
    public boolean removeChess(String chessId) {
        chessMap.remove(chessId);
        return true;
    }

    public ChineseChess getChineseChess(String chessId) {
        return chessMap.get(chessId);
    }

    public List<Chess> getChineseList() {
        Iterator<Map.Entry<String, ChineseChess>> iterator = chessMap.entrySet().iterator();
        List<Chess> chessList = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<String, ChineseChess> entry = iterator.next();
            Chess chess = new Chess();
            chess.setId(entry.getKey());
            chess.setName(entry.getValue().getName());
            chessList.add(chess);
        }
        return chessList;
    }


}
