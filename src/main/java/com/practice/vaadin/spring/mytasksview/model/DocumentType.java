package com.practice.vaadin.spring.mytasksview.model;

public enum DocumentType {
    ContractSet,
    RegistrationSet;
    public String getCaption() {
        switch (this) {
            case ContractSet:
                return "Contract Set";
            case RegistrationSet:
                return "Registration Set";
        }
        return "";
    }
}
