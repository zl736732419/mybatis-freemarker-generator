package com.zheng.generator.utils;

import com.zheng.generator.builders.MyExceptionBuilder;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.List;

/**
 * java文件编译
 * @Author zhenglian
 * @Date 17:11 2018/6/4
 */
@Component
public class JavaCompileUtil {
    public static String OUT_PATH_PREFIX = "mybatis-generator-example/target/classes/";
    private JavaCompiler compiler;

    public JavaCompileUtil() {
        compiler = ToolProvider.getSystemJavaCompiler();
    }


    /**
     * 编译java文件
     * @param ops 编译参数
     * @param targetFile
     * @param file 编译文件
     */
    public void javac(List<String> ops, File targetFile, String file){
        StandardJavaFileManager manager = null;
        try{
            manager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> it = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, ops, null, it);
            task.call();
        }catch(Exception e){
            e.printStackTrace();
            throw MyExceptionBuilder.build(e.getLocalizedMessage());
        }finally{
            if(manager!=null){
                try {
                    manager.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw MyExceptionBuilder.build(e.getLocalizedMessage());
                }
            }
        }

        // class文件是生成在当前java文件所在目录的，所以这里需要将其移动到target目录
        String classFilePath = file.substring(0, file.lastIndexOf(".")) + ".class";
        try {
            if(targetFile.exists()) {
                targetFile.delete();
            }
            FileUtils.moveFile(new File(classFilePath), targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw MyExceptionBuilder.build(e.getLocalizedMessage());
        }

    }
}
