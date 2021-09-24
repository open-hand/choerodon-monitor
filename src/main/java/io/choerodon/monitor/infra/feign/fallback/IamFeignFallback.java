package io.choerodon.monitor.infra.feign.fallback;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.choerodon.core.exception.CommonException;
import io.choerodon.monitor.api.vo.IamUserDTO;
import io.choerodon.monitor.api.vo.Permission;
import io.choerodon.monitor.infra.feign.IamFeign;

/**
 * User: Mr.Wang
 * Date: 2020/6/24
 */
@Component
public class IamFeignFallback implements IamFeign {
    @Override
    public ResponseEntity<List<Permission>> getPermission(String[] code) {
        throw new CommonException("query.permission.by.code,error");
    }

    @Override
    public ResponseEntity<IamUserDTO> queryById(Long id) {
        throw new CommonException("error.user.get.byId");
    }
}
