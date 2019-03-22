package com.example.demo.common;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Created by zyb on 2018/9/12.
 * 对于实现自己的Interceptor而言有两个很重要的注解，一个是@Intercepts，其值是一个@Signature数组
 * 其中@Intercepts用于表明当前的对象是一个Interceptor，
 * 而@Signature则表明要拦截的接口、方法以及对应的参数类型。
 */
@Intercepts({
//        @Signature(type=Executor.class,method="query",args={MappedStatement.class,Object.class,RowBounds.class, ResultHandler.class}),
        @Signature(type=StatementHandler.class,method="prepare",args={Connection.class,Integer.class })
})
public class PageInterceptor implements Interceptor {


    private String test;
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,new DefaultReflectorFactory());
//拦截的是RoutingStatemHandler，（RoutingStatemHandler下有一个BaseStatementHandler接口）
//delegate是第一层属性的属性名，mappedStatement：第二层属性的属性名
        MappedStatement mappedStatement =(MappedStatement)metaObject.getValue("delegate.mappedStatement");
        String id=mappedStatement.getId();
        System.out.println("方法名："+id);
        //以Page结尾的做拦截。$是字符串结束符
        if(id.matches(".+Page$"))
        {
            //获取到当前StatementHandler的 boundSql，这里不管是调用handler.getBoundSql()还是直接调用delegate.getBoundSql()结果是一样的，因为之前已经说过了
            //RoutingStatementHandler实现的所有StatementHandler接口方法里面都是调用的delegate对应的方法。
            BoundSql boundSql=statementHandler.getBoundSql();
            //原始的Sql语句
            String sql=boundSql.getSql();
            System.out.println("原SQL语句："+sql);
            //查询总条数的Sql语句
            String countSql = "select count(*) from (" + sql + ")a";
            Connection connection = (Connection)invocation.getArgs()[0];
            PreparedStatement countStatement = connection.prepareStatement(countSql);
            ParameterHandler parameterHandler =(ParameterHandler)metaObject.getValue("delegate.parameterHandler");
            parameterHandler.setParameters(countStatement);
            ResultSet rs = countStatement.executeQuery();

            //拿到当前绑定Sql的参数对象，就是我们在调用对应的Mapper映射语句时所传入的参数对象
            Page page = (Page) boundSql.getParameterObject();
            if(rs.next()) {
                page.setTotalNumber(rs.getInt(1));
            }
            //改造后的Sql语句
            //获取分页Sql语句
            String pageSql = sql + " limit " + (page.getDbIndex()-1)*(page.getDbNumber()) + "," + page.getDbNumber();
            System.out.println("分页后的SQL语句："+pageSql);
            metaObject.setValue("delegate.boundSql.sql", pageSql);
        }
        //通过源码下的一个反射来调用被拦截的方法,让原本被拦截的方法继续执行
        return invocation.proceed();
    }

    //target被代理人
    /*
     * 达成协议，把具有代理权的要给普通的业务员this传进wrap实现的源码去做代理。
     *
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler){
            return Plugin.wrap(target,this);
        }
        else {
            return target;
        }
    }

        @Override
    public void setProperties(Properties proterties) {
        this.test=proterties.getProperty("test");
        System.out.println(this.test);
    }

}
