package behaviorics.controllers;

import behaviorics.FailReportsService;
import behaviorics.models.RepairLog;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FailStatisticsController {

    FailReportsService service = new FailReportsService();
    @RequestMapping(value="/failstatistics", method= RequestMethod.GET)
    public String reports(Model model) {

        try {
            model.addAttribute("service", service);
            RepairLog rl;
            //rl.

            return "failStatistics";
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("errMsg", e.getMessage());
            return "errorPage";
        }


    }
}
