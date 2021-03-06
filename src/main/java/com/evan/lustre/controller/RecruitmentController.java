package com.evan.lustre.controller;

import com.evan.lustre.common.annotation.Authentication;
import com.evan.lustre.common.enums.RoleEnum;
import com.evan.lustre.common.model.param.RecruitmentParam;
import com.evan.lustre.common.model.vo.ResponseVO;
import com.evan.lustre.common.utils.ResponseUtil;
import com.evan.lustre.service.RecruitmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName RecruitmentController
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/4 21:24
 */
@RestController
@RequestMapping("/api/recruitment")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Api(tags = "Recruitment Module Interface")
public class RecruitmentController {
    /**
     * 信息查询模块：招聘信息的增删改查
     */
    private final RecruitmentService recruitmentService;

    @PostMapping("publish")
    @ApiOperation("Publish a recruitment")
    @Authentication(RoleEnum.ADMIN)
    public ResponseEntity<ResponseVO> publish(@RequestBody @Valid RecruitmentParam recruitmentParam) {
        recruitmentService.create(recruitmentParam);
        return ResponseUtil.successResponse();
    }

    @PutMapping("{recruitmentId:\\d+}")
    @ApiOperation("Update a recruitment")
    @Authentication(RoleEnum.ADMIN)
    public ResponseEntity<ResponseVO> updateOne(@PathVariable Integer recruitmentId,
                                                @RequestBody @Valid RecruitmentParam recruitmentParam) {
        return ResponseUtil.successResponse(recruitmentService.updateOne(recruitmentId, recruitmentParam));
    }

    @DeleteMapping("{recruitmentId:\\d+}")
    @ApiOperation("Delete a recruitment")
    @Authentication(RoleEnum.ADMIN)
    public ResponseEntity<ResponseVO> delete(@PathVariable Integer recruitmentId) {
        recruitmentService.deleteOne(recruitmentId);
        return ResponseUtil.successResponse();
    }

    @GetMapping("{recruitmentId:\\d+}")
    @ApiOperation("Get one recruitment")
    public ResponseEntity<ResponseVO> getOne(@PathVariable Integer recruitmentId) {
        return ResponseUtil.successResponse(recruitmentService.getOne(recruitmentId));
    }

    @GetMapping("all")
    @ApiOperation("Get all recruitment")
    public ResponseEntity<ResponseVO> getAll() {
        return ResponseUtil.successResponse(recruitmentService.getAll());
    }
    //TODO apply the recruitment api.
    //TODO add the candidate table.
    //TODO add more user info into user entity,eg:age,sex,skills,education and so on.
    //TODO fix the exception messages
    //TODO fix the cross origin
}
