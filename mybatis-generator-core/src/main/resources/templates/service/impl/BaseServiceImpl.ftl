package ${package}.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import ${package}.dao.BaseDao;
import ${package}.filter.BaseFilter;
import ${package}.service.BaseService;
import com.zheng.generator.domain.mybatis.MyPageBounds;
import com.zheng.generator.domain.mybatis.MyPageList;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * 共用服务实现类
 * Created by ${author} on ${createTime?string["HH:mm yyyy-MM-dd"]}..
 *
 * @param <T> 具体实例
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    /**
     * 分页一页显示条数
     */
    public static final Integer MAX_SIZE = 20;
    
    @Override
    public int insert(T record){
        if (!Optional.ofNullable(record).isPresent()) {
            return 0;
        }
        return getBaseDao().insert(record);
    }

    @Override
    public int deleteById(Integer id){
        if (!Optional.ofNullable(id).isPresent() || id < 0) {
            return 0;
        }
        return getBaseDao().deleteById(id);
    }

    @Override
    public int update(T record){
        if (!Optional.ofNullable(record).isPresent()) {
            return 0;
        }
        return getBaseDao().update(record);
    }

    @Override
    public T selectById(Integer id){
        if (!Optional.ofNullable(id).isPresent() ||
                id < 0) {
            return null;
        }
        
        return getBaseDao().selectById(id);
    }

    @Override
    public List<T> findAll() {
        return getBaseDao().findAll();
    }

    @Override
    public MyPageList<T> listPage(MyPageBounds myPageBounds) {
        if (!Optional.ofNullable(myPageBounds).isPresent()) {
            myPageBounds = new MyPageBounds(MyPageBounds.PAGINATOR_DEFAULT_PAGE, MyPageBounds.PAGINATOR_DEFAULT_LIMIT);
        }
        PageBounds pageBounds = myPageBounds.getPageBounds();
        PageList<T> pageList = getBaseDao().listPage(pageBounds);
        if (CollectionUtils.isEmpty(pageList)) {
            return new MyPageList<>(pageList);
        }
        pageList.stream()
            .filter(item -> Optional.ofNullable(item).isPresent())
            .forEach(item-> fillData(item));

        return new MyPageList<>(pageList);
    }

    @Override
    public MyPageList<T> listPageByFilter(BaseFilter filter, MyPageBounds myPageBounds) {
        if (!Optional.ofNullable(filter).isPresent()) {
            return listPage(myPageBounds);
        }
        if (!Optional.ofNullable(myPageBounds).isPresent()) {
            myPageBounds = new MyPageBounds(MyPageBounds.PAGINATOR_DEFAULT_PAGE, MyPageBounds.PAGINATOR_DEFAULT_LIMIT);
        }

        PageBounds pageBounds = myPageBounds.getPageBounds();
        PageList<T> pageList = getBaseDao().listPageByFilter(filter, pageBounds);

        if (CollectionUtils.isEmpty(pageList)) {
            return new MyPageList<>(pageList);
        }
        pageList.stream()
            .filter(item -> Optional.ofNullable(item).isPresent())
            .forEach(item-> fillData(item));

        return new MyPageList<>(pageList);
    }

    /**
    * 根据条件查询，无分页
    * @param filter
    * @return
    */
    @Override
    public List<T> listByFilter(BaseFilter filter) {
        if (!Optional.ofNullable(filter).isPresent()) {
            return findAll();
        }
        List<T> list = getBaseDao().listByFilter(filter);
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        list.stream()
            .filter(item -> Optional.ofNullable(item).isPresent())
            .forEach(item -> fillData(item));
        return list;
    }
    /**
    * 填充实体中的数据
    * @param t
    */
    protected abstract void fillData(T t);

    /**
     * Gets base dao.
     *
     * @return the base dao
     */
    protected abstract BaseDao<T> getBaseDao();
}