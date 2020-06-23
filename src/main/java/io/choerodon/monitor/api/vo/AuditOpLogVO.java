package io.choerodon.monitor.api.vo;

import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import org.hzero.monitor.domain.entity.AuditOpLog;

import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;

/**
 * User: Mr.Wang
 * Date: 2020/6/22
 */
@ApiModel("操作审计记录")
@VersionAudit
@ModifyAudit
@Table(
        name = "hmnt_audit_op_log"
)
public class AuditOpLogVO extends AuditOpLog {
    private String type;
    private String sourceType;

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
