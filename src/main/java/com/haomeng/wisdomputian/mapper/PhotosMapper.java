package com.haomeng.wisdomputian.mapper;

import com.haomeng.wisdomputian.bean.Photos;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotosMapper {

    //新建图片
    public int AddPhotos(Photos photos);

    //查找图片信息  by linkid
    public List<Photos> SearchPhotosByLinkId(Photos photos);

    //查找图片信息  by id
    public Photos SearchPhotosById(String photosid);

    //更新linkid by id
    public int UpdatePhotosById(Photos photos);


}
