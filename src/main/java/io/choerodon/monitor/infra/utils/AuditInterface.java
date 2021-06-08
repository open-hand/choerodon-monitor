package io.choerodon.monitor.infra.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Mr.Wang
 * Date: 2020/6/15
 */
public class AuditInterface {

    private AuditInterface() {
    }

    public static Map<String, String> CODE_TYPE = new HashMap<>();
    public static Map<String, String> TYPE_LEVEL = new HashMap<>();

    //操作类型与接口的
    static {
        CODE_TYPE.put(SiteAuditInterface.CREATE_ORG, "createOrg");
        CODE_TYPE.put(SiteAuditInterface.ASSIGN_USERSROLES_SITE_ONE, "addAdminUsers");
        CODE_TYPE.put(SiteAuditInterface.ENABL_PROJECT, "enableProject");
        CODE_TYPE.put(SiteAuditInterface.DISABLE_PROJECT, "disableProject");
        CODE_TYPE.put(SiteAuditInterface.DISABLE_PROJECT_BUS, "disableProject");
        CODE_TYPE.put(SiteAuditInterface.ENABL_EUSER_ONE, "enableUser");
        CODE_TYPE.put(SiteAuditInterface.ENABL_EUSER_TWO, "enableUser");
        CODE_TYPE.put(SiteAuditInterface.ENABL_EUSER_THREE, "enableUser");
        CODE_TYPE.put(SiteAuditInterface.RESETUSER_PASSWORD_ONE, "resetUserPassword");
        CODE_TYPE.put(SiteAuditInterface.RESETUSER_PASSWORD_TWO, "resetUserPassword");
        CODE_TYPE.put(SiteAuditInterface.ASSIGN_USERSROLES_SITE_TWO, "assignUserRole");
        CODE_TYPE.put(SiteAuditInterface.SITE_RETRY, "siteRetry");
        CODE_TYPE.put(SiteAuditInterface.ORG_RETRY, "orgRetry");
        CODE_TYPE.put(SiteAuditInterface.ENABLE_ORGANIZATION, "enableOrganization");
        CODE_TYPE.put(SiteAuditInterface.REGISTERS_APPROVAL, "registersApproval");
        CODE_TYPE.put(SiteAuditInterface.DISABLE_ORGANIZATION, "disableOrganization");
        CODE_TYPE.put(SiteAuditInterface.CREATE_PROJECT, "createProject");
        CODE_TYPE.put(SiteAuditInterface.DISABLE_USER_ONE, "disableUser");
        CODE_TYPE.put(SiteAuditInterface.DISABLE_USER_TWO, "disableUser");
        CODE_TYPE.put(SiteAuditInterface.DISABLE_USER_THREE, "disableUser");
        CODE_TYPE.put(SiteAuditInterface.ASSIGN_USERSROLE, "assignUserRole");
        CODE_TYPE.put(SiteAuditInterface.ASSIGN_USERSROLE_ORG, "assignUserRole");

        TYPE_LEVEL.put(SiteAuditInterface.CREATE_ORG, "SITE");
        TYPE_LEVEL.put(SiteAuditInterface.ASSIGN_USERSROLES_SITE_ONE, "SITE");
        TYPE_LEVEL.put(SiteAuditInterface.ENABL_PROJECT, "ORGANIZATION");
        TYPE_LEVEL.put(SiteAuditInterface.DISABLE_PROJECT, "ORGANIZATION");
        TYPE_LEVEL.put(SiteAuditInterface.DISABLE_PROJECT_BUS, "ORGANIZATION");
        TYPE_LEVEL.put(SiteAuditInterface.ENABL_EUSER_ONE, "ORGANIZATION");
        TYPE_LEVEL.put(SiteAuditInterface.ENABL_EUSER_TWO, "ORGANIZATION");
        TYPE_LEVEL.put(SiteAuditInterface.ENABL_EUSER_THREE, "ORGANIZATION");
        TYPE_LEVEL.put(SiteAuditInterface.RESETUSER_PASSWORD_ONE, "ORGANIZATION");
        TYPE_LEVEL.put(SiteAuditInterface.RESETUSER_PASSWORD_TWO, "ORGANIZATION");
        TYPE_LEVEL.put(SiteAuditInterface.ASSIGN_USERSROLES_SITE_TWO, "ORGANIZATION");
        TYPE_LEVEL.put(SiteAuditInterface.ASSIGN_USERSROLE, "ORGANIZATION");
        TYPE_LEVEL.put(SiteAuditInterface.ASSIGN_USERSROLE_ORG, "ORGANIZATION");
        TYPE_LEVEL.put(SiteAuditInterface.SITE_RETRY, "SITE");
        TYPE_LEVEL.put(SiteAuditInterface.ORG_RETRY, "ORGANIZATION");
        TYPE_LEVEL.put(SiteAuditInterface.ENABLE_ORGANIZATION, "SITE");
        TYPE_LEVEL.put(SiteAuditInterface.REGISTERS_APPROVAL, "SITE");
        TYPE_LEVEL.put(SiteAuditInterface.DISABLE_ORGANIZATION, "SITE");
        TYPE_LEVEL.put(SiteAuditInterface.CREATE_PROJECT, "ORGANIZATION");
        TYPE_LEVEL.put(SiteAuditInterface.DISABLE_USER_ONE, "ORGANIZATION");
        TYPE_LEVEL.put(SiteAuditInterface.DISABLE_USER_TWO, "ORGANIZATION");
        TYPE_LEVEL.put(SiteAuditInterface.DISABLE_USER_THREE, "ORGANIZATION");
    }


