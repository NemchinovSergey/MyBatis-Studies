package com.nsergey;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;


/**
 * Инициализация и враппер для фабрики {@link org.apache.ibatis.session.SqlSessionFactory}.
 *
 * @author Generated by script "Plugin Module Generator".
 */
public class SessionFactory {
    private static SessionFactory instance;
    private static SqlSessionFactory sessionFactory;

    private SessionFactory() {
        try {
            Reader reader = Resources.getResourceAsReader(SessionFactory.class.getClassLoader(), "mybatis-config.xml");
            assert reader != null;

            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            throw new RuntimeException("Error while MyBatis initialization.", e);
        }
    }

    /**
     * @return {@link org.apache.ibatis.session.SqlSessionFactory}
     */
    public static synchronized SessionFactory getInstance() {
        if (instance == null) {
            instance = new SessionFactory();
        }

        return instance;
    }

    /**
     * Открыть новую сессию.
     *
     * @return {@link org.apache.ibatis.session.SqlSession}
     */
    public SqlSession openSession() {
        return sessionFactory.openSession();
    }
}