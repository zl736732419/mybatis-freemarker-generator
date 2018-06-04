package com.zheng.generator.utils;

import com.zheng.generator.builders.MyExceptionBuilder;
import org.springframework.stereotype.Component;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.util.List;

/**
 * java文件编译
 * @Author zhenglian
 * @Date 17:11 2018/6/4
 */
@Component
public class JavaCompileUtil {
    private JavaCompiler compiler;

    public JavaCompileUtil() {
        compiler = ToolProvider.getSystemJavaCompiler();
    }


    /**
     * 编译java文件
     * @param ops 编译参数
     * @param files 编译文件
     */
    public void javac(List<String> ops, String... files){
        StandardJavaFileManager manager = null;
        try{
            manager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> it = manager.getJavaFileObjects(files);
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
    }
}
