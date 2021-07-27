package stu.cfl.yygh.cmn.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import stu.cfl.yygh.model.cmn.Dict;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictService extends IService<Dict> {

    List<Dict> findChlidData(Long id);

    /**
     * 导出
     * @param response
     */
    void exportData(HttpServletResponse response);

    void importDictData(MultipartFile file);

}
