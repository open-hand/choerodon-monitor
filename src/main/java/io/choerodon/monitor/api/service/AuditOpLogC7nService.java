package io.choerodon.monitor.api.service;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.monitor.domain.entity.AuditOpLog;

/**
 * User: Mr.Wang
 * Date: 2020/4/21
 */
public interface AuditOpLogC7nService {
    Page<AuditOpLog> pageAuditOpLog(Long sourceId, PageRequest pageRequest);
}
