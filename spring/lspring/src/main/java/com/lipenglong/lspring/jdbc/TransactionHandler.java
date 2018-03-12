package com.lipenglong.lspring.jdbc;

import org.apache.log4j.Logger;

/**
 * 数据库事务handler类
 * User: lipl
 * Date: 12-7-3
 * Time: 下午5:28
 * To change this template use File | Settings | File Templates.
 */
public class TransactionHandler {
    private DataSource dataSource;

    private static Logger logger = Logger.getLogger(TransactionHandler.class);

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void beginTransaction() {
        logger.info("open database connection, begin transaction");
        this.dataSource.beginTransaction();
    }

    public void commit() {
        logger.info("commit transaction, close database connection");
        this.dataSource.commit();
    }

    public void rollback() {
        logger.info("there is some error, transaction rollback!!!!");
        this.dataSource.rollback();
    }
}
