package com.practice.vaadin.spring.mytasksview.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScanDocumentToUpload {
    private Long id;
    private DocumentType documentType;
    private String name;
    private int page;
    private String scanned;
    private String remove;
}
