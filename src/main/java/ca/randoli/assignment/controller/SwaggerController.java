package ca.randoli.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {

    @RequestMapping("/swagger-ui")
    public String swaggerUi() {
        return "redirect:/webjars/swagger-ui/index.html?url=/api/swagger&validatorUrl=";
    }
}
