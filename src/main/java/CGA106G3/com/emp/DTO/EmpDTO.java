package CGA106G3.com.emp.DTO;

import lombok.Data;

import java.sql.Date;

@Data
public class EmpDTO {
    private  Integer empno;
    private String ename;
    private Date hiredate;
    private Integer empsta;
    private String emppw;


}
