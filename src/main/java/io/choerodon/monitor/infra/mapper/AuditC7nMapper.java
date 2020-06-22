package io.choerodon.monitor.infra.mapper;

import java.util.List;

import io.choerodon.monitor.api.vo.AuditOpLogVO;
import io.choerodon.mybatis.common.BaseMapper;

/**
 * User: Mr.Wang
 * Date: 2020/6/22
 */
public interface AuditC7nMapper extends BaseMapper<AuditOpLogVO> {
    List<AuditOpLogVO> selectList();
}
