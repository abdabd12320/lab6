package com.example.employeemanagementsystem.EmployeeModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
public class Model {

    @NotEmpty(message = "id should be not empty")
    @Size(min = 3,message = "id must be more than 2 characters")
    private String id;
    @NotEmpty(message = "name should be not empty")
    @Size(min = 5,message = "name must be more than 4 characters")
    @Pattern(regexp = "^[a-zA-Z]{5,15}$",message = "name should be only characters")
    private String name;
    @Email
    private String email;
    @Digits(message = "phoneNumber should be 10 digits", integer = 10, fraction = 0)
    @Pattern(regexp = "^(05)(\\d){8}$",message = "phoneNumber should be start with 05 and 10 digits")
    private String phoneNumber;
    @NotNull(message = "age must be not null")
    @Digits(integer = 3,fraction = 0,message = "age should be numbers")
    @Min(value = 26,message = "age must be more than 25")
    private int age;
    @NotEmpty(message = "position should be not empty")
    @Pattern(regexp = "supervisor|coordinator",message = "position should be supervisor or coordinator")
    private String position;
    @AssertFalse(message = "onLeave must be false")
    private boolean onLeave;
    @NotNull(message = "hireDate should be not null")
    @PastOrPresent(message = "hireDate should be a date in the past or the present")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date hireDate;
    @NotNull(message = "annualLeave should be not null")
    @Positive(message = "annualLeave should be positive")
    private double annualLeave;
}