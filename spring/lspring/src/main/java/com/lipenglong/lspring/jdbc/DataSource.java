package com.lipenglong.lspring.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: lipl
 * Date: 12-8-6
 * Time: 下午 3:37
 * To change this template use File | Settings | File Templates.
 */
public interface DataSource {

    void beginTransaction();

    void commit();

    void rollback();

    void close(ResultSet rs, Statement stmt, PreparedStatement pstmt, Connection conn);
}
