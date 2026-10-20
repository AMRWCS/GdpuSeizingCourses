package org.amrwcs.gdpuseizingcourses.service;

import org.amrwcs.gdpuseizingcourses.pojo.dto.CancelCourseDTO;
import org.amrwcs.gdpuseizingcourses.pojo.dto.ChooseCourseDTO;

public interface CoursesOperationService {

    /**
     * 课程选中
     * @param chooseCourseDTO
     * @return
     */
    String chooseCourse(ChooseCourseDTO chooseCourseDTO);

    /**
     * 课程取消
     *
     * @param cancelCourseDTO
     * @return
     */
    String cancelCourse(CancelCourseDTO cancelCourseDTO);
}
