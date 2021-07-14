package stu.cfl.yygh.hosp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stu.cfl.yygh.hosp.service.HospitalSetService;
import stu.cfl.yygh.model.hosp.HospitalSet;

import java.util.List;

@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    // 注入service
    @Autowired
    private HospitalSetService hospitalSetService;

    @GetMapping("findAll")
    public List<HospitalSet> findAllHospitalSet(){
        // 调用service方法
        List<HospitalSet> list = hospitalSetService.list();
        return list;
    }



}
