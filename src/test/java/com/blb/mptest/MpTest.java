package com.blb.mptest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blb.MpApplication;
import com.blb.mapper.UserMapper;
import com.blb.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//springbootjunit
@RunWith(SpringRunner.class)// spring容器
@SpringBootTest(classes = com.blb.MpApplication.class) //读取
public class MpTest{

    @Autowired
    private UserMapper userMapper;


    @Test
    public void  testfind(){

        List<User> users = this.userMapper.selectList(null);
        for(User u:users){

            System.out.println(u);
        }
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setAge(22);
        user.setMail("adfad@qq.com");
        user.setName("tom1");
        user.setUserName("tom2");
        user.setPassword("111");
        user.setAddress("wuhan");

        int i = this.userMapper.insert(user);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdate(){

        User user = new User();
        user.setId(6L);
        user.setName("mike");
        int i = this.userMapper.updateById(user);
        //update tb_user set name='mike' where id=6
        System.out.println(i);
    }

    @Test
    public void testUpdate2(){

        User user = new User();
        user.setName("jetty");

        //where
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",3L);
        //update tb_user set name='jetty' where id=3
        int i = this.userMapper.update(user, queryWrapper);
        System.out.println(i);

    }

    //更新
    @Test
    public void testUpdate3(){
        UpdateWrapper<User> objectUpdateWrapper = new UpdateWrapper<>();
        objectUpdateWrapper.eq("id",2L).set("user_name","tom");

        int i = this.userMapper.update(null, objectUpdateWrapper);
        System.out.println(i);

    }
    //删除
    @Test
    public void testDelete(){

        int i = this.userMapper.deleteById(1L);
        System.out.println(i);

    }


    //删除
    @Test
    public void testDelete2(){

       Map<String,Object> o= new HashMap<>();
       o.put("name","李四");
       o.put("age",20);
       //delete from tb_user where name='tt' and age=11
        int i = this.userMapper.deleteByMap(o);
        System.out.println(i);
    }

    @Test
    public void testDelete3(){
        User user = new User();
        user.setName("jetty");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        int delete = this.userMapper.delete(queryWrapper);
        System.out.println(delete);

    }


    @Test
    public void testDelete4(){
         //delete from tb_user where id in (4l,7l)
        this.userMapper.deleteBatchIds(Arrays.asList(4L,7L));
    }

    @Test
    public void testSelect1(){

        User user = this.userMapper.selectById(5L);
        System.out.println(user);

    }
    @Test
    public void testSelect2(){
        List<User> users = this.userMapper.selectBatchIds(Arrays.asList(5L, 6L));
        for(User u:users){

            System.out.println(u);
        }


    }

    @Test
    public void testSelect3(){

        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("name","mike");
        User user = this.userMapper.selectOne(objectQueryWrapper);
        System.out.println(user);
    }

    @Test
    public void testSelect4(){

        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.ge("age",10);//>=
        Integer i = this.userMapper.selectCount(objectQueryWrapper);
        //
        System.out.println(i);
    }


    @Test
   public void testList(){
       QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
       objectQueryWrapper.eq("name","mike");

       List<User> users = this.userMapper.selectList(objectQueryWrapper);
       for(User u:users){

           System.out.println(u);
       }


   }

   @Test
   public void testPage() {
        //select * from tb_user where limit 0,5
       //select count(*) from tb_user
       QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
       objectQueryWrapper.ge("age",10);

       Page<User> objectPage = new Page<>(1, 2);//  1 ，每页显示二条
       IPage<User> userIPage = this.userMapper.selectPage(objectPage, objectQueryWrapper);

       System.out.println(userIPage.getTotal());//总条数
       System.out.println(userIPage.getPages());//总页数
       List<User> records = userIPage.getRecords();//查询的数据
       for(User u:records){

           System.out.println(u);
       }


   }


   @Test
   public void testfindById(){
       User user = this.userMapper.findByid(3L);
       System.out.println(user);

   }

   @Test
   public void testEq(){
       QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();

       HashMap<String, Object> objectObjectHashMap = new HashMap<>();
       objectObjectHashMap.put("name","孙七");
       objectObjectHashMap.put("age",24);
       objectObjectHashMap.put("password",null);

       objectQueryWrapper.allEq(objectObjectHashMap,false);
       List<User> users = this.userMapper.selectList(objectQueryWrapper);
       for(User u:users){

           System.out.println(u);
       }

   }

   @Test
   public void testIN(){

       QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
       objectQueryWrapper.eq("name","孙七").or().in("age",18L,24L);
       List<User> users = this.userMapper.selectList(objectQueryWrapper);
       for(User u:users){

           System.out.println(u);
       }
   }

   @Test
   public void testLike(){
       QueryWrapper<User> queryWrapper = new QueryWrapper<>();
       queryWrapper.like("name","张");
       queryWrapper.orderByAsc("age");
       queryWrapper.select("id","age");
       List<User> users = this.userMapper.selectList(queryWrapper);
       for(User u:users){

           System.out.println(u);
       }


   }
}