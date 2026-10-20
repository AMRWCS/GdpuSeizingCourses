package org.amrwcs.gdpuseizingcourses.pojo.dto;


import lombok.Data;
import org.amrwcs.gdpuseizingcourses.pojo.entity.Cookie;
import org.amrwcs.gdpuseizingcourses.pojo.entity.JxbIdsRequest;

import java.io.Serializable;

/**
 * 课程选中所需参数实体
 */
@Data
public class ChooseCourseDTO implements Serializable {
    private String kchId;
    private String jxbIds;
    private String qz;
    private JxbIdsRequest jxbIdsRequest;
    private Cookie cookie;
}
