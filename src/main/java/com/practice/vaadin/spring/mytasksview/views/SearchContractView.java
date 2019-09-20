package com.practice.vaadin.spring.mytasksview.views;

import com.practice.vaadin.spring.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("document/scan/additional")
public class SearchContractView extends VerticalLayout {

    public SearchContractView(){
        add(MainView.menu());
        searchPage();
    }

    private void searchPage(){
        Div page = new Div();
        page.setWidthFull();
        page.setHeightFull();
        page.setTitle("Search Contract");

        Icon logoV = new Icon(VaadinIcon.SEARCH);
        logoV.getStyle().set("cursor", "pointer");
        logoV.addClickListener(
                event -> page.setText("The VAADIN_V icon was clicked!"));

        TextField field = new TextField("Contract No.");
        Button button = new Button(logoV);

        page.add(field);
        page.add(button);
        add(page);
    }

}
