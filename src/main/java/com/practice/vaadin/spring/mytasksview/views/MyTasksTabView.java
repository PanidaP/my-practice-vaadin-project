package com.practice.vaadin.spring.mytasksview.views;

import com.practice.vaadin.spring.MainView;
import com.practice.vaadin.spring.mytasksview.model.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
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

@Route("myTasks")
public class MyTasksTabView extends VerticalLayout {

    public MyTasksTabView() {
        this.setWidthFull();
        add(MainView.menu());
        taps();
    }

    private void taps() {
        Tab tab1 = new Tab("Check out");
        Div page1 = new Div();
        Select< Type > selecteType = new Select();
        selecteType.setLabel("Type");
        selecteType.setPlaceholder("Select Type");
        selecteType.setItemLabelGenerator(Type::getCaption);
        selecteType.setItems(EnumSet.allOf(Type.class));
        selecteType.addValueChangeListener(event -> {
            if (!event.getSource().isEmpty()) {
                if (event.getValue().equals(Type.CSD)) {
                } else if (event.getValue().equals(Type.OTHER)) {
                }
            } else {
                Notification.show("Please select type.", 1000,
                        Notification.Position.MIDDLE);
            }
        });
        page1.add(selecteType);
        page1.add(checkOut());

        Tab tab2 = new Tab("Follow up");
        Div page2 = new Div();
        Grid< FollowContract > followContractGrid = followUp();
        page2.add(followContractGrid);
        page2.setVisible(false);

        Tab tab3 = new Tab("Expected Document");
        Div page3 = new Div();
        Select< Type > selecteType2 = new Select();
        selecteType2.setLabel("Type");
        selecteType2.setPlaceholder("Select Type");
        selecteType2.setItemLabelGenerator(Type::getCaption);
        selecteType2.setItems(EnumSet.allOf(Type.class));
        selecteType2.addValueChangeListener(event -> {
            if (!event.getSource().isEmpty()) {
                if (event.getValue().equals(Type.CSD)) {
                } else if (event.getValue().equals(Type.OTHER)) {
                }
            } else {
                Notification.show("Please select type.", 1000,
                        Notification.Position.MIDDLE);
            }
        });
        TextField lotField = new TextField("Lot");
        List< ExpectDoc > expectDocs = new ArrayList<>();
        expectDocs.add(new ExpectDoc(2l, LocalDate.now().minusDays(4),
                "q", 2, "q"));
        expectDocs.add(new ExpectDoc(1l, LocalDate.now().minusDays(2),
                "s", 4, "x"));
        Grid< ExpectDoc > expectDocGrid = new Grid<>();
        expectDocGrid.setItems(expectDocs);
        Grid.SelectionMode mode = Grid.SelectionMode.SINGLE;
        expectDocGrid.setSelectionMode(mode);
        {
            expectDocGrid.addColumn(ExpectDoc::getId,"Id").setHeader("Id");
            expectDocGrid.addColumn(ExpectDoc::getRequestDate).setHeader("Request Date");
            expectDocGrid.addColumn(ExpectDoc::getRequester).setHeader("Requester");
            expectDocGrid.addColumn(ExpectDoc::getExpectDocCount).setHeader("Expected Document Count");
            expectDocGrid.addColumn(ExpectDoc::getRequestCh).setHeader("Request Channel");
        }
        page3.add(selecteType2);
        page3.add(lotField);
        page3.add(expectDocGrid);
        page3.setVisible(false);

        Map< Tab, Component > tabsToPages = new HashMap<>();
        tabsToPages.put(tab1, page1);
        tabsToPages.put(tab2, page2);
        tabsToPages.put(tab3, page3);
        Tabs tabs = new Tabs(tab1, tab2, tab3);
        tabs.setWidthFull();
        Div pages = new Div(page1, page2, page3);
        pages.setWidthFull();
        Set< Component > pagesShown = Stream.of(page1)
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

    private Grid< CheckoutContract > checkOut() {
        List< CheckoutContract > contracts = new ArrayList<>();
        {
            contracts.add(new CheckoutContract(1l, "1234567890", Type.CSD, Status.pending,
                    "BB", "GL", LocalDate.now()));
            contracts.add(new CheckoutContract(2l, "0056789892", Type.CSD, Status.pending,
                    "AA", "GL", LocalDate.now().plusDays(3)));
            contracts.add(new CheckoutContract(3l, "5674476858", Type.CSD, Status.pending,
                    "LL", "GL", LocalDate.now().minusDays(1)));
            contracts.add(new CheckoutContract(4l, "0765457687", Type.CSD, Status.pending,
                    "PP", "GL", LocalDate.now()));
            contracts.add(new CheckoutContract(6l, "6698870821", Type.CSD, Status.pending,
                    "YY", "GL", LocalDate.now().minusDays(5)));
            contracts.add(new CheckoutContract(5l, "2324543535", Type.CSD, Status.pending,
                    "OO", "GL", LocalDate.now().plusDays(7)));
            contracts.add(new CheckoutContract(7l, "2121325453", Type.CSD, Status.pending,
                    "EE", "GL", LocalDate.now()));
        }
        Grid< CheckoutContract > grid = new Grid<>();
        grid.setItems(contracts);
        Grid.SelectionMode mode = Grid.SelectionMode.SINGLE;
        grid.setSelectionMode(mode);
        {
            grid.addColumn(CheckoutContract::getContractNo, "Contract No.")
                    .setHeader("Contract No.");
            grid.addColumn(CheckoutContract::getBorrower, "Borrower")
                    .setHeader("Borrower");
            grid.addColumn(CheckoutContract::getCheckoutLocation, "Location")
                    .setHeader("Location");
            grid.addColumn(CheckoutContract::getDueDate, "Due Date")
                    .setHeader("Due Date");
            grid.addColumn(CheckoutContract::getStatus, "Status")
                    .setHeader("Status");
            grid.addColumn(CheckoutContract::getType, "Type")
                    .setHeader("Type");
            grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS,
                    GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        }

// you can set the sort order from server-side with the grid.sort method
        grid.addItemDoubleClickListener(event -> {
            grid.getUI().ifPresent(o -> o.navigate("document-request"));
        });
        return grid;
    }

    private Grid< FollowContract > followUp() {
        List< FollowContract > contracts = new ArrayList<>();
        {
            contracts.add(new FollowContract(1l, "0001", "นายหนึ่ง หนึ่ง", "L0001", String.valueOf(DocumentType.RegistrationSet),
                    "a", "D001", 1, 2, Status.checkOut, "CSD", "GL", LocalDate.now().minusDays(2)));
            contracts.add(new FollowContract(2l, "0002", "นายสอง หนึ่ง", "L0001", String.valueOf(DocumentType.RegistrationSet),
                    "a", "D001", 1, 2, Status.checkOut, "CSD", "GL", LocalDate.now().minusDays(3)));
            contracts.add(new FollowContract(3l, "0003", "นางหนึ่ง สอง", "L0001", String.valueOf(DocumentType.RegistrationSet),
                    "a", "D001", 1, 2, Status.checkOut, "CSD", "GL", LocalDate.now().minusDays(5)));
            contracts.add(new FollowContract(4l, "0004", "นางสาวสาม แปด", "L0001", String.valueOf(DocumentType.RegistrationSet),
                    "a", "D001", 1, 2, Status.checkOut, "CSD", "GL", LocalDate.now().minusDays(1)));
            contracts.add(new FollowContract(5l, "0005", "นายเจ็ด หนึ่ง", "L0001", String.valueOf(DocumentType.RegistrationSet),
                    "a", "D001", 1, 2, Status.checkOut, "CSD", "GL", LocalDate.now().minusDays(3)));

        }
        Grid< FollowContract > grid = new Grid<>();
        grid.setItems(contracts);
        Grid.SelectionMode mode = Grid.SelectionMode.SINGLE;
        grid.setSelectionMode(mode);
        {
            grid.addColumn(FollowContract::getContractNo, "Contract No.")
                    .setHeader("Contract No.");
            grid.addColumn(FollowContract::getBorrower, "Check out by")
                    .setHeader("Check out by");
            grid.addColumn(FollowContract::getTypeFollow, "Type")
                    .setHeader("Type");
            grid.addColumn(FollowContract::getLots, "Lot")
                    .setHeader("Lot");
            grid.addColumn(FollowContract::getCustomerName, "Name")
                    .setHeader("Name");
            grid.addColumn(FollowContract::getStatus, "Status")
                    .setHeader("Status");
            grid.addColumn(FollowContract::getApproveBy, "Approve by")
                    .setHeader("Approve by");
            grid.addColumn(FollowContract::getDueDate, "Due Date")
                    .setHeader("Return Due Date");
            grid.addColumn(FollowContract::getDocTrxNo, "DocTrx No.")
                    .setHeader("DocTrx No.");
            grid.addColumn(FollowContract::getCheckoutLocation, "Location")
                    .setHeader("Location");
            grid.addColumn(FollowContract::getLotSequence, "Lot Sequence")
                    .setHeader("Lot Sequence");
            grid.addColumn(FollowContract::getSequenceNo, "Sequence No.")
                    .setHeader("Sequence No.");
            grid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT,
                    GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        }
        return grid;
    }

}
