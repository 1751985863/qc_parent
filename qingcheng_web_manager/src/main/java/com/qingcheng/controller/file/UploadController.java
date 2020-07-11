package com.qingcheng.controller.file;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private HttpServletRequest servletRequest;

    @PostMapping("/native")
    public String nativeUpload(@RequestParam("file") MultipartFile file,String folder){
        System.out.println("native......");
        /*HttpServletRequest对象可以获取当前项目的相对位置，然后创建相应的文件夹*/
        String path=  servletRequest.getSession().getServletContext().getRealPath("img");
        String fileName=folder+"/"+ UUID.randomUUID()+ file.getOriginalFilename();
        String filePath= path +"/"+fileName;
        File desFile=new File(filePath);
        if(!desFile.getParentFile().exists() ){
            desFile.mkdirs();
        }
        try {
            file.transferTo(desFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "http://localhost:9101/img/"+fileName;
    }
    @Autowired
    private OSSClient ossClient;

    @PostMapping("/oss")
    public String ossUpload(@RequestParam("file") MultipartFile file,String folder){
        String bucketname="cquptcommunity";
        String fileName=folder+"/"+ UUID.randomUUID()+ file.getOriginalFilename();
        try {
            ossClient.putObject(bucketname,fileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "https://"+bucketname+".oss-cn-beijing.aliyuncs.com/"+fileName;


    }
}
