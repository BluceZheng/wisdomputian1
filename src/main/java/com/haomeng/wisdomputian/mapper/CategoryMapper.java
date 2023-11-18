package com.haomeng.wisdomputian.mapper;

import com.haomeng.wisdomputian.bean.Category;
import com.haomeng.wisdomputian.bean.PageParam;
import com.haomeng.wisdomputian.bean.Region;
import com.haomeng.wisdomputian.bean.RegionCategory;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CategoryMapper {

    //获取分类所有的信息
    public List<Category> SearchAllCategory(PageParam pageParam);

    //获取已删除分类信息
    public List<Category> SearchAllDeletedCategory(PageParam pageParam);

    //获取分类数量
    public long ObtainSum();

    //查找分类信息
    public List<Category> SearchCategory(Category category);

    //添加分类信息
    public int AddCategory(Category category);

    //修改分类信息
    public int UpdateCategory(Category category);

    //逻辑删除分类信息
    public int LogicDeleteCategory(Category category);

    //还原逻辑删除分类信息
    public int LogicDeleteCategoryReject(Category category);

    //正式删除分类信息
    public int RealDeleteCategory(Category category);

    //获取分类信息byid
    public Category SearchSimpleCategory(String categoryid);

    //获取指定区域可显示的区域分类信息
    public List<Category> SearchRegionCategory(RegionCategory regionCategory);

    //查找已经选中指定区域可显示的区域分类信息
    public List<Category> SearchRegionCategoryChecked(String regionid);

    //查找所有未删除的分类
    public List<Category> SearchCategoryUnDeleted();
}
