package stu.cfl.yygh.hosp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import stu.cfl.yygh.hosp.mapper.HospitalSetMapper;
import stu.cfl.yygh.hosp.service.HospitalSetService;
import stu.cfl.yygh.model.hosp.HospitalSet;

@Service
public class HospitalSetServiceImpl
        extends ServiceImpl<HospitalSetMapper, HospitalSet>
        implements HospitalSetService {

    // mapper在ServiceImpl中已经注入了
}
