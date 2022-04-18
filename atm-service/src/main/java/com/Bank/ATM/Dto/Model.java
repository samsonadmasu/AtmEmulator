package com.Bank.ATM.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Model implements Serializable {
    private Long id;
    @JsonIgnore
    private Date createdOn;
    @JsonIgnore
    private Date updatedOn;
}
