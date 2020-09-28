package io.choerodon.monitor.infra.task;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hzero.monitor.domain.entity.AuditOpConfig;
import org.hzero.monitor.domain.repository.AuditOpConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import io.choerodon.core.exception.CommonException;
import io.choerodon.monitor.api.vo.Permission;
import io.choerodon.monitor.infra.feign.IamFeign;

/**
 * User: Mr.Wang
 * Date: 2020/6/15
 */
@Component
public class AuditConfigInitializationRunner implements CommandLineRunner {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final String API = "API";

    private final String API = "API";

    @Autowired
    private IamFeign iamFeign;

    @Autowired
    private AuditOpConfigRepository auditOpConfigRepository;


    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            try {
                Set<String> keySet = FixAduitInterceptInterface.stringLongHashMap.keySet();
                String[] codes = keySet.toArray(new String[]{});
                List<Permission> permissionList = iamFeign.getPermission(codes).getBody();
                if (CollectionUtils.isEmpty(permissionList)) {
                    return;
                }
                Map<String, String> stringLongHashMap = FixAduitInterceptInterface.stringLongHashMap;
                auditOpConfigRepository.batchDelete(auditOpConfigRepository.selectAll());
                LOGGER.info("更新操作拦截配置");
                for (Permission permission : permissionList) {
                    AuditOpConfig auditOpConfig = new AuditOpConfig();
                    auditOpConfig.setAuditOpConfigId(Long.valueOf(permissionList.indexOf(permission) + 1));
                    auditOpConfig.setPermissionId(permission.getId());
                    auditOpConfig.setAuditArgsFlag(1);
                    auditOpConfig.setAuditResultFlag(1);
                    auditOpConfig.setAuditContent(stringLongHashMap.get(permission.getCode()));
                    auditOpConfig.setTenantId(0L);
                    auditOpConfig.setCreatedBy(1L);
                    auditOpConfig.setObjectVersionNumber(1L);
                    auditOpConfig.setCreationDate(new Date());
                    auditOpConfig.setLastUpdatedBy(1L);
                    auditOpConfig.setLastUpdateDate(new Date());
                    auditOpConfig.setAuditDataFlag(1);
                    auditOpConfig.setAuditType(API);
                    auditOpConfigRepository.insert(auditOpConfig);
                }
            } catch (Exception e) {
                throw new CommonException("update.audit.fail", e);
            }

        }).start();
    }
}
