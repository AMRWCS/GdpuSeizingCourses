package org.amrwcs.gdpuseizingcourses.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 教务系统登录密钥
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cookie implements Serializable {
    private String route;
    private String JSESSIONID;
}
