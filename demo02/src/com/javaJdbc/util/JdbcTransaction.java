package com.javaJdbc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

/*
JDBC 事务操作简单演示
表数据结构:
CREATE TABLE `transaction`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monery` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of transaction
-- ----------------------------
INSERT INTO `transaction` VALUES (1, '张三', 1000);
INSERT INTO `transaction` VALUES (2, '李四', 1000);

SET FOREIGN_KEY_CHECKS = 1;

需要注意的是回滚要设置在事务开启之后sql语句执行之前
 */
public class JdbcTransaction {
    public static void main(String[] args){
        Connection conn = JdbcUtil.getConn();
        String sql1 = "update transaction set monery=monery-? where name=?";
        String sql2 = "update transaction set monery=monery+? where name=?";
        Savepoint transfer = null;
        //开始事务
        try {
            conn.setAutoCommit(false);
            transfer = conn.setSavepoint("transfer");

            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setInt(1, 500);
            ps1.setString(2, "张三");
            ps1.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, 500);
            ps2.setString(2, "李四");
            ps2.executeUpdate();
            int x = 10 / 0;

            conn.commit();
        }catch (Exception e){
            // 若捕获异常 为安全起见 对事务回滚
            e.printStackTrace();
            try {
                if(transfer != null){
                    conn.rollback(transfer);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
