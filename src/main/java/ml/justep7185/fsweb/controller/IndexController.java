package ml.justep7185.fsweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangjiawei
 * @date 2020-12-11 14:36
 **/
@Controller
public class IndexController {

    @RequestMapping(value = "/index")
    public String getHomePage(Model model){
        model.addAttribute("name","JustEP");
        return "index";
    }

    @RequestMapping(value = "/execute")
    public String getSteam(Model model){
        return "index";
    }
}
