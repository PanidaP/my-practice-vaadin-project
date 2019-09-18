package com.practice.vaadin.spring.documentview.views;

import com.practice.vaadin.spring.MainView;
import com.practice.vaadin.spring.documentview.models.CheckoutLocation;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.EnumSet;

@Route("document/request/new")
public class RequestDocument extends VerticalLayout {
    public RequestDocument() throws FileNotFoundException {
        setWidthFull();
        add(MainView.menu());
        requestDoc();
    }

    private void requestDoc() throws FileNotFoundException {
        Button submitButton = new Button("Submit");
        Button clearButton = new Button("Clear");

        SplitLayout splitLayout = new SplitLayout();

        Div page = new Div();
        Button prepareButton = new Button("Prepare");
        prepareButton.setIcon(new Icon(VaadinIcon.FILE_O));
        DatePicker datePicker = new DatePicker("Return date");
        page.add(prepareButton);
        page.add(datePicker);
        page.setWidthFull();

        Div page1 = new Div();
        page1.setWidthFull();
        Div page2 = new Div();
        page2.setWidthFull();
        page1.add(new H5("Permanent Checkout"));
        InputStream inputStream = new FileInputStream(
                new File("C:\\tmp\\aa.jpg"));
        StreamResource resource = new StreamResource("123.pdf", () -> inputStream);
        Anchor link = new Anchor(resource,"");
        link.getElement().setAttribute("download", true);
        Button button1 = new Button("Download");
        button1.setIcon(new Icon(VaadinIcon.DOWNLOAD_ALT));
        link.add(button1);
        TextField field1 = new TextField("Contract No.");
        page1.add(link);
        page1.add(field1);

        page2.add(new H5("Temporary Checkout"));
        InputStream fileInputStream = new FileInputStream(
                new File("C:\\tmp\\123.jpg"));
        StreamResource streamResource = new StreamResource("123.pdf", () -> fileInputStream);
        Anchor link2 = new Anchor(streamResource,"");
        link2.getElement().setAttribute("download", true);
        Button button2 = new Button("Download");
        button2.setIcon(new Icon(VaadinIcon.DOWNLOAD_ALT));
        link2.add(button2);
        TextField field2 = new TextField("Contract No.");
        Select< CheckoutLocation > select = new Select();
        select.setLabel("Check-out Location");
        select.setPlaceholder("Select Location");
        select.setItemLabelGenerator(CheckoutLocation::getCaption);
        select.setItems(EnumSet.allOf(CheckoutLocation.class));
        page2.add(link2);
        page2.add(select);
        page2.add(field2);

        splitLayout.addToPrimary(page1);
        splitLayout.addToSecondary(page2);
        splitLayout.setWidth("95%");
        splitLayout.setHeight("75%");

        page.add(splitLayout);
        add(submitButton);
        add(clearButton);
        add(page);
    }

}
