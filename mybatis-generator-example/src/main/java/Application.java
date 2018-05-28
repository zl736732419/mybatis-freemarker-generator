import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 代码生成器启动类
 * @Author zhenglian
 * @Date 18:18 2018/5/28
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        runWithBuilder(args);
    }

    public static void runWithBuilder(String[] args) {
        new SpringApplicationBuilder()
                .sources(Application.class)
                .run(args);
    }
}
