package cn.ruoshy.platform.service.cchess;

import cn.ruoshy.platform.entity.cchess.ChineseChess;
import cn.ruoshy.platform.entity.cchess.Round;
import cn.ruoshy.platform.service.WebSocketService;
import cn.ruoshy.platform.util.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PKServiceImpl implements PKService {

    @Resource
    public ChessServiceImpl chessService;

    /**
     * 移动棋子
     *
     * @param round 回合数据
     */
    @Override
    public boolean move(Round round) {
        ChineseChess chess = chessService.getChineseChess(round.getChessId());
        // 判断对局是否开始
        if (chess.getRedSquareUserName() == null || chess.getBlackSquareUserName() == null) {
            return false;
        }
        StrategyServiceImpl strategyService = new StrategyServiceImpl(round, chess);
        // 棋子移动逻辑验证
        if (strategyService.verify()) {
            return strategyService.execute();
        }
        return false;
    }

    /**
     * 对局指令处理
     *
     * @param code
     */
    @Override
    public void codeHandle(JSONObject code) {
        Round round = code.toJavaObject(Round.class);
        Result<Round> result;
        if (round.getCode() == 0) {                 // 加入对局
            result = chessService.bind(round.getChessId(), round.getUserName());
            switch (result.getCode()) {
                case 100:
                    // 加入对局发送消息至红方玩家
                    WebSocketService.send2User(round.getUserName(), result.toJSONString());
                    break;
                case 101:
                    round = result.getData();
                    String redName = round.getOpponentName();
                    String blackName = round.getUserName();
                    // 开始对局发送消息至黑方玩家
                    WebSocketService.send2User(blackName, result.toJSONString());
                    // 开始对局发送消息至红方玩家
                    round.swapUser();
                    WebSocketService.send2User(redName, result.toJSONString());
                    break;
                case 40:
                    // 重新加入对局发送消息至请求玩家
                    WebSocketService.send2User(round.getUserName(), result.toJSONString());
                    break;
                case 41:
                    // 房间已满 -- 观战
//                    ChineseChess chess = chessService.getChineseChess(round.getChessId());
                    break;
                case 42:
                    WebSocketService.send2User(round.getUserName(), result.toJSONString());
                    break;
                case 44:
                    WebSocketService.send2User(round.getUserName(), result.toJSONString());
            }
        } else if (round.getCode() == 1) {                                    // 移动棋子
            result = new Result<>();
            result.setData(round);
            ChineseChess chess = chessService.getChineseChess(round.getChessId());
            if (move(round)) {
                chess.setTake(chess.getTake() == 1 ? 0 : 1);
                // 判断对局是否结束
                if (chess.getEnd() != 0) {
                    result.setCode(1000);
                    round.setEnd(chess.getEnd());
                    chessService.removeChess(round.getChessId());
                } else {
                    result.setCode(20);
                }
            } else {
                result.setCode(21);
            }
            round.setTake(chess.getTake());
            // 发送消息给请求者
            WebSocketService.send2User(round.getUserName(), result.toJSONString());
            // 发送消息给对手
            if (result.getCode() == 20 || result.getCode() == 1000) {
                round.swapUser();
                WebSocketService.send2User(round.getUserName(), result.toJSONString());
            }
        } else {
            result = new Result<>();
            result.setCode(200);
            result.setData(round);
            round.setCode(102);
            round.swapUser();
            WebSocketService.send2User(round.getUserName(), result.toJSONString());
        }
    }

}
