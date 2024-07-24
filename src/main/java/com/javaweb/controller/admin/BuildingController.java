package com.javaweb.controller.admin;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingSearchBuilderConvertor;
import com.javaweb.enums.BuildingTypeEnum;
import com.javaweb.enums.DistrictEnum;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingResponseDTO;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IBuildingService buildingService;
    @Autowired
    private IUserService userService;

    @GetMapping(value = "/admin/building-list")
    public ModelAndView buildingList(@RequestParam Map<String, String> params, @RequestParam(value = "typeCode", required = false) List<String> typeCode) {
        ModelAndView modelAndView = new ModelAndView("admin/building/list");
        BuildingSearchBuilder builder = BuildingSearchBuilderConvertor.toBuildingSearchBuilder(params, typeCode);
        modelAndView.addObject("buildingSearch", builder);
        List<BuildingResponseDTO> buildingSearchResponse = buildingService.findByCondition(params, typeCode);
        modelAndView.addObject("buildingList", buildingSearchResponse);
        Map<Long, String> listStaffs = userService.findAllStaffs();
        modelAndView.addObject("listStaffs", listStaffs);
        modelAndView.addObject("district", DistrictEnum.type());
        modelAndView.addObject("typeCode", BuildingTypeEnum.type());
        return modelAndView;
    }
    @GetMapping(value = "/admin/building-edit")
    public ModelAndView buildingAdd(@ModelAttribute BuildingDTO building) {
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        modelAndView.addObject("building", building);
        modelAndView.addObject("district", DistrictEnum.type());
        modelAndView.addObject("typeCode", BuildingTypeEnum.type());
        return modelAndView;
    }
    @GetMapping(value = "/admin/building-edit-{id}")
    public ModelAndView buildingEdit(@PathVariable(value = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/admin/building/edit");
        BuildingDTO building = buildingService.findBuildingById(id);
        modelAndView.addObject("building", building);
        modelAndView.addObject("district", DistrictEnum.type());
        modelAndView.addObject("typeCode", BuildingTypeEnum.type());
        return modelAndView;
    }
}
