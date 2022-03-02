package cn.ruoshy.platform.service.cchess;

import cn.ruoshy.platform.util.Result;

public interface ChessService {

    /**
     * 创建棋盘
     *
     * @return
     */
    String create(String name);

    /**
     * 棋局绑定
     *
     * @param userName 用户名称
     * @return
     */
    Result bind(String chessId, String userName);

    /**
     * 删除棋盘
     * @param chessId 棋盘id
     * @return
     */
    boolean removeChess(String chessId);
}
