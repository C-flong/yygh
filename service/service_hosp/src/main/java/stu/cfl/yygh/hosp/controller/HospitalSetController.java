package stu.cfl.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import stu.cfl.yygh.common.result.Result;
import stu.cfl.yygh.common.utils.MD5;
import stu.cfl.yygh.hosp.service.HospitalSetService;
import stu.cfl.yygh.model.hosp.HospitalSet;
import stu.cfl.yygh.vo.hosp.HospitalQueryVo;

import java.util.List;
import java.util.Random;

/**
 * @Api：修饰整个类，描述Controller的作用
 * @ApiOperation：描述一个类的一个方法，或者说一个接口
 * @ApiParam：单个参数描述
 * @ApiModel：用对象来接收参数
 * @ApiModelProperty：用对象接收参数时，描述对象的一个字段
 * @ApiImplicitParam：一个请求参数
 * @ApiImplicitParams：多个请求参数
 */

@Api(tags = "医院信息API")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
@CrossOrigin  // 允许跨域访问（协议不同，ip地址不懂，端口号不同，都会产生跨域）
public class HospitalSetController {

    // 注入service
    @Autowired
    private HospitalSetService hospitalSetService;

    @ApiOperation("查询所有信息API")
    @GetMapping("findAll")
    public Result findAllHospitalSet(){
        // 调用service方法
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    @ApiOperation(value = "逻辑删除医院信息")
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        return flag ? Result.ok() : Result.fail();
    }

    // 3、条件查页
    @PostMapping("findPageHospSet/{current}/{limit}")  // 走post方法，下边接受json数据
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) HospitalQueryVo hospitalQueryVo){
        /**
         * current: 当前页
         * limit: 记录数
         * hospitalQueryVo: 查询条件
         */
        // 创建page对象，传递当前页，每页记录数 (分页查询)
        Page<HospitalSet> page = new Page<>(current, limit);

        // 构建条件
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        String hosname = hospitalQueryVo.getHosname();  // 医院的名称
        String hoscode = hospitalQueryVo.getHoscode();  // 医院的编号

        if(!StringUtils.isEmpty(hosname)){
            wrapper.like("hosname", hosname);
        }
        if (!StringUtils.isEmpty(hoscode)){
            wrapper.eq("hoscode", hoscode);
        }

        // 调用方法实现分页查询
        Page<HospitalSet> pageHospitalSet = hospitalSetService.page(page, wrapper);

        // 返回结果
        return Result.ok(pageHospitalSet);


    }

    // 4、添加医院设置
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet){
        // 设置状态：1 使用， 0 不能使用
        hospitalSet.setStatus(1);

        // 生成签名密钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000)));

        boolean save = hospitalSetService.save(hospitalSet);
        if(save){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    // 5、根据id获取医院设置
    @GetMapping("getHospSet/{id}")
    public Result getHospSet(@PathVariable Long id){
        HospitalSet byId = hospitalSetService.getById(id);
        return Result.ok(byId);
    }

    // 6、修改医院设置
    @PostMapping("updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet){
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if(flag){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }


    // 7、批量删除医院设置
    @DeleteMapping("batchRemove")
    public Result batchRemoveHospitalSet(@RequestBody List<Long> ids){
        boolean flag = hospitalSetService.removeByIds(ids);
        return Result.ok();
    }

    // 8、医院设置锁定和解锁
    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,
                                  @PathVariable Integer status){

        HospitalSet byId = hospitalSetService.getById(id);
        byId.setStatus(status);
        hospitalSetService.updateById(byId);
        return Result.ok();

    }

    // 9、发送签名密钥
    @PutMapping("sendKey/{id}")
    public Result sendKey(@PathVariable Long id){
        HospitalSet byId = hospitalSetService.getById(id);
        String signKey = byId.getSignKey();
        String hoscode = byId.getHoscode();

        // TODO 发送短信
        return Result.ok();
    }

}
