package stu.cfl.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;

public class TestRead {
    public static void main(String[] args) {
        // 设置excel文件路径和文件名称
        String filename = "D:\\WorkSpace\\IdeaProjects\\yygh_parent\\service\\service_cmn\\src\\test\\java\\stu\\cfl\\easyexcel\\01.xlsx";

        EasyExcel.read(filename, UserData.class, new ExcelListener())
                .sheet()
                .doRead();
    }
}
