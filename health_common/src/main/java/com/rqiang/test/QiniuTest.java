package com.rqiang.test;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QiniuTest {
    @Test
    public void test() throws QiniuException {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "QfzitGzH0Yzt9slrv6UhYNIU1d-qhD3djJMXFZwT";
        String secretKey = "_H4HboRqGHtARqREz1QnIeQbimA2kEk7GtY4zAcz";
        String bucket = "rqiang-health";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath1 = "C:\\Users\\Ncsf7101\\Desktop\\图片2.png";
        String localFilePath = "C:\\Users\\Ncsf7101\\Desktop\\831fdb3d70cf3bc74cc714cddd00baa1cc112ae7.jpg";

//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "a";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }
    @Test
    public void deleteTest(){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        //...生成上传凭证，然后准备上传
        String accessKey = "QfzitGzH0Yzt9slrv6UhYNIU1d-qhD3djJMXFZwT";
        String secretKey = "_H4HboRqGHtARqREz1QnIeQbimA2kEk7GtY4zAcz";
        String bucket = "rqiang-health";
        Auth auth = Auth.create(accessKey, secretKey);
        String key = "Fh0b4PYFAJIaKFxuW3IwD8A2cCVc";
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

    @Test
    public void test1() throws ParseException {
        String str = "1997-12-14";
//        String str = "2019-3-4 18:07:20";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ;//Mon Mar 04 18:07:20 CST 2019
        System.out.println(sdf.parse(str));
//        System.out.println(new Date(str));

    }
}
