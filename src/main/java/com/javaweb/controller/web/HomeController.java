package com.javaweb.controller.web;

import com.javaweb.enums.DistrictEnum;
import com.javaweb.model.request.BuildingSearchRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage(BuildingSearchRequest buildingSearchRequest, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("web/home");
        mav.addObject("modelSearch", buildingSearchRequest);
        mav.addObject("districts", DistrictEnum.type());
		return mav;
	}

    @GetMapping(value="/gioi-thieu")
    public ModelAndView introducceBuiding(){
        return new ModelAndView("web/introduce");
    }

    @GetMapping(value="/san-pham")
    public ModelAndView buidingList(){
        return new ModelAndView("/web/list");
    }

    @GetMapping(value="/tin-tuc")
    public ModelAndView news(){
        return new ModelAndView("/web/news");
    }

    @GetMapping(value="/lien-he")
    public ModelAndView contact(){
        return new ModelAndView("/web/contact");
    }

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
        return new ModelAndView("login");
	}

	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/login?accessDenied");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/trang-chu");
	}
}
