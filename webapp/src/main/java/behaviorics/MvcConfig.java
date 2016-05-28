package behaviorics;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/addbuilding").setViewName("addbuilding");
        registry.addViewController("/addcamera").setViewName("addcamera");
        registry.addViewController("/addentity").setViewName("addentity");
        registry.addViewController("/addfloor").setViewName("addfloor");
        registry.addViewController("/addUser").setViewName("addUser");
        registry.addViewController("/errorPage").setViewName("errorPage");
        registry.addViewController("/failReports").setViewName("failReports");
        registry.addViewController("/failStatistics").setViewName("failStatistics");
        registry.addViewController("/manageEntities").setViewName("manageEntities");
        registry.addViewController("/managementPage").setViewName("managementPage");
        registry.addViewController("/reportsPage").setViewName("reportsPage");
        registry.addViewController("/submitResults").setViewName("submitResults");
    }
}
