package com.zadanieVaadin.service;

import com.zadanieVaadin.dao.CurrencyDao;
import com.zadanieVaadin.domain.Currency;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author jbielawski on 30.11.2017 <jakub.bielawski@coi.gov.pl>
 */
public class CurrencyService {
    private Map<String, List> comparedCurrencies = new LinkedHashMap<>();

    public Map<String, List> getComparedCurrencies() {
        return comparedCurrencies;
    }

    public void setComparedCurrencies(CurrencyDao currencyDao) {
        List<Currency> temp = currencyDao.getAll();

        //comparedCurrencies =
        /*temp.stream().forEach(currency -> {
            if (comparedCurrencies.keySet().equals(currency.getCode())) {
                comparedCurrencies.put()
                *//*Currency c = cmap.get
                cmap.put(currency, )*//*
            }
            else{

            }
        });*/
    }

    /*public List<Currency> getThreeBestCurrencies(CurrencyDao currencyDao){
        List<Currency> best = new ArrayList<>();

        best = currencyDao.getAll().stream().filter((e1, e2) -> )

        return best;
    }*/
}
