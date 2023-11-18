package com.haomeng.wisdomputian.controller.UtilController;

import com.alibaba.fastjson.JSONObject;
import com.haomeng.wisdomputian.bean.FileUpload;
import com.haomeng.wisdomputian.bean.Photos;
import com.haomeng.wisdomputian.bean.ReturnUploadMessage;
import com.haomeng.wisdomputian.bean.UploadData;
import com.haomeng.wisdomputian.daoimp.PhotosDaoImp;
import com.haomeng.wisdomputian.util.FileHandleUtil;
import com.haomeng.wisdomputian.util.ResizeImage;
import com.haomeng.wisdomputian.util.StringHelper;
import com.haomeng.wisdomputian.util.UUIDUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class FileUploadController {

    private ReturnUploadMessage returnUploadMessage;



    @Resource
    private PhotosDaoImp photosDaoImp;


    @GetMapping(value = "/file")
    public String file() {
        return "fileupload";
    }

    /**
     * 实现文件上传
     */
    @RequestMapping("/fileUpload")
    @ResponseBody
    public Map<String, String> fileUpload(MultipartFile file) {
        Map<String, String> plist = new HashMap<>();
        if (file.isEmpty()) {
            return plist;
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();


        String suffixName = fileName.substring(fileName.lastIndexOf("."));


        String fileuuidName = UUIDUtil.getNoLineUUID();
        fileName = fileuuidName + suffixName;

        //缩略图文件名
        String fileNameMini = fileuuidName + "Mini" + suffixName;

        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();


        path = path + "/static/file";


        File dest = new File(path + "/" + fileName);

        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }


        try {
            file.transferTo(dest); //保存文件

            //创建
            ResizeImage resizeImage = new ResizeImage();

            //制作缩略图

            //这儿填写你存放要缩小图片的文件夹全地址
            String inputFoler = path + "/" + fileName;

            //这儿填写你转化后的图片存放的文件夹
            String outputFolder = path + "/" + fileNameMini;

            String[] type = new String[]{suffixName};

            float multiple = 0.5F;

            boolean issuccess = resizeImage.creatImageMini(inputFoler, outputFolder, type, multiple);


            if (issuccess) {
                plist.put("pathMini", "static/file/" + fileNameMini);
            }

            plist.put("path", "static/file/" + fileName);

            return plist;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
            return plist;
        } catch (IOException e) {
            // TODO Auto-generated catch blockS

            e.printStackTrace();
            return plist;
        }
    }


    /**
     * 实现文章中文件上传
     */
    @RequestMapping("/articlefileupload")
    @ResponseBody
    public JSONObject ArticleFileUpload(MultipartFile file) {

        Photos photos = new Photos();

        //Map<String, String> retnMap = new HashMap<>();

        JSONObject resultJsonObject = new JSONObject();

        if (file.isEmpty()) {
            return resultJsonObject;
        }
        //获取文件名称
        String fileName = file.getOriginalFilename();
        String fileoriginalName = fileName;//转存文件原名
        String size = StringHelper.null2String(file.getSize());

        //获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //新建文件名+后缀名
        fileName = UUIDUtil.getNoLineUUID() + suffixName;

        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        //设置文件的存储路径
        path = path + "/static/file";
        File dest = new File(path + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件

            //保存数据信息到数据库中
            photos.setId(UUIDUtil.getNoLineUUID());
            photos.setPath("/static/file" + "/" + fileName);
            photos.setName(fileoriginalName);
            photos.setSize(size);
            int result = photosDaoImp.AddPhotos(photos);
            if (result >= 1) {
                resultJsonObject.put("photosid", photos.getId());
            }
            return resultJsonObject;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return resultJsonObject;
        } catch (IOException e) {
            // TODO Auto-generated catch blockS
            e.printStackTrace();
            return resultJsonObject;
        }
    }


    /**
     * 编辑器文件上传
     */
    @RequestMapping(value = "/editfileUpload", method = RequestMethod.POST)
    @ResponseBody
    public ReturnUploadMessage editfileUpload(MultipartFile file) {
        returnUploadMessage = new ReturnUploadMessage();
        if (file == null) {
            returnUploadMessage.setCode(1);
            returnUploadMessage.setMsg("文件没有上传上来");
            return returnUploadMessage;
        }

        String fileName = file.getOriginalFilename();
        String title = file.getOriginalFilename();
        String path = System.getProperty("user.dir");
        path += "/src/main/resources/static/file";

        fileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
        File dest = new File(path + "/" + fileName);

        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            returnUploadMessage.setCode(0);
            returnUploadMessage.setMsg("上传成功");
            UploadData uploadData = new UploadData();
            uploadData.setSrc("/file/" + fileName);
            uploadData.setTitle(title);
            returnUploadMessage.setData(uploadData);

        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            returnUploadMessage.setCode(1);
            returnUploadMessage.setMsg("文件没有上传上来");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            returnUploadMessage.setCode(1);
            returnUploadMessage.setMsg("文件没有上传上来");
        }

        return returnUploadMessage;
    }

    /*
     * 获取multifile.html页面
     */
    @RequestMapping("multifile")
    public String multifile() {
        return "/multifile";
    }

    /**
     * 实现多文件上传
     */
    @RequestMapping(value = "multifileUpload", method = RequestMethod.POST)
    /**public @ResponseBody String multifileUpload(@RequestParam("fileName")List<MultipartFile> files) */ public @ResponseBody Map<String, String> multifileUpload(HttpServletRequest request) {

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");
        Map<String, String> plist = new HashMap<>();
        if (files.isEmpty()) {
            System.out.println("ccc");
            return plist;
        }

        String basepath = System.getProperty("user.dir");
        String path = basepath + "/src/main/resources/static/file";
        int num = 1;
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            fileName = UUID.randomUUID() + suffixName;

            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);

            if (file.isEmpty()) {
                System.out.println("eeee");
                return plist;
            } else {
                File dest = new File(path + "/" + fileName);
                if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                    plist.put("图片" + num, path + "/" + fileName);
                    num++;
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println("ssss");
                    return plist;
                }
            }
        }
        return plist;
    }


    @RequestMapping("/uploadPictures")
    @ResponseBody
    public String uploadFile(HttpServletRequest request, @RequestPart(value = "file") MultipartFile file) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String res = sdf.format(new Date());
        //服务器上使用
        String rootPath = System.getProperty("user.dir");//target的目录
        rootPath = rootPath + "/src/main/resources/static/file";


        //本地使用
        //String rootPath ="/D:/download/beijing";
        //原始名称
        String originalFilename = file.getOriginalFilename();
        //新的文件名称
        String newFileName = res + originalFilename.substring(originalFilename.lastIndexOf("."));
        //创建年月文件夹
        Calendar date = Calendar.getInstance();
        File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1));
        //新文件
        File newFile = new File(rootPath + File.separator + dateDirs + File.separator + newFileName);

        //判断目标文件所在的目录是否存在
        if (!newFile.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            newFile.getParentFile().mkdirs();
        }

        System.out.println(newFile);
        //将内存中的数据写入磁盘
        file.transferTo(newFile);

        //完整的url
        String fileUrl = "file/" + date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + newFileName;
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        map.put("code", 0);//0表示成功，1失败
        map.put("msg", "上传成功");//提示消息
        map.put("data", map2);
        map2.put("src", fileUrl);//图片url
        map2.put("title", newFileName);//图片名称，这个会显示在输入框里
        String result = new JSONObject(map).toString();

        return result;

    }


    /**
     * Restful风格获取图片
     *
     * @param request
     * @param response
     * @param pictureId
     */
    @RequestMapping("/getPicture/{pictureId}")
    public void getPicture(HttpServletRequest request, HttpServletResponse response, @PathVariable() String pictureId) {
        FileInputStream in = null;
        ServletOutputStream outputStream = null;
        try {
            File fileByName = FileHandleUtil.getFileByName(pictureId);
            in = new FileInputStream(fileByName);
            outputStream = response.getOutputStream();
            IOUtils.copyLarge(in, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(outputStream);
        }
    }


    /**
     * 图片上传
     *
     * @param imgFile
     * @return
     */
    @RequestMapping("/uploadPicture")
    @ResponseBody
    public Map<String, Object> uploadPicture(MultipartFile imgFile) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("error", 1);
        System.out.println("xxxxxxxxxxxx");
        if (imgFile == null) {
            result.put("message", "文件没接到");
            return result;
        }

        String fileOriName = imgFile.getOriginalFilename();// 获取原名称
        String fileNowName = UUIDUtil.getNoLineUUID() + "." + FilenameUtils.getExtension(fileOriName);// 生成唯一的名字
        try {
            FileHandleUtil.uploadSpringMVCFile(imgFile, fileNowName);

        } catch (Exception e) {

            return result;
        }

        String id = UUIDUtil.getUUID();

        System.out.println("xxxxxxxxxxxx   xx  " + "static/file/" + fileNowName);
        result.put("error", 0);
        result.put("url", "file/" + fileNowName);

        return result;
    }

}
