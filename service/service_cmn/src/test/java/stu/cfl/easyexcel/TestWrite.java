package stu.cfl.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;

public class TestWrite {
    public static void main(String[] args) {
        // 设置excel文件路径和文件名称
        String filename = "D:\\WorkSpace\\IdeaProjects\\yygh_parent\\service\\service_cmn\\src\\test\\java\\stu\\cfl\\easyexcel\\01.xlsx";

        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserData userData = new UserData();
            userData.setUid(i);
            userData.setUsername("melody" + i);
            list.add(userData);
        }

        EasyExcel.write(filename, UserData.class)
                .sheet("用户信息")
                .doWrite(list);
    }
}
