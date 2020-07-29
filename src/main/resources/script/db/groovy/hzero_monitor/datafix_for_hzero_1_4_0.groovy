package script.db

databaseChangeLog(logicalFilePath: 'script/db/datafix_for_hzero_1_4_0.groovy') {
    changeSet(author: 'wanghao', id: '2020-07-21-data-fix') {
        preConditions(onFail: "MARK_RAN") {
            tableExists(tableName: "hmnt_audit_op_log_line")
            tableExists(tableName: "hmnt_audit_op_log")
        }
        sql("""
            UPDATE hmnt_audit_op_log_line haoll
            SET haoll.tenant_id = ( SELECT haol.tenant_id FROM hmnt_audit_op_log haol WHERE haol.log_id = haoll.log_id)
            WHERE haoll.log_id in ( SELECT log_id FROM hmnt_audit_op_log);
            """)
    }
}