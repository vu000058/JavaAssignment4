package com.algonquincollege.cst8277.models;

import static org.eclipse.persistence.config.PersistenceUnitProperties.SCHEMA_GENERATION_SQL_LOAD_SCRIPT_SOURCE;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.DefaultSessionLog;

public interface TestSuiteConstants {

    public static final String ASSIGNMENT4_PU_NAME = "assignment4-testing";
    public static final String ECLIPSELINK_LOGGING_SQL = "eclipselink.logging.sql";
    public static final String META_INF_SQL_PREFIX = "META-INF/sql/";
    public static final String META_INF_SQL_SUFFIX = ".sql";

    static EntityManagerFactory buildEntityManagerFactory(String testSuiteSql) {
        Map<String, Object> properties = new HashMap<>();
//        properties.put(SCHEMA_GENERATION_SQL_LOAD_SCRIPT_SOURCE, META_INF_SQL_PREFIX + testSuiteSql + META_INF_SQL_SUFFIX);
        AbstractSessionLog.setLog(new DefaultSessionLog());
        return Persistence.createEntityManagerFactory(ASSIGNMENT4_PU_NAME, properties);
    }
}
