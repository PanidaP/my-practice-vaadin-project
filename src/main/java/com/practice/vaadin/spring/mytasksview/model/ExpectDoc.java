package com.practice.vaadin.spring.mytasksview.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpectDoc {
    private Long id;
    private LocalDate requestDate;
    private String requester;
    private int expectDocCount;
    private String requestCh;
}
