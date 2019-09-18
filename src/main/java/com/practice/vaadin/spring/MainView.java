package com.practice.vaadin.spring;

import com.practice.vaadin.spring.mytasksview.model.Contract;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;


//********************** How to Run on Intellij idea **********************//
//First time run use [spring-boot:run]
//later use [hammer] icon on left side of run [L>] icon
//then open google chrome for path localhost:[your port]/ *default port is 8080*
//I don't know more direction to run vaadin.
//************************************************************************//
//MainView.Class just extends VerticalLayout is will show like [ TextField ] v
//                                                             [  Button1  ] v
//                                                             [  Button2  ] v
//                                                        on web app
//If it extends HorizontalLayout is will show [ TextField ] > [  Button1  ] > [  Button2  ]
//* [v],[>] is flow display.

@CssImport("styles/testCSS.css")
@Route("home")
@PageTitle("Home")
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    public MainView() {
        addClassName("main-view");
        setSizeFull();
        add(menu());
        homeView();
    }

    public static MenuBar menu(){
        Icon icon = VaadinIcon.SIGN_OUT.create();
        icon.setColor("");
        //Create Menubar
        MenuBar menuBar = new MenuBar();
        Text selected = new Text("");
        Div message = new Div(new Text("Selected: "), selected);
        //Create MenuItem into menuBar [Project][Account]
        MenuItem home = menuBar.addItem("Home");
        home.addClickListener(event -> home.getUI().ifPresent(o -> o.navigate("home")));

        MenuItem tasks = menuBar.addItem("Tasks");
        tasks.getSubMenu().addItem("My Tasks",
                e -> tasks.getUI().ifPresent(o -> o.navigate("myTasks")));
        tasks.getSubMenu().addItem("Scan Document",
                e -> tasks.getUI().ifPresent(o -> o.navigate("document/scan")));
        tasks.getSubMenu().addItem("Check-in",
                e -> tasks.getUI().ifPresent(o -> o.navigate("document/checkin")));
        tasks.getSubMenu().addItem("Additional Scan Document",
                e -> tasks.getUI().ifPresent(o -> o.navigate("document/scan/additional")));
        tasks.getSubMenu().addItem("Re-print Upload File Report",
                e -> tasks.getUI().ifPresent(o -> o.navigate("document/reprint")));

        MenuItem document = menuBar.addItem("Document");
        document.getSubMenu().addItem("View Document",
                e -> document.getUI().ifPresent(o -> o.navigate("document")));
        document.getSubMenu().addItem("Request Document",
                e -> document.getUI().ifPresent(o -> o.navigate("document/request/new")));
        document.getSubMenu().addItem("Submit Document",
                e -> document.getUI().ifPresent(o -> o.navigate("testview")));

//        MenuItem transaction = menuBar.addItem("Transaction");
//        transaction.getSubMenu().addItem("Create Payment",
//                e -> transaction.getUI().ifPresent(o -> o.navigate("login")));
//        transaction.getSubMenu().addItem("Pre-Approve",
//                e -> transaction.getUI().ifPresent(o -> o.navigate("testview")));
//        transaction.getSubMenu().addItem("Approve",
//                e -> transaction.getUI().ifPresent(o -> o.navigate("testview")));

        MenuItem exit = menuBar.addItem(icon);
        exit.addClickListener(event -> exit.getUI().ifPresent(o -> o.navigate("login")));

        menuBar.setOpenOnHover(true);
        return menuBar;
    }

    private void homeView(){
        //set width size in padding
        //Display Something
        setId("header");
        add(new H2("Vaadin!! Test!!! :P"));
        //*require* import com.vaadin.flow.component.textfield.TextField; for ues getValue()
        //Create field => Please type something [ typing here... ]
        TextField field = new TextField("Please type something");
        //Create button [ Display Notice ] and display notification from TextField
        Button buttonTest1 = new Button("Display Notice",
                event ->  Notification.show(field.getValue(), 1000,
                        Notification.Position.MIDDLE));
        //Create button [ Display Label ] and display Label from TextField
        Button buttonTest2 = new Button("Display Label",
                event -> add(new Label(field.getValue())));
        //add all to Layout
        //In this case they are add to VerticalLayout because MainView extends VerticalLayout
        //*if don't add it will not display on wep app,and may be have some error.*
        add(field);
        add(buttonTest1);
        add(buttonTest2);

        //Create FormLayout and component for add to layout
        FormLayout testFormLayout = new FormLayout();
        TextField nameTextField = new TextField("Name");
        nameTextField.setPlaceholder("Typing here...");
        DatePicker testDateField = new DatePicker("Date");

        BeanValidationBinder binder = new BeanValidationBinder(Contract.class);
        binder.forField(nameTextField).bind("contractNo");
        binder.forField(testDateField).bind("createDate");
        binder.setBean(new Contract());

        Button testButton = new Button("Create");
        Checkbox checkBox = new Checkbox("Please type something on the text field ," +
                "then click on the button if you want to dis play.", false);
        checkBox.addClickListener(event -> testButton.setEnabled(true));
        testButton.addClickListener(event -> {
            if (nameTextField.getValue().isEmpty()){
                nameTextField.isRequired();
                nameTextField.setErrorMessage("Please type something!!");
                Notification.show(nameTextField.getErrorMessage());
            } else {
                add(new Label("Name: " + nameTextField.getValue() +
                        " Date: " + testDateField.getValue()));
                testButton.setEnabled(false);
                checkBox.clear();
            }
        });
        testButton.setEnabled(false);
        //add component to FormLayout
        testFormLayout.add(nameTextField,testDateField,testButton,checkBox);
        //add FromLayout to Main Layout (VerticalLayout in this case)
        this.add(testFormLayout);
    }

}
