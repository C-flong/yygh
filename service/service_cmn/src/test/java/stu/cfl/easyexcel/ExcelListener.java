package stu.cfl.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import stu.cfl.yygh.model.acl.User;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<UserData> {

    @Override
    public void invoke(UserData userData, AnalysisContext analysisContext) {
        // 一行一行读取excel内容，从第二行读取
        System.out.println(userData);

    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息" + headMap);
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 执行完后

    }
}
