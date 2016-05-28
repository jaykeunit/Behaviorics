package behaviorics.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController implements ErrorController{

    @RequestMapping(value = "/error")
    public String error() {
        return "errorPage";
    }

    @RequestMapping(value = "/accessdenied")
    public String accessDenied() {
        return "accessdeniedPage";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}