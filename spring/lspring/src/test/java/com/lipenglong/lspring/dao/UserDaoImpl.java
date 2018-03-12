package com.lipenglong.lspring.dao;

import com.lipenglong.lspring.model.UserVO;
import com.lipenglong.lspring.annotation.Inject;
import com.lipenglong.lspring.annotation.Persistance;
import com.lipenglong.lspring.jdbc.JdbcTemplate;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lipl
 * Date: 12-6-30
 * Time: 下午9:19
 * To change this template use File | Settings | File Templates.
 */
@Persistance(name = "userDao")
public class UserDaoImpl implements UserDao {
    @Inject
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserDaoImpl() {
//        System.out.println("----------userDaoImpl init----------");
    }

    /**
     * 初始化user表
     * @return
     */
    public boolean initTable() {
        boolean flag = false;
        String dropTable = "drop table if exists user";
        String createTable = "create table user(id bigint, name varchar, " +
                "age int, birthday DATE, height float, identity bigint)";
        this.jdbcTemplate.beginTransaction();
        this.jdbcTemplate.execute(dropTable);
        this.jdbcTemplate.execute(createTable);
        this.jdbcTemplate.commit();
        flag = true;
        return flag;
    }

    /**
     * 向user表中插入一些测试数据
     */
    public void initData() {
        String sql = "insert into user(id, name, age, birthday, height, identity) values (?, ?, ?, ?, ?, ?)";
        try {
            this.jdbcTemplate.beginTransaction();
            PreparedStatement pstmt = this.jdbcTemplate.executeBatch(sql);
            for(int i=0; i<5; i++) {
                pstmt.setLong(1, i);
                pstmt.setString(2, "name-" + i);
                pstmt.setInt(3, 24);
                pstmt.setObject(4, new GregorianCalendar(1988, 1, 4).getTime());
                pstmt.setFloat(5, 1.70f);
                pstmt.setLong(6, 123456789l);
                pstmt.executeUpdate();
            }
            this.jdbcTemplate.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 查询user表记录
     * @return
     */
    public List<UserVO> getUserList() {
        List<UserVO> list = new ArrayList<UserVO>();
        List<Object[]> result = null;
        String sql = "select id,name,age,birthday,height,identity from user";
        result = this.jdbcTemplate.query(sql);
        for(Object[] temp : result) {
            UserVO user = new UserVO();
            user.setId((Long) temp[0]);
            user.setUserName((String) temp[1]);
            user.setAge((Integer) temp[2]);
            user.setBirthday((Date) temp[3]);
            user.setHeight(((Double) temp[4]).floatValue());
            user.setIdentity((Long) temp[5]);
            list.add(user);
        }
        return list;
    }

    public void addUser(UserVO userVO) {
        String sql = "insert into user(id,name,age,birthday,height,identity) values(?,?,?,?,?,?)";
        sql = sql.replaceFirst("[?]", "" + userVO.getId());
        sql = sql.replaceFirst("[?]", "'" + userVO.getUserName() + "'");
        sql = sql.replaceFirst("[?]", "" + userVO.getAge());
        sql = sql.replaceFirst("[?]", "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(userVO.getBirthday()) + "'");
        sql = sql.replaceFirst("[?]", String.valueOf(userVO.getHeight()));
        sql = sql.replaceFirst("[?]", String.valueOf(userVO.getIdentity()));
        this.jdbcTemplate.executeInsert(sql);
    }
}
