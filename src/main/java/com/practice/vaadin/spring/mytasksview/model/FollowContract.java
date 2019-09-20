package com.practice.vaadin.spring.mytasksview.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vaadin.flow.component.polymertemplate.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FollowContract {
    @Id
    @Generated
    private Long id;
    @NotNull
    private String contractNo;
    @NotEmpty
    private String customerName;
    @NotNull
    private String lots;
    @NotNull
    private String typeFollow;
    @NotNull
    private String approveBy;
    @NotNull
    private String docTrxNo;
    @NotNull
    private int lotSequence;
    @NotNull
    private int sequenceNo;
    @NotNull
    private Status status;
    @NotNull
    private String borrower;
    @NotNull
    private String checkoutLocation;
    @NotNull
    private LocalDate dueDate;
}
