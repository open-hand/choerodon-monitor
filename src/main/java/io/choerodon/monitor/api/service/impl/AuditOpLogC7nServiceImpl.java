package io.choerodon.monitor.api.service.impl;

import io.choerodon.core.domain.Page;
import io.choerodon.monitor.api.service.AuditOpLogC7nService;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.monitor.app.service.AuditOpLogService;
import org.hzero.monitor.domain.entity.AuditOpLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Mr.Wang
 * Date: 2020/4/21
 */
@Service
public class AuditOpLogC7nServiceImpl implements AuditOpLogC7nService {

    @Autowired
    private AuditOpLogService auditOpLogService;

    @Override
    public Page<AuditOpLog> pageAuditOpLog(Long sourceId, PageRequest pageRequest) {
        return auditOpLogService.pageAuditOpLog(sourceId,
                null, null,
                null, null,
                null, null,
                null, null,
                null, pageRequest);
    }
}
