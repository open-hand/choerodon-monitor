
databaseChangeLog(logicalFilePath: 'script/db/hmnt_audit_op_data_rel.groovy') {
    def weight_c = 1
    if(helper.isOracle()){
    weight_c = 2
    }
    if(helper.isOracle()){
    weight_c = 3
    }
    changeSet(author: "hzero@hand-china.com", id: "hmnt_audit_op_data_rel-2021-04-22-version-2"){
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hmnt_audit_op_data_rel_s', startValue:"1")
        }
        createTable(tableName: "hmnt_audit_op_data_rel", remarks: "操作审计配置数据关联") {
            column(name: "audit_column_rel_id", type: "bigint",  remarks: "审计配置字段关联ID，主键")  {constraints(primaryKey: true)} 
            column(name: "audit_op_config_id", type: "bigint",  remarks: "操作审计配置ID")  {constraints(nullable:"false")}  
            column(name: "source_table_name", type: "varchar(" + 60* weight_c + ")",  remarks: "来源表名")  {constraints(nullable:"false")}  
            column(name: "source_column_name", type: "varchar(" + 60* weight_c + ")",  remarks: "来源列名")   
            column(name: "target_table_name", type: "varchar(" + 60* weight_c + ")",  remarks: "目标表名")   
            column(name: "target_column_name", type: "varchar(" + 60* weight_c + ")",  remarks: "目标列名")   
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "创建人")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "最近更新人")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed :"CURRENT_TIMESTAMP",   remarks: "创建时间")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed :"CURRENT_TIMESTAMP",   remarks: "最近更新时间")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "order_seq", type: "int",  remarks: "排序号")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint",  remarks: "租户ID")  {constraints(nullable:"false")}  
        }
        addUniqueConstraint(columnNames:"audit_op_config_id,target_table_name,source_table_name",tableName:"hmnt_audit_op_data_rel",constraintName: "hmde_audit_column_rel_u1")
    }
}
