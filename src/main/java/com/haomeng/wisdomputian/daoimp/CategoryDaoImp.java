package com.haomeng.wisdomputian.daoimp;

import com.haomeng.wisdomputian.bean.Category;
import com.haomeng.wisdomputian.bean.PageParam;
import com.haomeng.wisdomputian.bean.RegionCategory;
import com.haomeng.wisdomputian.dao.CategoryDao;
import com.haomeng.wisdomputian.mapper.CategoryMapper;
import com.haomeng.wisdomputian.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CategoryDaoImp implements CategoryDao {
    @Resource
    private CategoryMapper categoryMapper;

    //查找未删除分类全部信息
    @Override
    public List<Category> SearchAllCategory(PageParam pageParam) {

        List<Category> categoryList = categoryMapper.SearchAllCategory(pageParam);
        return categoryList;
    }

    //查找已删除分类全部信息
    @Override
    public List<Category> SearchAllDeletedCategory(PageParam pageParam) {
        List<Category> categoryList = categoryMapper.SearchAllDeletedCategory(pageParam);
        return categoryList;
    }

    //获取数量
    @Override
    public long ObtainSum() {
        long sumNum = categoryMapper.ObtainSum();
        return sumNum;
    }

    //查找分类信息
    @Override
    public List<Category> SearchCategory(Category category) {
        //查找分类信息
        List<Category> categoryList = categoryMapper.SearchCategory(category);
        return categoryList;
    }

    //添加分类信息
    @Override
    public int AddCategory(Category category) {
        String id = UUIDUtil.getNoLineUUID();
        category.setId(id);
        int result = categoryMapper.AddCategory(category);
        return result;
    }

    //更新分类信息
    @Override
    public int UpdateCategory(Category category) {
        int result = categoryMapper.UpdateCategory(category);
        return result;
    }

    //逻辑删除分类
    @Override
    public int LogicDeleteCategory(Category category) {
        int result = categoryMapper.LogicDeleteCategory(category);
        return result;
    }

    //还原逻辑删除分类
    @Override
    public int LogicDeleteCategoryReject(Category category) {
        int result = categoryMapper.LogicDeleteCategoryReject(category);
        return result;
    }

    //正式删除分类
    @Override
    public int RealDeleteCategory(Category category) {
        int result = categoryMapper.RealDeleteCategory(category);
        return result;
    }

    //查找单条分类数据信息
    @Override
    public Category SearchSimpleCategory(String categoryid) {
        Category category = categoryMapper.SearchSimpleCategory(categoryid);
        return category;
    }

    //获取指定区域可显示的区域分类信息
    @Override
    public List<Category> SearchRegionCategory(RegionCategory regionCategory) {
        List<Category> regionCategoryList = categoryMapper.SearchRegionCategory(regionCategory);
        return regionCategoryList;
    }

    //查找已经选中指定区域可显示的区域分类信息
    @Override
    public List<Category> SearchRegionCategoryChecked(String regionid) {
        List<Category> categoryList = categoryMapper.SearchRegionCategoryChecked(regionid);
        return categoryList;
    }

    @Override
    public List<Category> SearchCategoryUnDeleted() {
        List<Category> categoryList = categoryMapper.SearchCategoryUnDeleted();
        return categoryList;
    }


}
