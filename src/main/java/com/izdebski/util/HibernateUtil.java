package com.izdebski.util;

import com.izdebski.events.EventListenerIntegrator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;

    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);
    static{
        if (sessionFactory == null) {
            try {
                // Create StandardServiceRegistry
                BootstrapServiceRegistry bootstrapRegistry =
                        new BootstrapServiceRegistryBuilder()
                                .applyIntegrator(new EventListenerIntegrator())
                                .build();
                standardServiceRegistry = new StandardServiceRegistryBuilder(bootstrapRegistry)
                        .configure()
                        .build();
                // Create MetadataSources
                MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
                // Create Metadata
                Metadata metadata = metadataSources.getMetadataBuilder().build();
                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();
                logger.info("SessionFactory created.");
            } catch (Exception e) {
                logger.error("SessionFactory creation failed:"+e);
                if (standardServiceRegistry != null) {
                    StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
                }
            }
        }
    }
    //Utility method to return SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}