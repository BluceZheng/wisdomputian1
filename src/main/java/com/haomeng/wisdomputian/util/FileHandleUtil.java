package com.haomeng.wisdomputian.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

public class FileHandleUtil {
    public static final String LANGUAGE = "zh";

    public static final String COUNTRY = "CN";

    private static String getProperties(String baseName, String section) {
        String retValue = "";
        try {
            Locale locale = new Locale(LANGUAGE, COUNTRY);
            ResourceBundle rb = ResourceBundle.getBundle(baseName, locale);
            retValue = (String) rb.getObject(section);
        } catch (Exception e) {
        }
        return retValue;
    }

    public static String getValue(String fileName, String key) {
        String value = getProperties(fileName, key);
        return value;
    }

    public static boolean deletePlainFile(String propertiesFileName, String fileName) {
        if (fileName == null) {
            return false;
        }

        String fileDir = StringUtils.defaultIfBlank(FileHandleUtil.getValue("path", "picture"), "E:/picture/");
        try {
            FileUtils.deleteQuietly(new File(fileDir + fileName));
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static boolean uploadSpringMVCFile(MultipartFile multipartFile, String fileName) throws Exception {
        String path = System.getProperty("user.dir") ;
        path=path+"/src/main/resources/static/file";
        File dest = new File(path + "/" + fileName);
        if (!new File(path).exists()) {
            new File(path).mkdirs();
        }
        multipartFile.transferTo(dest);// 保存文件

        return true;
    }

    public static File getFileByName(String path) {
        String paths = System.getProperty("user.dir") ;
        paths=paths+"/src/main/resources/static/file";
        //String fileDir = StringUtils.defaultIfBlank(FileHandleUtil.getValue("path", "picture"), "E:/picture/");
        return new File(paths+path);
    }
}
