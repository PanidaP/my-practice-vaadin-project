package com.practice.vaadin.spring.mytasksview.views;

import com.practice.vaadin.spring.MainView;
import com.practice.vaadin.spring.mytasksview.model.DocumentType;
import com.practice.vaadin.spring.mytasksview.model.ReprintContract;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.EnumSet;

@Route("document/reprint")
public class RePrintView extends VerticalLayout {
    public RePrintView(){
        setWidthFull();
        add(MainView.menu());
        rePrint();
    }

    private void rePrint(){
        Select< DocumentType > typeSelect = new Select<>();
        typeSelect.setItems(EnumSet.allOf(DocumentType.class));
        typeSelect.setLabel("Type");
        typeSelect.setPlaceholder("Select Type");

        TextField contractNoField = new TextField("Contract No.");
        TextField lotField = new TextField("Lot");
        TextField sequenceField = new TextField("Sequence");
        TextField toSequenceField = new TextField("To");

        DatePicker dateField = new DatePicker("Date");
        DatePicker toDateFiled = new DatePicker("To");

        Button addButton = new Button("Add");
        Button clearButton = new Button("Clear");
        Button exportButton = new Button("Export");

        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.add(typeSelect);
        hLayout.add(contractNoField);
        hLayout.add(lotField);
        VerticalLayout vLayout2 = new VerticalLayout();
        vLayout2.add(sequenceField);
        vLayout2.add(toSequenceField);
        VerticalLayout vLayout3 = new VerticalLayout();
        vLayout3.add(dateField);
        vLayout3.add(toDateFiled);
        HorizontalLayout hLayout2 = new HorizontalLayout();
        hLayout2.add(addButton);
        hLayout2.add(clearButton);
        hLayout2.add(exportButton);
        HorizontalLayout layout = new HorizontalLayout();
        layout.add(hLayout,vLayout2,vLayout3);

        Grid< ReprintContract > grid = new Grid<>();
        Grid.SelectionMode multi = Grid.SelectionMode.MULTI;
        grid.setSelectionMode(multi);
        grid.addColumn(ReprintContract::getContractNo).setHeader("Contract No.");
        grid.addColumn(ReprintContract::getCustomerName).setHeader("Name");
        grid.addColumn(ReprintContract::getLots).setHeader("Lots");
        grid.addColumn(ReprintContract::getSequence).setHeader("Sequence");
        grid.addColumn(ReprintContract::getCreateDate).setHeader("Create Date");
        grid.addColumn(ReprintContract::getType).setHeader("Type");
        grid.addColumn(ReprintContract::getAction).setHeader("Action");

        add(layout);
        add(hLayout2);
        add(grid);
    }

}
