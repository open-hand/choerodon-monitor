package script.db

databaseChangeLog(logicalFilePath: 'script/db/hmnt_audit_op_desensitize.groovy') {
    changeSet(author: "qingsheng.chen@hand-china.com", id: "2020-09-15-hmnt_audit_op_desensitize") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hmnt_audit_op_desensitize_s', startValue:"1")
        }
        createTable(tableName: "hmnt_audit_op_desensitize", remarks: "操作审计字段脱敏配置") {
            column(name: "audit_op_desensitize_id", type: "bigint", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)}
            column(name: "audit_op_config_id", type: "bigint",  remarks: "操作审计配置ID")  {constraints(nullable:"false")}
            column(name: "field_name", type: "varchar(" + 120 * weight + ")",  remarks: "字段名称")  {constraints(nullable:"false")}  
            column(name: "desensitize_rule", type: "varchar(" + 60 * weight + ")",  remarks: "脱敏规则")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint",   defaultValue:"0",   remarks: "租户ID，hpfm_tenant.tenant_id")  {constraints(nullable:"false")}
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"audit_op_config_id,field_name",tableName:"hmnt_audit_op_desensitize",constraintName: "hmnt_audit_op_desensitize_u1")
    }
}