package org.hzero.monitor.api.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import org.hzero.boot.monitor.audit.op.util.StringUtils;
import org.hzero.boot.platform.lov.annotation.ProcessLovValue;
import org.hzero.core.base.BaseConstants;
import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;
import org.hzero.export.annotation.ExcelExport;
import org.hzero.export.vo.ExportParam;
import org.hzero.monitor.api.dto.AuditOpLogRequest;
import org.hzero.monitor.api.dto.OperationalAudit;
import org.hzero.monitor.app.service.AuditOpLogService;
import org.hzero.monitor.config.MonitorSwaggerApiConfig;
import org.hzero.monitor.domain.entity.AuditOpLog;
import org.hzero.monitor.domain.entity.AuditOpLogLine;
import org.hzero.monitor.infra.constant.MonitorConstants;
import org.hzero.starter.keyencrypt.core.Encrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.monitor.api.vo.IamUserDTO;
import io.choerodon.monitor.infra.feign.IamFeign;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.CustomPageRequest;
import io.choerodon.swagger.annotation.Permission;

/**
 * @author qingsheng.chen@hand-china.com
 */
@Api(tags = MonitorSwaggerApiConfig.AUDIT_OP_LOG)
@RestController("auditOpLogController.v1")
@RequestMapping("/v1/{organizationId}")
public class AuditOpLogController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(AuditOpLogController.class);
    private AuditOpLogService auditOpLogService;

    @Autowired
    private IamFeign iamFeign;

    @Autowired
    public AuditOpLogController(AuditOpLogService auditOpLogService) {
        this.auditOpLogService = auditOpLogService;
    }

    @ApiOperation("查询操作审计记录")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/audit/operational/logs")
    @CustomPageRequest
    @ProcessLovValue(targetField = {BaseConstants.FIELD_BODY, BaseConstants.FIELD_BODY + ".auditOpLogLineList"})
    public ResponseEntity<Page<AuditOpLog>> page(@ApiParam(value = "租户id", required = true) @PathVariable long organizationId,
                                                 @Encrypt AuditOpLogRequest auditOpLogRequest,
                                                 @ApiIgnore @SortDefault(value = AuditOpLog.FIELD_LOG_ID, direction = Sort.Direction.DESC) PageRequest pageRequest) {
        return Results.success(auditOpLogService.pageAuditOpLog(auditOpLogRequest.setTenantId(organizationId), pageRequest));
    }

    @ApiOperation("查询操作审计记录行")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/audit/operational/logs/line/{logLineId}")
    @ProcessLovValue(targetField = {BaseConstants.FIELD_BODY})
    public ResponseEntity<AuditOpLogLine> query(@ApiParam(value = "租户id", required = true) @PathVariable long organizationId,
                                                @Encrypt @ApiParam("操作审计记录行id") @PathVariable long logLineId) {
        return Results.success(auditOpLogService.queryAuditOpLogLine(logLineId));
    }

    @ApiOperation("新增操作审计记录")
    @Permission(level = ResourceLevel.ORGANIZATION, permissionWithin = true)
    @PostMapping("/audit/operational")
    public ResponseEntity<Void> create(@ApiParam(value = "租户id", required = true) @PathVariable long organizationId,
                                       @Encrypt @RequestBody OperationalAudit operationalAudit) {
        logger.debug(">>>>> [OA] Receive audit data : {}", operationalAudit);
        //content中如果操作者包含null 则要替换为admin
        ResponseEntity<IamUserDTO> iamUserDTOResponseEntity = iamFeign.queryById(operationalAudit.getUserId());
        if (Objects.isNull(iamUserDTOResponseEntity) || iamUserDTOResponseEntity.getBody() == null) {
            logger.info(">>>>> operater user is null >>>>>>>");
            return Results.success();
        }
        operationalAudit.setContent(operationalAudit.getContent().replace("null", iamUserDTOResponseEntity.getBody().getRealName()));
        AuditOpLog auditOpLog = new AuditOpLog()
                .setServiceName(operationalAudit.getServiceName())
                .setUserId(operationalAudit.getUserId())
                .setAuditContent(operationalAudit.getContent())
                .setAuditDatetime(operationalAudit.getOperationalDate())
                .setAuditResult(MonitorConstants.AuditResult.getAuditResult(operationalAudit.isFailed()))
                .setTimeConsuming(operationalAudit.getTimeConsuming())
                .setTenantId(organizationId)
                .setRequestUrl(operationalAudit.getRequestUrl())
                .setRequestIp(operationalAudit.getRequestIp())
                .setRequestReferrer(operationalAudit.getRequestReferrer())
                .setRequestUserAgent(operationalAudit.getRequestUserAgent())
                .setRequestMethod(operationalAudit.getRequestMethod())
                .setAuditBatchNumber(operationalAudit.getAuditBatchNumber())
                .setMenuId(operationalAudit.getMenuId())
                .setAuditOpConfigId(operationalAudit.getAuditOpConfigId())
                .setRoleId(operationalAudit.getRoleId())
                .setClientName(operationalAudit.getClientName())
                .setBusinessKey(operationalAudit.getBusinessKey());
        List<AuditOpLogLine> auditOpLogLineList = new ArrayList<>();
        if (StringUtils.hasText(operationalAudit.getArgs())) {
            auditOpLogLineList.add(new AuditOpLogLine()
                    .setLogType(MonitorConstants.AuditLogType.PARAMETER)
                    .setLogContent(operationalAudit.getArgs())
                    .setTenantId(organizationId));
        }
        if (StringUtils.hasText(operationalAudit.getResult())) {
            auditOpLogLineList.add(new AuditOpLogLine()
                    .setLogType(MonitorConstants.AuditLogType.RESULT)
                    .setLogContent(operationalAudit.getResult())
                    .setTenantId(organizationId));
        }
        validObject(auditOpLog.setAuditOpLogLineList(auditOpLogLineList));
        auditOpLogService.createAuditOpLog(auditOpLog);
        return Results.success();
    }

    @ApiOperation("导出操作审计记录")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/audit/operational/logs/export")
    @ExcelExport(AuditOpLog.class)
    @CustomPageRequest
    public ResponseEntity<List<AuditOpLog>> export(@ApiParam(value = "租户id", required = true) @PathVariable long organizationId,
                                                   @Encrypt AuditOpLogRequest auditOpLogRequest,
                                                   HttpServletResponse response,
                                                   @ApiIgnore @SortDefault(value = AuditOpLog.FIELD_LOG_ID, direction = Sort.Direction.DESC) PageRequest pageRequest,
                                                   ExportParam exportParam) {
        return Results.success(auditOpLogService.listAuditOpLog(auditOpLogRequest, pageRequest));
    }
}
