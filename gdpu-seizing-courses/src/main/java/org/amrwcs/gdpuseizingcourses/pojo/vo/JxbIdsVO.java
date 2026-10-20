package org.amrwcs.gdpuseizingcourses.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回jxbIds
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JxbIdsVO implements Serializable {
    private String jxbIds;
}
