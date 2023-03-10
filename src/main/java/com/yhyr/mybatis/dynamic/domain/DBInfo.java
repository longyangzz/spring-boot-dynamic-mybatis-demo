package com.yhyr.mybatis.dynamic.domain;

/**
 * Created by wly on 2023-03-08
 */

public class DBInfo {
    private int id;
    private String dbName;
    private String dbIp;
    private int dbPort;
    private String dbUser;
    private String dbPasswd;
    private String dbDatasourceName;
    private String dbDriver;

    public String getDbDriver() {
        return dbDriver;
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    public String getDbDatasourceName() {
        return dbDatasourceName;
    }

    public void setDbDatasourceName(String dbDatasourceName) {
        this.dbDatasourceName = dbDatasourceName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbIp() {
        return dbIp;
    }

    public void setDbIp(String dbIp) {
        this.dbIp = dbIp;
    }

    public int getDbPort() {
        return dbPort;
    }

    public void setDbPort(int dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPasswd() {
        return dbPasswd;
    }

    public void setDbPasswd(String dbPasswd) {
        this.dbPasswd = dbPasswd;
    }
}
