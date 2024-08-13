package com.example.demo.dto;


import com.example.demo.model.Staff;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class StaffResponse {
    private Long staffId;

    private String staffType;
    private String possition;
    private String depertment;
    private String officeNumber;
    private Long userId;


    public StaffResponse(Staff staff) {
        this.staffId = staff.getStaffId();
        this.staffType = String.valueOf(staff.getStaffType());

        this.possition = staff.getPossition(); // Correct spelling
        this.depertment = staff.getDepertment(); // Correct spelling
        this.officeNumber = staff.getOfficeNumber();
        this.userId = staff.getUserId();
    }

    public StaffResponse(ResponseEntity updated) {
    }
}
