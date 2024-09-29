package com.ar.cabBooingApplication.dto;

import com.ar.cabBooingApplication.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiderDto {
    private UserDto user;
    private Double rating;

}
