package cn.edu.zjou.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeAndDeptCountDto {

    private String typeName;
    private List<DeptAndCount> deptAndCountList;


    @Data
    public static class DeptAndCount{
        private String deptName;
        private Long count;
    }
}
