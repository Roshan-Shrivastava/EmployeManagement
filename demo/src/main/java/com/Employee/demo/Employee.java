package com.Employee.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
        private Long id;
        private String name;

        @JsonProperty("Mobile_No")
        private String MobileNo;
}

