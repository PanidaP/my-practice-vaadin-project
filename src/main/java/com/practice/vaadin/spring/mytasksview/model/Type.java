package com.practice.vaadin.spring.mytasksview.model;

public enum Type {
    All,
    CSD,
    OTHER;
    public String getCaption() {
        switch (this) {
            case All:
                return "All";
            case CSD:
                return "CSD";
            case OTHER:
                return "OTHER";
        }
        return "";
    }
}
