package com.example.teachingplatform.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DataSourceUtil {

    private final SqlSessionFactory sqlSessionFactory;
    private static DataSourceUtil dataSourceUtil;

    private DataSourceUtil(){
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSession getSqlSession() {
        if (dataSourceUtil == null){
            dataSourceUtil = new DataSourceUtil();
        }
        return dataSourceUtil.sqlSessionFactory.openSession();
    }



}
