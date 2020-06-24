package io.choerodon.monitor.infra.task;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * User: Mr.Wang
 * Date: 2020/6/24
 */
@Component
public class FixAduitInterceptInterface {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    public static Map<String, String> stringLongHashMap = new HashMap<>();

    static {
        //code 与 操作拦截配置的id关联
        stringLongHashMap.put("hzero-iam.choerodon-organization-project.create", "'  [' + #_userDetails.getRealName() + '] 创建项目'");
        stringLongHashMap.put("hzero-iam.choerodon-organization-project.enableProject", "'  [' + #_userDetails.getRealName() + '] 启用了项目'");
        stringLongHashMap.put("hzero-iam.choerodon-project.disableProject", "'  [' + #_userDetails.getRealName() + '] 停用了项目'");
        stringLongHashMap.put("hzero-iam.organization-user.enableUser", "'  [' + #_userDetails.getRealName() + '] 启用了用户'");
        stringLongHashMap.put("hzero-iam.hiam-user.unfrozenUser", "'  [' + #_userDetails.getRealName() + '] 启用了用户'");
        stringLongHashMap.put("hzero-iam.hiam-user-site-level.unfrozenUser", "'  [' + #_userDetails.getRealName() + '] 启用了用户'");
        stringLongHashMap.put("hzero-iam.organization-user.resetUserPassword", "'  [' + #_userDetails.getRealName() + '] 重置了用户密码'");
        stringLongHashMap.put("hzero-iam.hiam-user.resetUserPassword", "'  [' + #_userDetails.getRealName() + '] 重置了用户密码'");
        stringLongHashMap.put("hzero-iam.choerodon-menu-role.create", "'  [' + #_userDetails.getRealName() + '] 分配了权限'");
        stringLongHashMap.put("hzero-asgard.saga-task-instance.retry", "'  [' + #_userDetails.getRealName() + '] 重试了事务'");
        stringLongHashMap.put("hzero-asgard.saga-task-instance-org.retry", "'  [' + #_userDetails.getRealName() + '] 重试了事务'");
        stringLongHashMap.put("hzero-iam.choerodon-organization-pro.create", "'  [' + #_userDetails.getRealName() + '] 创建了组织'");
        stringLongHashMap.put("hzero-iam.choerodon-tenant.enableOrganization", "'  [' + #_userDetails.getRealName() + '] 启用了组织'");
        stringLongHashMap.put("hzero-iam.choerodon_register_info_pro.approval", "'  [' + #_userDetails.getRealName() + '] 审批了组织'");
        stringLongHashMap.put("hzero-iam.choerodon-tenant.disableOrganization", "'  [' + #_userDetails.getRealName() + '] 停用了组织'");
        stringLongHashMap.put("hzero-iam.choerodon-user.addDefaultUsers", "'  [' + #_userDetails.getRealName() + '] 分配了root用户'");
        stringLongHashMap.put("hzero-iam.hiam-user.frozenUser", "'  [' + #_userDetails.getRealName() + '] 停用用户'");
        stringLongHashMap.put("hzero-iam.hiam-user-site-level.frozenUser", "'  [' + #_userDetails.getRealName() + '] 停用用户'");
        stringLongHashMap.put("hzero-iam.organization-user.disableUser", "'  [' + #_userDetails.getRealName() + '] 停用用户'");
    }

}
