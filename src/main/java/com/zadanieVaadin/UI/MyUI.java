package com.zadanieVaadin.UI;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.zadanieVaadin.dao.CurrencyDao;
import com.zadanieVaadin.daoImpl.CurrencyDaoImpl;
import com.zadanieVaadin.domain.Currency;
import com.zadanieVaadin.nbp.Table;
import com.zadanieVaadin.service.CurrencyService;

import javax.servlet.annotation.WebServlet;
import java.util.Map;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    private final CurrencyDao currencyDao = new CurrencyDaoImpl();
    private TableGrid tableGrid = new TableGrid();
    private Table table = new Table();
    private CurrencyService currencyService = new CurrencyService(currencyDao);


    private Label createMostImportantCurrenciesLabel(){
        Label label = new Label();

        label.setValue("3 kursy które odnotowały najwiekszy wzrost: ");
        for (Currency c : currencyService.getThreeBestCurrencies()){
            label.setValue(label.getValue() + c.getCurrency() + "; ");
        }

        return label;
    }

    private void updateTable(Table table){
        for(Currency c : table.getCurrencyList()){
            currencyDao.addCurrency(c);
        }
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        updateTable(table);

        VerticalLayout layout = new VerticalLayout();

        HorizontalLayout grids = new HorizontalLayout();
        grids.addComponent(tableGrid.createCurrencyTableToday(currencyDao));
        grids.addComponent(tableGrid.createCurrencyTableYesterday(currencyDao));

        layout.addComponent(grids);
        layout.addComponent(createMostImportantCurrenciesLabel());


        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
