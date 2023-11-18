package com.haomeng.wisdomputian.dao;

import com.haomeng.wisdomputian.bean.Category;
import com.haomeng.wisdomputian.bean.PageParam;
import com.haomeng.wisdomputian.bean.RegionCategory;

import java.util.List;

public interface CategoryDao {

    //查找未删除分类全部信息
    public List<Category> SearchAllCategory(PageParam pageParam);

    //查找已删除分类全部信息
    public List<Category> SearchAllDeletedCategory(PageParam pageParam);

    //获取数量
    public long ObtainSum();

    //查找分类信息
    public List<Category> SearchCategory(Category category);

    //添加分类信息
    public int AddCategory(Category category);

    //更新分类信息
    public int UpdateCategory(Category category);

    //逻辑删除分类
    public int LogicDeleteCategory(Category category);

    //还原逻辑删除分类
    public int LogicDeleteCategoryReject(Category category);

    //正式删除分类
    public int RealDeleteCategory(Category category);

    //查找单条分类数据信息
    public Category SearchSimpleCategory(String categoryid);

    //获取指定区域可显示的区域分类信息
    public List<Category> SearchRegionCategory(RegionCategory regionCategory);

    //查找已经选中指定区域可显示的区域分类信息
    public List<Category> SearchRegionCategoryChecked(String regionid);

    //查找所有未删除的分类
    public List<Category> SearchCategoryUnDeleted();
}
