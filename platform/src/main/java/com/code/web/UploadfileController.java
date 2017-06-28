package com.code.web;

import com.code.service.UploadfileService;
import com.code.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-28
 * Time: 下午3:38
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/uploadfile")
public class UploadfileController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UploadfileService uploadfileService;

    @RequestMapping(value="uploadfile")
    public String index() {
        return "uploadfile";
    }


    /*
     * 通过流的方式上传文件
     * @RequestParam("file") 将name=file控件得到的文件封装成CommonsMultipartFile 对象
     */
    @RequestMapping(value = "/uploadStream", method = RequestMethod.POST)
    public String  fileUpload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request, ModelMap model) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("upload");
        FileUtil.mkdir(path, false);
        //用来检测程序运行时间
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());
        String newFileName = new Date().getTime()+file.getOriginalFilename();
        try {
            //获取输出流
            OutputStream os=new FileOutputStream(path+"/"+newFileName);
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is=file.getInputStream();
            int temp;
            //一个一个字节的读取并写入
            while((temp=is.read())!=(-1)){
                os.write(temp);
            }
            os.flush();
            os.close();
            is.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法一的运行时间："+String.valueOf(endTime-startTime)+"ms");
        model.addAttribute("fileUrl", request.getContextPath() + "/upload/" + newFileName);
        model.addAttribute("fileName", file.getOriginalFilename());
        return "/uploadResult";
    }

    /*
     * 采用file.Transto 来保存上传的文件
     */
    @RequestMapping("transferTo")
    public String  transferTo(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request, ModelMap model) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("upload");
        FileUtil.mkdir(path, false);
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());
        String newFileName = new Date().getTime()+file.getOriginalFilename();
        File newFile=new File(path+"/"+newFileName);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        long  endTime=System.currentTimeMillis();
        System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
        model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+newFileName);
        model.addAttribute("fileName", file.getOriginalFilename());
        return "/uploadResult";
    }

    /*
     *采用spring提供的上传文件的方法
     */
    @RequestMapping("springUpload")
    public String  springUpload(HttpServletRequest request, ModelMap model) throws IllegalStateException, IOException{
        String path = request.getSession().getServletContext().getRealPath("upload");
        FileUtil.mkdir(path, false);
        long  startTime=System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)){
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();
            while(iter.hasNext()){
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null){
                    String newFileName = new Date().getTime()+file.getOriginalFilename();
                    System.out.println("fileName："+file.getOriginalFilename());
                    //上传
                    file.transferTo(new File(path+"/"+newFileName));
                }
            }
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
        model.addAttribute("fileUrl", "#");
        model.addAttribute("fileName", "多文件");
        return "/uploadResult";
    }



}
