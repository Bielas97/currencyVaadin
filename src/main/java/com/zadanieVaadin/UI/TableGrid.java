package com.zadanieVaadin.UI;

import com.vaadin.ui.Grid;
import com.zadanieVaadin.dao.CurrencyDao;
import com.zadanieVaadin.domain.Currency;
import com.zadanieVaadin.nbp.Table;

import java.util.stream.Stream;

/**
 * @author jbielawski on 30.11.2017 <jakub.bielawski@coi.gov.pl>
 */
public class TableGrid {
    private static Table table = new Table();

    static Grid<Currency> createCurrencyTableToday(CurrencyDao currencyDao){
        Grid<Currency> currencyTable = new Grid<>(Currency.class);

        currencyTable.setColumns("id", "code", "currency", "mid");
        currencyTable.setCaption("DZISIEJSZE KURSY");

        Stream<Currency> streamOfItems = currencyDao.getAll().stream().filter(e -> e.getDateOfPublication().equals(table.getDateOfPublicationToday()));
        currencyTable.setItems(streamOfItems);
        currencyTable.setSizeFull();

       /* VerticalLayout verticalLayout = new VerticalLayout(currencyTable);
        verticalLayout.setWidth("1000");*/

        /*currencyTable.setStyleGenerator(c -> {
            if(c.getMid() > 1){
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                return "dead";
            }
            return "highlight-red";
        });*/

        currencyTable.setWidth("900");

        return currencyTable;
    }

    static Grid<Currency> createCurrencyTableYesterday(CurrencyDao currencyDao){
        Grid<Currency> currencyTable = new Grid<>(Currency.class);

        currencyTable.setColumns("id", "code", "currency", "mid");
        currencyTable.setCaption("WCZORAJSZE KURSY");

        Stream<Currency> streamOfItems = currencyDao.getAll().stream().filter(e -> e.getDateOfPublication().equals(table.getDateOfPublicationFirstTable()));
        currencyTable.setItems(streamOfItems);
        currencyTable.setSizeFull();

        /*VerticalLayout verticalLayout = new VerticalLayout(currencyTable);
        verticalLayout.setWidth("1000");*/

        currencyTable.setWidth("900");

        return currencyTable;
    }
}
