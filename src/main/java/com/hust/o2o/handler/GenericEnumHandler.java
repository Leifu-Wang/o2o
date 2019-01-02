package com.hust.o2o.handler;

import com.hust.o2o.utils.BaseCodeEnum;
import com.hust.o2o.utils.EnumUtils;
import com.hust.o2o.utils.Gender;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: wang
 * @Desciption: 通用枚举类型处理器，适用于整形存储
 * @Date: Created in 14:50 2019/1/2
 * @Modified By:
 **/
public class GenericEnumHandler<E extends Enum<?> & BaseCodeEnum> extends BaseTypeHandler<BaseCodeEnum> {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private Class<E> enumClass;

    public GenericEnumHandler(Class<E> enumClass){
        if (enumClass == null)
            throw new IllegalArgumentException("Type cannot be null");
        this.enumClass = enumClass;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, BaseCodeEnum baseCodeEnum,
                                    JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,baseCodeEnum.code());
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String s) throws SQLException {

        Integer key = resultSet.getInt(s);
        if (resultSet.wasNull()){
            return null;
        }else {
            return EnumUtils.codeOf(enumClass, key);
        }
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Integer key = resultSet.getInt(i);
        if (resultSet.wasNull()){
            return null;
        }else {
            return EnumUtils.codeOf(enumClass, key);
        }
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Integer key = callableStatement.getInt(i);
        if (callableStatement.wasNull()){
            return null;
        }else {
            return EnumUtils.codeOf(enumClass, key);
        }
    }
}
