package cn.edu.zjou.dto;

import lombok.Data;

import java.util.List;

@Data
public class TypeAndDeptCountDto {

    private String typeName;
    private List<DeptAndCount> deptAndCountList;


    @Data
    public static class DeptAndCount{
        private String deptName;
        private Long count;
    }
}
