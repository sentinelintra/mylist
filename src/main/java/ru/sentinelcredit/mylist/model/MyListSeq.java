package ru.sentinelcredit.mylist.model;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyListSeq implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        Connection connection = sharedSessionContractImplementor.connection();

        try {
            //PreparedStatement ps = connection.prepareStatement("select s_sequence_pkg.get_next_sbllike_id from dual");
            PreparedStatement ps = connection.prepareStatement("select s_sequence_pkg.get_next_rowid from dual");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //return rs.getString("get_next_sbllike_id");
                String s = rs.getString("get_next_rowid");
                ps.close();
                ps.close();
                return s;
            }
            ps.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
