package io.choerodon.monitor.api.controller.v1;

import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.monitor.api.service.AuditOpLogC7nService;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.CustomPageRequest;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hzero.core.util.Results;
import org.hzero.monitor.domain.entity.AuditOpLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * User: Mr.Wang
 * Date: 2020/4/21
 */
@RestController
@RequestMapping("choerodon/v1/{source_id}")
public class AuditOpLogC7nController {

    @Autowired
    private AuditOpLogC7nService auditOpLogC7nService;

    @ApiOperation("查询组织层操作记录")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/organization/audit/operational/logs")
    @CustomPageRequest
    ResponseEntity<Page<AuditOpLog>> organizationPage(@ApiParam(value = "租户id", required = true) @PathVariable(name = "source_id") long sourceId,
                                                      @ApiIgnore @SortDefault(value = AuditOpLog.FIELD_LOG_ID, direction = Sort.Direction.DESC) PageRequest pageRequest) {
        return Results.success(auditOpLogC7nService.pageAuditOpLog(sourceId, pageRequest));
    }

    @ApiOperation("查询平台层操作记录")
    @Permission(level = ResourceLevel.SITE)
    @GetMapping("/site/audit/operational/logs")
    @CustomPageRequest
    ResponseEntity<Page<AuditOpLog>> sitePage(
            @ApiIgnore @SortDefault(value = AuditOpLog.FIELD_LOG_ID, direction = Sort.Direction.DESC) PageRequest pageRequest) {
        return Results.success(auditOpLogC7nService.pageAuditOpLog(Long.valueOf(0), pageRequest));
    }

}
