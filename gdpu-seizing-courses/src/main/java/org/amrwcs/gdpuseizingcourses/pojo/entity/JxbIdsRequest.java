package org.amrwcs.gdpuseizingcourses.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * jxb_ids请求相关参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JxbIdsRequest {
    private String njdmId;
    private String xkxnm;
    private String xkxqm;
    private String kklxdm;
    private String xkkzId;
}
