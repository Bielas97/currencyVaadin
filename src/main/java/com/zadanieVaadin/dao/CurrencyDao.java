package com.zadanieVaadin.dao;

import com.zadanieVaadin.domain.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyDao {
    void addCurrency(Currency currency);
    void updateCurrency(Currency currency);
    void deleteCurrency(Long id);
    List<Currency> getAll();
    Optional<Currency> getCurrencyById(Long id);
    Optional<Currency> getCurrencyByCode(String code);
    Optional<Currency> getCurrencyByCurrency(String c);
}
