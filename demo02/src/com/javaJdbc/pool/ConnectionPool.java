package com.javaJdbc.pool;
/*
数据库(Mysql)连接池:
        Mysql是C/S的架构,以客户端请求,服务端响应的方式工作
        Mysql数据库连接是通过TCP建立的（IP 端口 用户名 密码）。
    而在java中做这些事需要消耗系统资源以及等待连接建立才能进行
    数据库的操作，如果出现多次连接则不仅对系统资源消耗极大，而
    且多次连接会使程序的执行变得很慢很卡。为了对系统性能整体优
    化，需要一套解决方案。
        连接池正是解决以上问题的一套方案。
        通过预先创建连接对象，放入存储容器中，需要时从这个容器
    中取出，用完后则放回容器，省去了多次创建连接的资源以及时间
    上的消耗，整体提高程序服务性能

以下为仿写连接池案例
 */


import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

public class ConnectionPool implements DataSource {
    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;
    private static String poolsize;
    private static String maxwait;
    static {
        // 加载驱动与配置信息
        Properties poolConfig = new Properties();
        InputStream rs = ConnectionPool.class.getClassLoader().getResourceAsStream("conectionpool.properties");
        try {
            poolConfig.load(rs);
            driverClass = poolConfig.getProperty("driverClass");
            url = poolConfig.getProperty("url");
            username = poolConfig.getProperty("username");
            password = poolConfig.getProperty("password");
            poolsize = poolConfig.getProperty("poolsize");
            maxwait = poolConfig.getProperty("maxwait");
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

/*    // 连接池容器
    private static LinkedList<Connection> conections = new LinkedList<>();
    // 装饰者模式实现
    private ConnectionPool() throws SQLException {
        // 数据库连接池应当是单例模式
        for (int i = 0; i < Integer.parseInt(poolsize); i++) {
            Connection dConn = DriverManager.getConnection(url, username, password);
            MyconnectionPool myConn = new MyconnectionPool(dConn, conections);
            conections.add(myConn);
        }
    }*/

    // 连接池容器
    private static LinkedList<Connection> conections = new LinkedList<>();
    // 动态代理实现
    private ConnectionPool() throws SQLException {
        // 数据库连接池应当是单例模式
        for (int i = 0; i < Integer.parseInt(poolsize); i++) {
            Connection conn = DriverManager.getConnection(url, username, password);
            conections.add(conn);
        }
    }

    // 返回单个实例
    private static ConnectionPool connectionPool = null;
    public static synchronized ConnectionPool getConectionPool() throws SQLException {
        return connectionPool==null ? connectionPool = new ConnectionPool(): connectionPool;
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        if (conections.size()>0){
            conn = conections.removeFirst();
            System.out.println("获取连接成功还剩余可连接数量: "+ conections.size());
        }else{
            // 处理等待连接对象 这里正常情况应该是开线程来做的
            long start = System.currentTimeMillis();
            while(true){
                long end = System.currentTimeMillis();
                if(end-start>=Integer.parseInt(maxwait)){
                    throw  new SQLException();
                }
                if (conections.size()>0){
                    conn = conections.removeFirst();
                    System.out.println("获取连接成功还剩余可连接数量: "+ conections.size());
                    break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // 返回代理对象
        return getConn(conn);
    }

    /**
     *动态代理实现方法 返回连接
     * @return
     */
    private  Connection getConn(Connection conn){
        InvocationHandler newMyPool = new NewMyPool(conn, conections);
        ((NewMyPool) newMyPool).setStatus();
        conn = (Connection) Proxy.newProxyInstance(NewMyPool.class.getClassLoader(), new Class[]{Connection.class}, newMyPool);
        return conn;
    }


    public static int getPoolsize(){
        return conections.size();
    }

    @Override
    public Connection getConnection(String username, String password) {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) {

    }

    @Override
    public void setLoginTimeout(int seconds) {

    }

    @Override
    public int getLoginTimeout() {
        return 0;
    }

    @Override
    public Logger getParentLogger() {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) {
        return false;
    }
}
