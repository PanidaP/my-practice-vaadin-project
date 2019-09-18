package com.practice.vaadin.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.io.FileNotFoundException;

import static com.practice.vaadin.spring.Storage.download;

@Route("testview")
public class TestView extends VerticalLayout {

    public TestView() throws FileNotFoundException {
        add(MainView.menu());
        test();
    }

    private void test() throws FileNotFoundException {
        Button button = new Button("Click!!!!");
        Dialog dialog = new Dialog();
        dialog.add(new Label("Close me with the " +
                "esc-key or an outside click"));
        dialog.add(download());
        dialog.setWidth("400px");
        dialog.setHeight("150px");
        button.addClickListener(event -> dialog.open());
        add(button);
    }
}
