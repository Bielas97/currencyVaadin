package daoImpl;

import dao.CurrencyDao;
import domain.Currency;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class CurrencyDaoImpl implements CurrencyDao {
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public void addCurrency(Currency currency) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.getTransaction();
        try{
            tx. begin();

            session.persist(currency);

            tx.commit();
        } catch (Exception e){
            if( tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public void updateCurrency(Currency currency) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.getTransaction();
        try{
            tx. begin();

            Currency c = session.get(Currency.class, currency.getId());
            c.setCurrency(currency.getCurrency());
            c.setCode(currency.getCode());
            c.setMid(currency.getMid());

            tx.commit();
        } catch (Exception e){
            if( tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if(session != null){
                session.close();
            }
        }

    }

    @Override
    public void deleteCurrency(Long id) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.getTransaction();
        try{
            tx. begin();

            Currency currency = new Currency();
            currency.setId(id);

            session.delete(currency);

            tx.commit();
        } catch (Exception e){
            if( tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if(session != null){
                session.close();
            }
        }

    }

    @Override
    public List<Currency> getAll() {
        Session session = sessionFactory.openSession();

        Transaction tx = session.getTransaction();
        List<Currency> currencyList = null;
        try{
            tx. begin();

            currencyList = session.createCriteria(Currency.class).list();

            tx.commit();
        } catch (Exception e){
            if( tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if(session != null){
                session.close();
            }
        }
        return currencyList;
    }

    @Override
    public Optional<Currency> getCurrencyById(Long id) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.getTransaction();
        Optional<Currency> currencyOptional = null;
        try{
            tx. begin();

            currencyOptional = Optional.of(session.get(Currency.class, id));

            tx.commit();
        } catch (Exception e){
            if( tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if(session != null){
                session.close();
            }
        }
        return currencyOptional;
    }

}
