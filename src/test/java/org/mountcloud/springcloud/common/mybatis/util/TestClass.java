package org.mountcloud.springcloud.common.mybatis.util;

import org.mountcloud.springcloud.common.mybatis.entity.BaseExample;
import org.mountcloud.springcloud.common.mybatis.mapper.BaseMapper;
import org.mountcloud.springproject.common.entity.BaseEntity;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author zhanghaishan
 * @version V1.0
 * TODO:
 * 2020/1/17.
 */
public class TestClass<T extends BaseEntity,D extends BaseMapper> {


    public String getMapperClass(int num){
        Class cls = getClass();
        ParameterizedType type = (ParameterizedType)cls.getGenericSuperclass();
        Class tempClass = (Class)type.getActualTypeArguments()[num];
        return tempClass.getName();
    }

    public static void main(String[] args) {
        TestClassChild testClassChild = new TestClassChild();
        System.out.println(testClassChild.getMapperClass(1));
    }

    public static class TestClassChild extends TestClass<TestEntity,TestMapper>{

    }

    public static class TestEntity extends BaseEntity{

    }

    public static class TestMapper implements BaseMapper {

        @Override
        public <D extends BaseExample> long countByExample(D example) {
            return 0;
        }

        @Override
        public <D extends BaseExample> int deleteByExample(D example) {
            return 0;
        }

        @Override
        public int deleteByPrimaryKey(long id) {
            return 0;
        }

        @Override
        public <D extends BaseEntity> int insert(D record) {
            return 0;
        }

        @Override
        public <D extends BaseEntity> int insertSelective(D record) {
            return 0;
        }

        @Override
        public <D extends BaseEntity, E extends BaseExample> List<D> selectByExample(E example) {
            return null;
        }

        @Override
        public <D extends BaseEntity> D selectByPrimaryKey(Object id) {
            return null;
        }

        @Override
        public <D extends BaseEntity, E extends BaseExample> int updateByExampleSelective(D record, E example) {
            return 0;
        }

        @Override
        public <D extends BaseEntity, E extends BaseExample> int updateByExample(D record, E example) {
            return 0;
        }

        @Override
        public <D extends BaseEntity> int updateByPrimaryKeySelective(D record) {
            return 0;
        }

        @Override
        public <D extends BaseEntity> int updateByPrimaryKey(D record) {
            return 0;
        }

        @Override
        public <D extends BaseExample> List selectCustomByExample(D example) {
            return null;
        }
    }

}
