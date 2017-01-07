package cz.muni.fi.pa165.carpark.angular.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mr.K on 07.01.2017.
 */
@Controller
public class RedirectController {

    @RequestMapping({"/pa165/admin/cars", "/pa165/admin/employees", "/pa165/admin/admins", "/pa165/admin/reservations",
            "/pa165/employee/cars", "/pa165/employee/reservations"})
    public String index() {
        return "forward:/index.html";
    }
}