    private interface SiteAuditInterface {
        //平台层创建组织
        String CREATE_ORG = "/choerodon/v1/organizations";
        //平台层权限分配
        String ASSIGN_USERSROLES_SITE_ONE = "/choerodon/v1/users/admin";
        //启用组织
        String ENABLE_ORGANIZATION = "/choerodon/v1/organizations/{tenant_id}/enable";
        //停用了组织
        String DISABLE_ORGANIZATION = "/choerodon/v1/organizations/{tenant_id}/disable";

        //组织层 启用项目
        String ENABL_PROJECT = "/choerodon/v1/organizations/{organization_id}/projects/{project_id}/enable";
        //组织层 禁用项目
        String DISABLE_PROJECT = "/choerodon/v1/projects/{project_id}/disable";
        String DISABLE_PROJECT_BUS = "/choerodon/v1/organizations/{organization_id}/projects/{project_id}/disable";
        //创建项目
        String CREATE_PROJECT = "/choerodon/v1/organizations/{organization_id}/projects";
        //启用用户
        String ENABL_EUSER_ONE = "/choerodon/v1/organizations/{organization_id}/users/{id}/enable";
        String ENABL_EUSER_TWO = "/hzero/v1/{organizationId}/users/{userId}/unfrozen";
        String ENABL_EUSER_THREE = "/hzero/v1/users/{userId}/unfrozen";
        //重置了用户密码
        String RESETUSER_PASSWORD_ONE = "/choerodon/v1/organizations/{organization_id}/users/{id}/reset";
        String RESETUSER_PASSWORD_TWO = "/hzero/v1/{organizationId}/users/{userId}/admin-reset-password";

        //创建角色 创建角色并分配权限
        String ASSIGN_USERSROLES_SITE_TWO = "/choerodon/v1/organizations/{organization_id}/roles";
        String ASSIGN_USERSROLE = "/choerodon/v1/projects/{project_id}/users/assign_roles";
        String ASSIGN_USERSROLE_ORG = "/choerodon/v1/organizations/{organization_id}/users/assign_roles";

        //重试事务
        String SITE_RETRY = "/v1/sagas/tasks/instances/{id}/retry";
        String ORG_RETRY = "/v1/sagas/organizations/{organization_id}/tasks/instances/{id}/retry";

        //审批组织
        String REGISTERS_APPROVAL = "/choerodon/v1/registers/approval/{id}";

        //禁用用户
        String DISABLE_USER_ONE = "/hzero/v1/{organizationId}/users/{userId}/frozen";
        String DISABLE_USER_TWO = "/hzero/v1/users/{userId}/frozen";
        String DISABLE_USER_THREE = "/choerodon/v1/organizations/{organization_id}/users/{id}/disable";
    }
}
