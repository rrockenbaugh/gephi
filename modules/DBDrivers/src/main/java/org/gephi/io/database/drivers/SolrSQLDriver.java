package org.gephi.io.database.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = SQLDriver.class, position = 2)
public class SolrSQLDriver implements SQLDriver {


    public SolrSQLDriver() {
        try {
            Class.forName("org.apache.solr.client.solrj.io.sql.DriverImpl");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(org.apache.solr.client.solrj.io.sql.DriverImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Connection getConnection(String connectionUrl, String username, String passwd) throws SQLException {
        System.out.println("connectionUrl=" + connectionUrl);
    	return DriverManager.getConnection(connectionUrl, username, passwd);
    }

    @Override
    public String getPrefix() {
        return "solr";
    }

    @Override
    public String toString() {
        return "SOLR";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SolrSQLDriver) {
            return ((SolrSQLDriver) obj).getPrefix().equals(getPrefix());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrefix().hashCode();
    }

}
