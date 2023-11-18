package com.haomeng.wisdomputian.daoimp;

import com.haomeng.wisdomputian.bean.Photos;
import com.haomeng.wisdomputian.dao.PhotosDao;
import com.haomeng.wisdomputian.mapper.PhotosMapper;
import com.haomeng.wisdomputian.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PhotosDaoImp implements PhotosDao {

    @Resource
    private PhotosMapper photosMapper;

    //新建图片
    @Override
    public int AddPhotos(Photos photos) {
        String id = photos.getId();
        if ("".equals(id) || id == null || id == "") {
            id = UUIDUtil.getNoLineUUID();
            photos.setId(id);
        }
        int result = photosMapper.AddPhotos(photos);
        return result;
    }

    //查找图片信息  by linkid
    @Override
    public List<Photos> SearchPhotosByLinkId(Photos photos) {
        List<Photos> photosList = photosMapper.SearchPhotosByLinkId(photos);
        return photosList;
    }

    //查找图片信息  by id
    @Override
    public Photos SearchPhotosById(String photosid) {
        Photos photos = photosMapper.SearchPhotosById(photosid);
        return photos;
    }

    //更新linkid by id
    @Override
    public int UpdatePhotosById(Photos photos) {
        int result = photosMapper.UpdatePhotosById(photos);
        return result;
    }
}
