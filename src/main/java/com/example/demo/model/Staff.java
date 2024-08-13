package com.example.demo.model;

import com.example.demo.model.Enum.AppointmentStatus;
import com.example.demo.model.Enum.StaffType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Data
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;
    @Enumerated(EnumType.STRING)
    private StaffType staffType;
    private String possition;
    private String depertment;
    private String officeNumber;
    private Long userId;

    public  Staff(Long staffId){
        this.staffId =staffId;
    }


    public Staff() {

    }
}
