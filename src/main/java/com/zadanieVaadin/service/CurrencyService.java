package com.zadanieVaadin.service;

import com.zadanieVaadin.dao.CurrencyDao;
import com.zadanieVaadin.domain.Currency;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jbielawski on 30.11.2017 <jakub.bielawski@coi.gov.pl>
 */
public class CurrencyService {
    private Map<String, List<Double>> comparedCurrencies = new LinkedHashMap<>();
    private CurrencyDao currencyDao;
    private Map<String, Double> differentMids = new LinkedHashMap<>();

    public CurrencyService() {
    }

    public CurrencyService(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    public Map<String, Double> getDifferentMids() {
        return differentMids;
    }

    public CurrencyDao getCurrencyDao() {

        return currencyDao;
    }

    public void setCurrencyDao(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    public Map<String, List<Double>> getComparedCurrencies() {
        return comparedCurrencies;
    }

    private void setComparedCurrencies() {
        List<Currency> temp = currencyDao.getAll();

        temp.stream().forEach(c -> {
            if(!comparedCurrencies.containsKey(c.getCurrency())){
                List<Double> mids = new ArrayList<>();
                mids.add(c.getMid());
                comparedCurrencies.put(c.getCurrency(), mids);
            }
            else{
                comparedCurrencies.get(c.getCurrency()).add(c.getMid());
            }
        });
    }

    public void setDifferentMids(){
        for (Map.Entry<String, List<Double>> entry : comparedCurrencies.entrySet()) {
            differentMids.put(entry.getKey(), entry.getValue().get(0) - entry.getValue().get(1));
        }
    }

    public List<Currency> getThreeBestCurrencies(){
        List<Currency> best = new ArrayList<>();
        setComparedCurrencies();
        setDifferentMids();
        //sortowanie mapy po wartosci:
        differentMids = differentMids.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        int counter = 0;
        for (Map.Entry entry : differentMids.entrySet()) {
            Optional<Currency> currency = currencyDao.getCurrencyByCurrency(entry.getKey().toString());
            best.add(currency.get());
            counter ++;
            if(counter == 2) break;
        }

        return best;
    }

    public Boolean compareCurrenciesByMids(Currency c1, Currency c2){
        if(c1.getMid() > c2.getMid()){
            return false;
        }
        return true;
    }
}
