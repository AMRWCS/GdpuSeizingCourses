package org.amrwcs.gdpuseizingcourses.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.amrwcs.gdpuseizingcourses.common.constant.MessageConstant;
import org.amrwcs.gdpuseizingcourses.common.constant.ParamPatternConstant;
import org.amrwcs.gdpuseizingcourses.common.constant.URLConstant;
import org.amrwcs.gdpuseizingcourses.common.exception.CookieExpiredException;
import org.amrwcs.gdpuseizingcourses.common.exception.ServerResponseException;
import org.amrwcs.gdpuseizingcourses.pojo.dto.CancelCourseDTO;
import org.amrwcs.gdpuseizingcourses.pojo.dto.ChooseCourseDTO;
import org.amrwcs.gdpuseizingcourses.pojo.entity.Cookie;
import org.amrwcs.gdpuseizingcourses.pojo.entity.JxbIdsRequest;
import org.amrwcs.gdpuseizingcourses.service.CoursesOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
@Slf4j
public class CoursesOperationServiceImpl implements CoursesOperationService {

    @Autowired
    private RestClient restClient;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 课程选中
     * @param chooseCourseDTO
     * @return
     */
    @Override
    public String chooseCourse(ChooseCourseDTO chooseCourseDTO) {

        //判断jxb_ids格式有效性，无效则获取
        if(isNotValidJxbIds(chooseCourseDTO.getJxbIds())) {
            chooseCourseDTO.setJxbIds(fetchJxbIds(chooseCourseDTO.getKchId(), chooseCourseDTO.getJxbIdsRequest(),chooseCourseDTO.getCookie()));
        }else {
            log.info("选课无需获取jxb_ids");
        }

        //组装选课请求体表单
        MultiValueMap<String, String> form = buildSelectForm(chooseCourseDTO);

        //发送选课请求（写操作，不自动重试，避免重复选课）
        String resp = postForm(URLConstant.SEIZE_COURSES_URL, form, chooseCourseDTO.getCookie(), false);

        //解析结果
        parseResponse(resp,true);

        return chooseCourseDTO.getJxbIds();
    }

    /**
     * 课程取消
     *
     * @param cancelCourseDTO
     * @return
     */
    @Override
    public String cancelCourse(CancelCourseDTO cancelCourseDTO) {

        //判断jxb_ids格式有效性，无效则获取
        if(isNotValidJxbIds(cancelCourseDTO.getJxbIds())) {
            cancelCourseDTO.setJxbIds(fetchJxbIds(cancelCourseDTO.getKchId(), cancelCourseDTO.getJxbIdsRequest(),cancelCourseDTO.getCookie()));
        }else {
            log.info("退课无需获取jxb_ids");
        }

        //组装退课请求体表单
        MultiValueMap<String, String> form = buildRemoveForm(cancelCourseDTO);

        //发送退课请求（写操作，不自动重试，避免重复退课）
        String resp=postForm(URLConstant.REMOVE_COURSES_URL,form,cancelCourseDTO.getCookie(),false);

        //解析结果
        parseResponse(resp,false);

        return cancelCourseDTO.getJxbIds();
    }

    //判断jxb_ids参数格式是否无效
    private boolean isNotValidJxbIds(String jxbIds) {
        return jxbIds == null || !jxbIds.matches(ParamPatternConstant.JXBIDS_PATTERN);
    }

    //获取jxb_ids参数
    private String fetchJxbIds(String kchId, JxbIdsRequest jxbIdsRequest,Cookie cookie){

        log.info("开始获取jxb_ids");
        
        //组装jxb_ids请求表单
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("kch_id", kchId);
        form.add("njdm_id", jxbIdsRequest.getNjdmId());
        form.add("xkxnm", jxbIdsRequest.getXkxnm());
        form.add("xkxqm", jxbIdsRequest.getXkxqm());
        form.add("kklxdm", jxbIdsRequest.getKklxdm());
        form.add("xkkz_id", jxbIdsRequest.getXkkzId());

        //发送jxb_ids请求（只读操作，可重试）
        String resp=postForm(URLConstant.GET_JXBID_URL,form,cookie,true);

        //解析结果
        log.info("开始解析jxb_ids响应");
        JsonNode root = objectMapper.readTree(resp);
        if (!root.isArray() || root.isEmpty()) {
            log.error("获取的jxb_ids响应结构异常");
            throw new ServerResponseException(MessageConstant.RESPONSE_ERROR);
        }
        String doJxbId = root.get(0).path("do_jxb_id").asText();
        if (isNotValidJxbIds(doJxbId)) {
            log.error("获取的do_jxb_id值格式非法");
            throw new ServerResponseException(MessageConstant.RESPONSE_ERROR);
        }
        log.info("解析jxb_ids响应成功");
        
        log.info("获取jxb_ids成功");
        
        return doJxbId;
    }

