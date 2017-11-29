package com.zadanieVaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.zadanieVaadin.dao.CurrencyDao;
import com.zadanieVaadin.daoImpl.CurrencyDaoImpl;
import com.zadanieVaadin.domain.Currency;
import com.zadanieVaadin.nbp.Table;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    private final Grid<Currency> currencyTable = new Grid<>(Currency.class);

    private final CurrencyDao currencyDao = new CurrencyDaoImpl();

    private void updateDB(Table table){
        //TODO: baza danych ma sie updatowac z kazdym urochomieniem aplikacji
        for(Currency c : table.getCurrencyList()){
            currencyDao.addCurrency(c);
        }
    }


    private VerticalLayout createCurrencyTable(){
        currencyTable.setColumns("id", "code", "currency", "mid");
        currencyTable.setCaption("CURRENCY TABLE");

        currencyTable.setItems(currencyDao.getAll());
        currencyTable.setSizeFull();

        VerticalLayout verticalLayout = new VerticalLayout(currencyTable);
        verticalLayout.setWidth("1000");
        return verticalLayout;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
       //TODO updateDB(new Table());
        Table table = new Table();
        updateDB(table);

        System.out.println("------------------------------->  " + table.getDateOfPublicationFirstTable());
        System.out.println("------------------------------->  " + table.getDateOfPublicationSecondTable());

        System.out.println(currencyDao.getCurrencyByCode("THB"));

        VerticalLayout layout = new VerticalLayout();
        layout.addComponents(createCurrencyTable());
        setContent(layout);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
