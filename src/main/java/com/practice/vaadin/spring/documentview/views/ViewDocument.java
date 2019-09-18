package com.practice.vaadin.spring.documentview.views;

import com.practice.vaadin.spring.MainView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("document")
public class ViewDocument extends VerticalLayout {
    public ViewDocument() {
        add(MainView.menu());
        viewDocument();
    }

    private void viewDocument() {
        TextField textField = new TextField("Contract No.");
        textField.addKeyDownListener(Key.ENTER, event ->
                Notification.show(textField.getValue(), 1500,
                        Notification.Position.MIDDLE)
        );

        add(textField);
    }


}
