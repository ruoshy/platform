package cn.ruoshy.platform.controller;

import cn.ruoshy.platform.entity.cchess.Chess;
import cn.ruoshy.platform.service.cchess.ChessServiceImpl;
import cn.ruoshy.platform.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@RestController
public class PageController {

    @Resource
    private ChessServiceImpl chessService;

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index.html");
    }

    @RequestMapping("/mate")
    public ModelAndView mate() {
        return new ModelAndView("mate.html");
    }

    @RequestMapping("/chess")
    public ModelAndView chess() {
        return new ModelAndView("chess.html");
    }

    @RequestMapping("/login")
    public String login(String name, HttpSession session) {
        Result result = new Result();
        result.setCode(200);
        session.setAttribute("name", name);
        return result.toJSONString();
    }

    @RequestMapping("/chess/create")
    public String createChess(@RequestParam("cname") String name) {
        Result<String> result = new Result<>();
        result.setCode(200);
        result.setData(chessService.create(name));
        return result.toJSONString();
    }

    /**
     * 获取对局列表
     *
     * @return
     */
    @RequestMapping("/chess/list")
    public String chessList() {
        Result<List<Chess>> result = new Result<>();
        result.setCode(200);
        result.setData(chessService.getChineseList());
        return result.toJSONString();
    }

    /**
     * 获取服务器ip
     *
     * @return
     * @throws UnknownHostException
     */
    @RequestMapping("/sv/ip")
    public String getServiceIp() throws UnknownHostException {
        Result<String> result = new Result<>();
        result.setCode(200);
        result.setData(InetAddress.getLocalHost().getHostAddress());
        return result.toJSONString();
    }
}
