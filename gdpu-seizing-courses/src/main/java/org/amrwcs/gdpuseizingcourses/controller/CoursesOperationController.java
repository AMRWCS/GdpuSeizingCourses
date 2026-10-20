package org.amrwcs.gdpuseizingcourses.controller;

import lombok.extern.slf4j.Slf4j;
import org.amrwcs.gdpuseizingcourses.common.result.Result;
import org.amrwcs.gdpuseizingcourses.pojo.dto.CancelCourseDTO;
import org.amrwcs.gdpuseizingcourses.pojo.dto.ChooseCourseDTO;
import org.amrwcs.gdpuseizingcourses.pojo.vo.JxbIdsVO;
import org.amrwcs.gdpuseizingcourses.service.CoursesOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程相关接口
 */
@Slf4j
@RestController
@RequestMapping("/course")
public class CoursesOperationController {

    @Autowired
    private CoursesOperationService coursesOperationService;

    /**
     * 课程选中
     * @param chooseCourseDTO
     * @return
     */
    @PostMapping("/select")
    public Result<JxbIdsVO> chooseCourse(@RequestBody ChooseCourseDTO chooseCourseDTO) {
        log.info("接收选课请求");
        String jxbIds = coursesOperationService.chooseCourse(chooseCourseDTO);
        JxbIdsVO jxbIdsVO = JxbIdsVO.builder()
                .jxbIds(jxbIds)
                .build();
        log.info("选课请求处理成功");
        return Result.success(jxbIdsVO);
    }

    /**
     * 课程取消
     * @param cancelCourseDTO
     * @return
     */
    @PostMapping("/remove")
    public Result<JxbIdsVO> cancelChooseCourse(@RequestBody CancelCourseDTO cancelCourseDTO) {
        log.info("接收退课请求");
        String jxbIds=coursesOperationService.cancelCourse(cancelCourseDTO);
        JxbIdsVO jxbIdsVO = JxbIdsVO.builder()
                .jxbIds(jxbIds)
                .build();
        log.info("退课请求处理成功");
        return Result.success(jxbIdsVO);
    }
}