    //组装选课表单
    private MultiValueMap<String, String> buildSelectForm(ChooseCourseDTO chooseCourseDTO) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("jxb_ids", chooseCourseDTO.getJxbIds());
        form.add("kch_id", chooseCourseDTO.getKchId());
        form.add("qz", chooseCourseDTO.getQz());
        return form;
    }

    //组装退课表单
    private MultiValueMap<String, String> buildRemoveForm(CancelCourseDTO cancelCourseDTO) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("jxb_ids", cancelCourseDTO.getJxbIds());
        form.add("kch_id", cancelCourseDTO.getKchId());

        JxbIdsRequest jxbIdsRequest = cancelCourseDTO.getJxbIdsRequest();
        form.add("xkxnm", jxbIdsRequest.getXkxnm());
        form.add("xkxqm", jxbIdsRequest.getXkxqm());

        return form;
    }

    //最多尝试请求次数（首次 + 2次重试）
    private static final int MAX_ATTEMPTS = 3;
    //重试退避基础间隔（毫秒），按次数递增：1s、2s
    private static final long RETRY_DELAY_MS = 1000;

    //发送请求（retriable即是否超时重试，仅用于只读操作，如获取jxbIds）
    private String postForm(String url, MultiValueMap<String, String> form, Cookie cookie, boolean retriable) {

        for (int attempt=1;;attempt++) {
            try {
                log.info("开始发送请求（第{}次尝试）：{}", attempt, url);

                String result = restClient.post()
                        .uri(URLConstant.BASE_URL + url)
                        .headers(headers -> {
                            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                            headers.set("X-Requested-With", "XMLHttpRequest");
                            headers.set("Cookie", "route=" + cookie.getRoute() + ";" + "JSESSIONID=" + cookie.getJSESSIONID());
                        })
                        .body(form)
                        .retrieve()
                        //响应901时抛出'登录状态失效：Cookie失效'错误
                        .onStatus(status -> status.value() == 901, (request, response) -> {
                            throw new CookieExpiredException(MessageConstant.COOKIE_EXPIRED_ERROR);
                        })
                        //响应4xx、5xx时抛出'教务请求失败'错误
                        .onStatus(HttpStatusCode::isError, (request, response) -> {
                            throw new ServerResponseException(MessageConstant.RESPONSE_ERROR + ": status=" + response.getStatusCode());
                        })
                        .body(String.class);

                log.info("成功接收响应：{}", result);
                return result;

            } catch (ResourceAccessException e) {
                //仅对超时/连接类异常重试；业务失败与Cookie过期不进入此处，直接向上抛出
                log.warn("请求超时或连接异常（第{}次尝试失败）：{}", attempt, e.getMessage());
                if (!retriable || attempt >= MAX_ATTEMPTS) {
                    throw new ServerResponseException(MessageConstant.RESPONSE_ERROR+": Connection Timeout");
                }
                try {
                    //递增退避：第1次失败后等1s，第2次失败后等2s
                    Thread.sleep(RETRY_DELAY_MS * attempt);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new ServerResponseException(MessageConstant.SERVER_UNKNOWN_ERROR);
                }
            }
        }
    }

    //解析选课、退课响应结果
    private void parseResponse(String resp,boolean isSelect){
        log.info("开始解析响应");
        if(!isSelect && resp.equals("\"1\"")){
            log.info("解析响应成功");
            return;
        }
        JsonNode root = objectMapper.readTree(resp);
        if (root.isEmpty()) {
            log.error("响应内容为空");
            throw new ServerResponseException(MessageConstant.RESPONSE_ERROR);
        }
        String flag = root.path("flag").asText();
        String msg = root.path("msg").asText();
        switch (flag) {
            case "1" -> log.info("解析响应成功");
            case "0"  -> throw new ServerResponseException(MessageConstant.RESPONSE_ERROR+": "+msg);
            //可能的原因有 课程已满/已选时选课、课程未选时退课、...
            case "-1" -> throw new ServerResponseException(MessageConstant.RESPONSE_ERROR+": Possible reasons are, select when course are full/selected, remove when course aren't selected, ...");
            default -> throw new ServerResponseException(MessageConstant.RESPONSE_ERROR+": flag="+flag);
        }
    }

}
