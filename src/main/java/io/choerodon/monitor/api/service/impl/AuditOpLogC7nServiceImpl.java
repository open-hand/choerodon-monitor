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

import org.hzero.monitor.app.service.AuditOpLogService;
import org.hzero.monitor.domain.entity.AuditOpLog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    public Page<AuditOpLogVO> pageAuditOpLog(Long sourceId, PageRequest pageRequest) {

        Page<AuditOpLog> auditOpLogs = auditOpLogService.pageAuditOpLog(sourceId,
                null, null,
                null, null,
                null, null,
                null, null,
                null, pageRequest);

        List<AuditOpLogVO> auditOpLogVOSOrg = new ArrayList<>();
        List<AuditOpLogVO> auditOpLogVOSSite = new ArrayList<>();
        List<AuditOpLog> content = auditOpLogs.getContent();
        if (!CollectionUtils.isEmpty(content)) {
            for (AuditOpLog auditOpLog : content) {
                AuditOpLogVO auditOpLogVO = new AuditOpLogVO();
                BeanUtils.copyProperties(auditOpLog, auditOpLogVO);
                //不能以tenant来归类，根据具体的类型来归类。
                if (sourceId != 0 && StringUtils.endsWithIgnoreCase(AuditInterface.TYPE_LEVEL.get(auditOpLogVO.getRequestUrl()), ORGANIZATION)) {
                    auditOpLogVO.setType(AuditInterface.CODE_TYPE.get(auditOpLogVO.getRequestUrl()));
                    auditOpLogVOSOrg.add(auditOpLogVO);
                } else {
                    auditOpLogVO.setSourceType(SITE);
                    auditOpLogVO.setType(AuditInterface.CODE_TYPE.get(auditOpLogVO.getRequestUrl()));
                    auditOpLogVOSSite.add(auditOpLogVO);
                }
            }
        }
        Page<AuditOpLogVO> result = new Page<>();
        if (sourceId == 0) {
            result.setContent(auditOpLogVOSSite);
        } else {
            result.setContent(auditOpLogVOSOrg);
        }
        result.setNumberOfElements(auditOpLogs.getNumberOfElements());
        result.setNumber(auditOpLogs.getNumber());
        result.setTotalPages(auditOpLogs.getTotalPages());
        result.setSize(auditOpLogs.getSize());
        result.setTotalElements(auditOpLogs.getTotalElements());
        return result;
    }
}
