package com.practice.vaadin.spring.documentview.models;

public enum CheckoutLocation {
    GroupLease,
    BBLBank,
    KBank,
    SST;
    public String getCaption(){
        switch (this) {
            case GroupLease:
                return "GroupLases";
            case BBLBank:
                return "BBL-Bank";
            case KBank:
                return "K-Bank";
            case SST:
                return "SST";
        }
        return "";
    }
}
