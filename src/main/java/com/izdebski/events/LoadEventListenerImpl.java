package com.izdebski.events;

import com.izdebski.entities.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;


public class LoadEventListenerImpl implements LoadEventListener {

    private static final long serialVersionUID = 1L;
    private static Logger logger = LogManager.getLogger(LoadEventListenerImpl.class);

    @Override
    public void onLoad(LoadEvent e, LoadType type) throws HibernateException {
        logger.info("onLoad is called.");
        Object obj = e.getResult();
        if (obj instanceof Person) {
            Person person = (Person) obj;
            logger.info(person);
        }
    }

}