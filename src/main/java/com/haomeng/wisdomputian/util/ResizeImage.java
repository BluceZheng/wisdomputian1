package com.haomeng.wisdomputian.util;

import com.sun.image.codec.jpeg.JPEGImageEncoder;

import com.sun.image.codec.jpeg.JPEGCodec;

import com.sun.image.codec.jpeg.JPEGEncodeParam;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import java.util.HashMap;

import java.util.List;

import java.util.ArrayList;

import java.io.File;

import java.io.IOException;

import java.io.FileOutputStream;

import java.util.Map;

public class ResizeImage {

    /**
     * @param im          原始图像
     * @param resizeTimes 需要缩小的倍数，缩小2倍为原来的1/2 ，这个数值越大，返回的图片越小
     * @return 返回处理后的图像
     */
    public BufferedImage resizeImage(BufferedImage im, float resizeTimes) {

        /*原始图像的宽度和高度*/

        int width = im.getWidth();

        int height = im.getHeight();

        /*调整后的图片的宽度和高度*/

        int toWidth = (int) (Float.parseFloat(String.valueOf(width)) / resizeTimes);

        int toHeight = (int) (Float.parseFloat(String.valueOf(height)) / resizeTimes);

        /*新生成结果图片*/

        BufferedImage result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);

        result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

        return result;

    }

    /**
     * @param im          原始图像
     * @param resizeTimes 倍数,比如0.5就是缩小一半,0.98等等double类型
     * @return 返回处理后的图像
     */
    public BufferedImage zoomImage(BufferedImage im, float resizeTimes) {

        /*原始图像的宽度和高度*/

        int width = im.getWidth();

        int height = im.getHeight();

        /*调整后的图片的宽度和高度*/

        int toWidth = (int) (Float.parseFloat(String.valueOf(width)) * resizeTimes);

        int toHeight = (int) (Float.parseFloat(String.valueOf(height)) * resizeTimes);

        /*新生成结果图片*/

        BufferedImage result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);

        result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

        return result;

    }


    /**
     * @param path 要转化的图像的文件夹,就是存放图像的文件夹路径
     * @param type 图片的后缀名组成的数组
     * @return
     */
    public List getImageList(String path, String[] type) throws IOException {

        Map map = new HashMap();

        for (String s : type) {

            map.put(s, true);

        }

        List result = new ArrayList();

        Map<String, Object> rsltMap = new HashMap<>();

        File[] fileList = new File(path).listFiles();

        for (File f : fileList) {

            if (f.length() == 0) continue;

            if (map.get(getExtension(f.getName())) == null) {
                continue;
            }
            rsltMap.put("name", f.getName().split("\\."));
            rsltMap.put("BufferedImage", javax.imageio.ImageIO.read(f));

            result.add(rsltMap);
        }

        return result;

    }

    /**
     * 把图片写到磁盘上
     *
     * @param im
     * @param path     eg: C://home// 图片写入的文件夹地址
     * @param fileName DCM1987.jpg 写入图片的名字
     * @return
     */
    public boolean writeToDisk(BufferedImage im, String path, String fileName) {

        File f = new File(path + fileName);

        String fileType = getExtension(fileName);

        if (fileType == null)

            return false;

        try {

            ImageIO.write(im, fileType, f);

            im.flush();

            return true;

        } catch (IOException e) {

            return false;

        }

    }


    public boolean writeHighQuality(BufferedImage im, String fileFullPath) {

        try {

            /*输出到文件流*/

            FileOutputStream newimage = new FileOutputStream(fileFullPath + System.currentTimeMillis() + ".jpg");

            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);

            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(im);

            /* 压缩质量 */

            jep.setQuality(1f, true);

            encoder.encode(im, jep);

            /*近JPEG编码*/

            newimage.close();

            return true;

        } catch (Exception e) {

            return false;

        }

    }

    /**
     * 返回文件的文件后缀名
     *
     * @param fileName
     * @return
     */
    public String getExtension(String fileName) {

        try {
            return fileName.split("\\.")[fileName.split("\\.").length - 1];
        } catch (Exception e) {
            return null;
        }

    }


    /**
     * 创建指定倍数的缩略图
     *
     * @param inputFoler   这儿填写你存放要缩小图片的文件夹全地址
     * @param outputFolder 这儿填写你转化后的图片存放的文件夹
     * @param type         图片的后缀名组成的数组
     * @param multiple     倍数,比如0.5就是缩小一半,0.98等等double类型
     * @return 是否成功执行
     * @throws IOException
     */
    public boolean creatImageMini(String inputFoler, String outputFolder, String[] type, float multiple) throws IOException {
        boolean result = false;
        if (multiple == 0) {
            multiple = 0.5f; /*这个参数是要转化成的倍数,如果是1就是转化成1倍*/
        }
        List<HashMap<String, Object>> imageList = getImageList(inputFoler, type);

        for (HashMap<String, Object> imageMap : imageList) {
            BufferedImage i = (BufferedImage) imageMap.get("BufferedImage");
            result = writeHighQuality(zoomImage(i, multiple), outputFolder);
        }
        return result;
    }



    public static void main(String[] args) throws Exception {

        String inputFoler = "D:\\yuanshi\\"; /*这儿填写你存放要缩小图片的文件夹全地址*/

        String outputFolder = "D:\\suoluetu\\"; /*这儿填写你转化后的图片存放的文件夹*/

        float times = 0.5f; /*这个参数是要转化成的倍数,如果是1就是转化成1倍*/

        ResizeImage r = new ResizeImage();

        List imageList = r.getImageList(inputFoler, new String[]{"jpg"});

        for (Object ii : imageList) {

            HashMap<String, Object> iMap = (HashMap<String, Object>) ii;
            BufferedImage i = (BufferedImage) iMap.get("BufferedImage");

            r.writeHighQuality(r.zoomImage(i, times), outputFolder);

        }

    }


}
