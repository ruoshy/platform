package cn.ruoshy.platform.service;

import cn.ruoshy.platform.service.cchess.PKServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GameShuntService {

    @Resource
    private PKServiceImpl chessPKService;

    public void handle(String message) {
        System.out.println(message);
        JSONObject json = JSONObject.parseObject(message);
        int id = (int) json.get("id");
        switch (id) {
            case 1:
                chessPKService.codeHandle(json.getJSONObject("data"));
                break;
        }
    }

}
