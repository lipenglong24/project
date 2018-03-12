package com.lipenglong.lspring.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * jdbc操作类
 * User: lipl
 * Date: 12-7-3
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
public class JdbcTemplate implements DataSource{
    private Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    public JdbcTemplate() {

    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void beginTransaction() {
        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
            conn.commit();
            close(rs, stmt, pstmt, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback() {
        try {
            conn.rollback();
            close(rs, stmt, pstmt, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(ResultSet rs, Statement stmt, PreparedStatement pstmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
                stmt = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (pstmt != null) {
            try {
                pstmt.close();
                pstmt = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Object[]> query(String sql) {
        List<Object[]> list = new ArrayList<Object[]>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int colums = rsmd.getColumnCount();
            while (rs.next()) {
                Object[] result = new Object[colums];
                for (int i=1; i<=colums; i++) {
                    result[i-1] = rs.getObject(i);
                }
                list.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Integer executeInsert(String sql) {
        Integer result = null;
        try{
            stmt = conn.createStatement();
            result = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean execute(String sql) {
        boolean result = false;
        try{
            stmt = conn.createStatement();
            result = stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public PreparedStatement executeBatch(String sql) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }
}
