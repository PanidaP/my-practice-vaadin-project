package com.practice.vaadin.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends VerticalLayout {

    public LoginView() {
        loginView();
    }

    public void loginView() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(new H2("Login"));
        verticalLayout.add(new Label("Please enter username and password for login"));
        TextField usernameField = new TextField("Username");
        PasswordField passwordField = new PasswordField("Password");
        Button loginButton = new Button("Login");
        loginButton.addClickListener(event -> {
            if (usernameField.getValue().equals("abc") && passwordField.getValue().equals("111")) {
                loginButton.getUI().ifPresent(o -> o.navigate("home"));
            }
            }
        );
            verticalLayout.add(usernameField);
            verticalLayout.add(passwordField);
            verticalLayout.add(loginButton);
            add(verticalLayout);
        }
    }
