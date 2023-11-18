package com.haomeng.wisdomputian.dao;

import com.haomeng.wisdomputian.bean.Photos;

import java.util.List;

public interface PhotosDao {

    //新建图片
    public int AddPhotos(Photos photos);

    //查找图片信息  by linkid
    public List<Photos> SearchPhotosByLinkId(Photos photos);

    //查找图片信息  by id
    public Photos SearchPhotosById(String photosid);

    //更新linkid by id
    public int UpdatePhotosById(Photos photos);
}
