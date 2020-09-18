package io.choerodon.monitor.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.choerodon.core.domain.Page;
import io.choerodon.monitor.api.service.AuditOpLogC7nService;
import io.choerodon.monitor.api.vo.AuditOpLogVO;
import io.choerodon.monitor.infra.mapper.AuditC7nMapper;
import io.choerodon.monitor.infra.utils.AuditInterface;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

import org.hzero.monitor.api.dto.AuditOpLogRequest;
import org.hzero.monitor.app.service.AuditOpLogService;
import org.hzero.monitor.domain.entity.AuditOpLog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * User: Mr.Wang
 * Date: 2020/4/21
 */
@Service
public class AuditOpLogC7nServiceImpl implements AuditOpLogC7nService {

    private static final String SITE = "site";
    private static final String ORGANIZATION = "organization";

    @Autowired
    private AuditOpLogService auditOpLogService;

    @Override
    public Page<AuditOpLogVO> pageAuditOpLog(Long sourceId, AuditOpLogRequest auditOpLogRequest, PageRequest pageRequest) {
        Page<AuditOpLog> auditOpLogs = auditOpLogService.pageAuditOpLog(auditOpLogRequest, pageRequest);
        List<AuditOpLogVO> auditOpLogVOS = new ArrayList<>();
        List<AuditOpLog> content = auditOpLogs.getContent();
        if (!CollectionUtils.isEmpty(content)) {
            for (AuditOpLog auditOpLog : content) {
                AuditOpLogVO auditOpLogVO = new AuditOpLogVO();
                BeanUtils.copyProperties(auditOpLog, auditOpLogVO);
                if (sourceId == 0) {
                    auditOpLogVO.setSourceType(SITE);
                } else {
                    auditOpLogVO.setSourceType(ORGANIZATION);
                }
                auditOpLogVO.setType(AuditInterface.CODE_TYPE.get(auditOpLogVO.getRequestUrl()));
                auditOpLogVOS.add(auditOpLogVO);
            }
        }
        Page<AuditOpLogVO> result = new Page<>();
        result.setContent(auditOpLogVOS);
        result.setNumberOfElements(auditOpLogs.getNumberOfElements());
        result.setNumber(auditOpLogs.getNumber());
        result.setTotalPages(auditOpLogs.getTotalPages());
        result.setSize(auditOpLogs.getSize());
        result.setTotalElements(auditOpLogs.getTotalElements());
        return result;
    }
}
