package org.mountcloud.springcloud.common.mybatis.service;

import org.mountcloud.springcloud.common.mybatis.dao.BaseDao;
import org.mountcloud.springcloud.common.mybatis.entity.BaseExample;
import org.mountcloud.springcloud.mvc.common.service.BaseService;
import org.mountcloud.springproject.common.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 与数据库相关的服务父类，包含很多基础操作
 * @author zhanghaishan
 * @version V1.0
 * date 2017年8月22日 下午5:00:43
 */
public abstract class BaseMybatisService<T extends BaseEntity, D extends BaseDao<T, ?>> implements BaseService<T> {
	@Autowired
	public D generalDao;

	/**
	 * 通用删除方法，根据id
	 * @param id ID
	 * @return 删除结果
	 */
	public boolean delete(long id) {
		return generalDao.delete(id) > 0;
	}

	/**
	 * 通过bean删除
	 * @param bean bean
	 * @return 结果
	 */
	@Override
	public boolean delete(T bean){
		return delete(bean.getId());
	}

	/**
	 * 查询总数
	 *
	 * @param bean 条件辅助类
	 * @return 总数
	 */
	public Long listCount(T bean) {
		return generalDao.listCount(getExample(bean));
	}

	/**
	 * 查找用户，根据bean
	 *
	 * @param bean 条件辅助类
	 * @return AdminUser集合
	 */
	@Override
	public List<T> findList(T bean) {
		return generalDao.list(getExample(bean));
	}

	/**
	 * 查找用户，根据bean
	 * @param bean 条件辅助类
	 * @return AdminUser集合
	 */
	@Override
	public T findOne(T bean) {
		List<T> list = generalDao.list(getExample(bean));
		T obj = null;
		if(list!=null&&list.size()>0){
			obj = list.get(0);
		}
		return obj;
	}

	/**
	 * 根据主键查询
	 *
	 * @param id ID
	 * @return 查询结果
	 */
	public T listById(Object id) {
		return generalDao.listById(id);
	}

	/**
	 * 保存实体bean
	 *
	 * @param bean 实体bean
	 * @return 保存结果
	 */
	@Override
	public T save(T bean) {
		Date now = new Date();
		if(bean.getCreateTime()==null){
			bean.setCreateTime(now);
		}
		bean.setUpdateTime(now);
		int i=generalDao.save(bean);
		if(i<1){
			return null;
		}
		return bean;
	}

	/**
	 * 保存实体bean
	 *
	 * @param bean 实体bean
	 * @return 保存结果
	 */
	public T saveSelective(T bean) {
		Date now = new Date();
		if(bean.getCreateTime()==null){
			bean.setCreateTime(now);
		}
		bean.setUpdateTime(now);
		if(!generalDao.saveSelective(bean)){
			return null;
		}
		return bean;
	}

	/**
	 * 更新实体类，根据bean
	 * @param bean 实体bean
	 * @return 更新结果
	 */
	@Override
	public T update(T bean) {
		bean.setUpdateTime(new Date());
		int i = generalDao.update(bean);
		if(i<1){
			return null;
		}
		return bean;
	}

	/**
	 * 更新实体类，根据bean
	 *
	 * @param bean 实体bean
	 * @return 更新结果
	 */
	public T updateSelective(T bean) {
		bean.setUpdateTime(new Date());
		int i = generalDao.updateSelective(bean);
		if(i<1){
			return null;
		}
		return bean;
	}

	/**
	 * 提供dao
	 * @return dao
	 */
	public D getGeneralDao() {
		return generalDao;
	}

	public void setGeneralDao(D generalDao) {
		this.generalDao = generalDao;
	}

	/**
	 * 自定义查询条件查询
	 * @param example 查询条件
	 * @param <D> 实体类型
	 * @return 查询结果
	 */
	public <D extends BaseExample> List listCustom(D example){
		return this.generalDao.listCustom(example);
	}

	/**
	 * 获取example的虚拟类
	 * @param bean 实体
	 * @param <E> 实体类型
	 * @return 查询条件
	 */
	public abstract <E extends BaseExample> E getExample(T bean);

	/**
	 * 操作数据库是否成功
	 * @param baseEntity 实体
	 * @return 结果
	 */
	public <K extends BaseEntity> boolean isOperatorSuccess(K baseEntity){
		if(baseEntity==null||baseEntity.getId()==null){
			return false;
		}
		return true;
	}

}
