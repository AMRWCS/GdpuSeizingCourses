package org.amrwcs.gdpuseizingcourses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

// 第一版不启用数据库：排除数据源自动配置（依赖中虽有 mysql 驱动但未配置连接，不排除会启动失败）
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class GdpuSeizingCoursesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdpuSeizingCoursesApplication.class, args);
    }

}
