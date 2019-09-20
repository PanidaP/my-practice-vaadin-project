package com.practice.vaadin.spring.mytasksview.views;

import com.practice.vaadin.spring.MainView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Route;

@Route("document-request")
public class TaskGridClickView extends VerticalLayout {
    public TaskGridClickView(){
        add(MainView.menu());
        taskGridClick();
    }

    public void taskGridClick(){
        setWidthFull();
        SplitLayout layout = new SplitLayout();
        layout.setWidth("95%");
        layout.setHeight("75%");
        Div page1 = new Div();
        page1.setWidthFull();
        page1.add(new H2("First Page Test Split Layout"));

        Div page2 = new Div();
        page2.setWidthFull();
        page2.add(new H2("Second Page Test Split Layout"));

        layout.addToPrimary(page1);
        layout.addToSecondary(page2);
        add(layout);
    }

}
