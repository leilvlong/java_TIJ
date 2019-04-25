package com.javaJdbc.druid.templatejar;

import com.javaJdbc.druid.JdbcTest;
import com.javaJdbc.druid.JdbcUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class JdbcAffair {

    private static JdbcTemplate jt;
    private static DataSourceTransactionManager dtm =  new DataSourceTransactionManager(JdbcUtil.getDataSource());
    private static TransactionStatus status;

    public static void main(String[] args) {
        fun();
    }

    public static void fun(){
        jt = new JdbcTemplate(JdbcUtil.getDataSource());
        String sql1 = "update studen set age=23 where id=?";
        String sql2 = "update studen set age=53 where id=?";

        try{
            startAffair();
            jt.update(sql1,1);
            int a = 15/0;
            jt.update(sql2,2);

        }catch (Exception e){
            dtm.rollback(status);
        }

    }


    // 开启事务方法 并获得事务对象
    public static void startAffair(){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        status = dtm.getTransaction(def);

    }
}
