package com.practice.vaadin.spring.mytasksview.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contract {
    private Long id;
    @NotNull
    private String contractNo;
    @NotNull
    private Type type;
    @NotNull
    private Status status;
    @NotNull
    private LocalDate createDate;
}
