package com.osouza.teste.utils;

import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.springframework.stereotype.Component;

public class HibernateEntityListener implements PreUpdateEventListener {

    @Override
    public boolean onPreUpdate(PreUpdateEvent preUpdateEvent) {

        Object id = preUpdateEvent.getId();
        if(id == null) {
            throw new RuntimeException("Necessário passar o id da entidade");
        }

        return false;

    }

}
