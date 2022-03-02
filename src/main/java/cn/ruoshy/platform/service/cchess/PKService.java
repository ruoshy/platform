package cn.ruoshy.platform.service.cchess;

import cn.ruoshy.platform.entity.cchess.Round;
import com.alibaba.fastjson.JSONObject;

public interface PKService {

    /**
     * 移动棋子
     *
     * @param round 回合数据
     */
    boolean move(Round round);

    /**
     * 对局指令处理
     * @param code
     */
    void codeHandle(JSONObject code);

}
