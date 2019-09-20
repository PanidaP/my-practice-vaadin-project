package com.practice.vaadin.spring.mytasksview.views;

import com.practice.vaadin.spring.MainView;
import com.practice.vaadin.spring.mytasksview.model.CheckInContract;
import com.practice.vaadin.spring.mytasksview.model.DocumentType;
import com.practice.vaadin.spring.mytasksview.model.Status;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route("document/checkin")
public class CheckInDocView extends VerticalLayout {
    public CheckInDocView(){
        add(MainView.menu());
        checkInTab();
    }

    private void checkInTab(){
        List< CheckInContract > contracts = new ArrayList<>();
        {
            contracts.add(new CheckInContract(1l, "0001", "นายหนึ่ง หนึ่ง", "L0001", String.valueOf(DocumentType.RegistrationSet),
                    "a", "D001", 1, 2, Status.checkOut, "CSD", "GL", LocalDate.now()));
            contracts.add(new CheckInContract(2l, "0002", "นายสอง หนึ่ง", "L0001", String.valueOf(DocumentType.RegistrationSet),
                    "a", "D001", 1, 2, Status.checkOut, "CSD", "GL", LocalDate.now()));
            contracts.add(new CheckInContract(3l, "0003", "นางหนึ่ง สอง", "L0001", String.valueOf(DocumentType.RegistrationSet),
                    "a", "D001", 1, 2, Status.checkOut, "CSD", "GL", LocalDate.now()));
        }
        Tab tab1 = new Tab("Upload");
        Div page1 = new Div();
        Select< DocumentType > typeSelect = new Select();
        typeSelect.setLabel("Set");
        typeSelect.setPlaceholder("Select Type");
        typeSelect.setItemLabelGenerator(DocumentType::getCaption);
        typeSelect.setItems(EnumSet.allOf(DocumentType.class));
        TextField numberField = new TextField("Contract No.");

        Grid< CheckInContract > grid = new Grid<>();
        grid.setItems(contracts);
        Grid.SelectionMode mode = Grid.SelectionMode.MULTI;
        grid.setSelectionMode(mode);
        {
            grid.addColumn(CheckInContract::getContractNo, "Contract No.")
                    .setHeader("Contract No.");
            grid.addColumn(CheckInContract::getTypeFollow, "Type")
                    .setHeader("Type");
            grid.addColumn(CheckInContract::getLots, "Lot")
                    .setHeader("Lot");
            grid.addColumn(CheckInContract::getCustomerName, "Name")
                    .setHeader("Name");
            grid.addColumn(CheckInContract::getStatus, "Status")
                    .setHeader("Status");
            grid.addColumn(CheckInContract::getCheckinLocation, "Location")
                    .setHeader("Location");
            grid.addColumn(CheckInContract::getRemove, "Remove")
                    .setHeader("Remove");
            grid.addColumn(CheckInContract::getCreate, "Create Date")
                    .setHeader("Create Date");
            grid.addColumn(CheckInContract::getDocTrxNo, "Document Trx No.")
                    .setHeader("Document Trx No.");
            grid.addColumn(CheckInContract::getRequestBy, "Request by")
                    .setHeader("Request by");
            grid.addColumn(CheckInContract::getLotSequence, "Root Lot Sequence")
                    .setHeader("Root Lot Sequence");
            grid.addColumn(CheckInContract::getSequenceNo, "Sequence No.")
                    .setHeader("Sequence No.");
            grid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT,GridVariant.MATERIAL_COLUMN_DIVIDERS,
                    GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        }

        Button clearButton = new Button("Clear All Document");
        Button checkInButton = new Button("Check-in");

        page1.add(typeSelect);
        page1.add(numberField);
        page1.add(clearButton);
        page1.add(checkInButton);
        page1.add(grid);

        Tab tab2 = new Tab("Export");
        Div page2 = new Div();
        page2.setVisible(false);
        Grid< CheckInContract > contractGrid = new Grid<>();
        contractGrid.setItems(contracts);
        Grid.SelectionMode multi = Grid.SelectionMode.MULTI;
        contractGrid.setSelectionMode(multi);
        {
            contractGrid.addColumn(CheckInContract::getDocTrxNo, "DocTrx No.")
                    .setHeader("Document Trx No.");
            contractGrid.addColumn(CheckInContract::getContractNo, "Contract No.")
                    .setHeader("Contract No.");
            contractGrid.addColumn(CheckInContract::getLots, "Lot")
                    .setHeader("Lot");
            contractGrid.addColumn(CheckInContract::getCustomerName, "Name")
                    .setHeader("Name");
            contractGrid.addColumn(CheckInContract::getSequenceNo, "Sequence No.")
                    .setHeader("Sequence No.");
            contractGrid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT,
                    GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        }

        Button exportButton = new Button("Export PDF");
        Button clearSelectButton = new Button("Clear Select");
        Button clearAllButton = new Button("Clear All");

        page2.add(exportButton);
        page2.add(clearSelectButton);
        page2.add(clearAllButton);
        page2.add(contractGrid);

        Map<Tab, Component > tabsToPages = new HashMap<>();
        tabsToPages.put(tab1, page1);
        tabsToPages.put(tab2, page2);
        Tabs tabs = new Tabs(tab1, tab2);
        tabs.setWidthFull();
        Div pages = new Div(page1, page2);
        pages.setWidthFull();
        Set<Component> pagesShown = Stream.of(page1)
                .collect(Collectors.toSet());
        tabs.addSelectedChangeListener(event -> {
            pagesShown.forEach(page -> page.setVisible(false));
            pagesShown.clear();
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
            pagesShown.add(selectedPage);
        });
        add(tabs);
        add(pages);
    }

}
