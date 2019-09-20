package com.practice.vaadin.spring.mytasksview.views;

import com.practice.vaadin.spring.MainView;
import com.practice.vaadin.spring.mytasksview.model.DocumentType;
import com.practice.vaadin.spring.mytasksview.model.FollowContract;
import com.practice.vaadin.spring.mytasksview.model.ScanDocumentToUpload;
import com.practice.vaadin.spring.mytasksview.model.Status;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileBuffer;
import com.vaadin.flow.router.Route;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route("document/scan")
public class ScanDocument extends VerticalLayout {
    public ScanDocument(){
        add(MainView.menu());
        scanTabs();
    }

    private void scanTabs() {
        Tab tab1 = new Tab("Upload");
        Div page1 = new Div();
        Select< DocumentType > selectType = new Select();
        selectType.setLabel("Set");
        selectType.setPlaceholder("Select Type");
        selectType.setItemLabelGenerator(DocumentType::getCaption);
        selectType.setItems(EnumSet.allOf(DocumentType.class));
        TextField numbeyField = new TextField("Contract No.");

        Button upDocButton =  new Button("Upload Documents");
        upDocButton.setEnabled(false);
        Button clearListButton =  new Button("Clear Scan List");
        clearListButton.addClickListener(event -> upDocButton.setEnabled(false));
        Button pairDocButton =  new Button("Pair Documents");
        pairDocButton.addClickListener(event -> upDocButton.setEnabled(true));

        MultiFileBuffer multiFileBuffer = new MultiFileBuffer();
        Upload upload = new Upload(multiFileBuffer);
        upload.addFinishedListener(e -> {
            InputStream inputStream =
                    multiFileBuffer.getInputStream(e.getFileName());
        });
        upload.setUploadButton(new Button("Add Documents"));
        upload.setWidth("20%");

        Button clearDocButton =  new Button("Clear Documents");
        clearDocButton.addClickListener(event ->
                upDocButton.setEnabled(false)
        );

        List< ScanDocumentToUpload > scanDocuments = new ArrayList<>();
        {
            scanDocuments.add(new ScanDocumentToUpload(1l,DocumentType.RegistrationSet,
                    "q",2,"e","d"));
        }
        Grid<ScanDocumentToUpload> grid = new Grid();
        grid.setItems(scanDocuments);
        Grid.SelectionMode mode = Grid.SelectionMode.MULTI;
        grid.setSelectionMode(mode);
        {
            grid.addColumn(ScanDocumentToUpload::getId).setHeader("Contract No.");
            grid.addColumn(ScanDocumentToUpload::getDocumentType).setHeader("Type");
            grid.addColumn(ScanDocumentToUpload::getName).setHeader("Name");
            grid.addColumn(ScanDocumentToUpload::getPage).setHeader("Page");
            grid.addColumn(ScanDocumentToUpload::getScanned).setHeader("Scanned");
            grid.addColumn(ScanDocumentToUpload::getRemove).setHeader("Remove");
        }

        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.add(clearDocButton);
        hLayout.add(clearListButton);
        hLayout.add(pairDocButton);
        hLayout.add(upDocButton);
        VerticalLayout vLayout = new VerticalLayout();
        vLayout.add(hLayout);
        vLayout.add(grid);
        HorizontalLayout layout = new HorizontalLayout();
        layout.add(upload);
        layout.add(vLayout);
        //..............................................................
        page1.add(selectType);
        page1.add(numbeyField);
        page1.add(layout);

        Tab tab2 = new Tab("Export");
        Div page2 = new Div();
        page2.setVisible(false);

        List< FollowContract > contracts = new ArrayList<>();
        {
            contracts.add(new FollowContract(1l, "0001", "นายหนึ่ง หนึ่ง", "L0001", String.valueOf(DocumentType.RegistrationSet),
                    "a", "D001", 1, 2, Status.checkOut, "CSD", "GL", LocalDate.now().minusDays(2)));
            contracts.add(new FollowContract(2l, "0002", "นายสอง หนึ่ง", "L0001", String.valueOf(DocumentType.RegistrationSet),
                    "a", "D001", 1, 2, Status.checkOut, "CSD", "GL", LocalDate.now().minusDays(3)));
            contracts.add(new FollowContract(3l, "0003", "นางหนึ่ง สอง", "L0001", String.valueOf(DocumentType.RegistrationSet),
                    "a", "D001", 1, 2, Status.checkOut, "CSD", "GL", LocalDate.now().minusDays(5)));
        }
        Grid< FollowContract > contractGrid = new Grid<>();
        contractGrid.setItems(contracts);
        Grid.SelectionMode mode1 = Grid.SelectionMode.MULTI;
        contractGrid.setSelectionMode(mode);
        {
            contractGrid.addColumn(FollowContract::getDocTrxNo, "DocTrx No.")
                    .setHeader("Document Trx No.");
            contractGrid.addColumn(FollowContract::getContractNo, "Contract No.")
                    .setHeader("Contract No.");
            contractGrid.addColumn(FollowContract::getLots, "Lot")
                    .setHeader("Lot");
            contractGrid.addColumn(FollowContract::getCustomerName, "Name")
                    .setHeader("Name");
            contractGrid.addColumn(FollowContract::getSequenceNo, "Sequence No.")
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
        tabs.setHeightFull();
        Div pages = new Div(page1, page2);
        pages.setWidthFull();
        tabs.setHeightFull();
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
