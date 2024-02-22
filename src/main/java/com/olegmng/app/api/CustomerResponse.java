package com.olegmng.app.api;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class CustomerResponse {

    private Long id;
    private String name;

}
