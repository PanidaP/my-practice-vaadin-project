package com.practice.vaadin.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import java.io.*;


@Route("test2")
public class Storage extends VerticalLayout {
    public Storage() {
    }

    //download
    protected static Anchor download() throws FileNotFoundException {
        //Can use with .txt .pdf .jpg .etc
        InputStream inputStream = new FileInputStream(
                new File("C:\\Users\\44606\\Desktop\\novel\\QQ farm\\CH 1 - 100.pdf"));
        StreamResource resource = new StreamResource("123.pdf", () -> inputStream);
        Anchor link = new Anchor(resource,"Download");
        link.getElement().setAttribute("download", true);
        link.add(new Button(new Icon(VaadinIcon.DOWNLOAD_ALT)));
        return link;
    }
}
