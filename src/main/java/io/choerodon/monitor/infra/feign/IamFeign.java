package io.choerodon.monitor.infra.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.choerodon.monitor.api.vo.Permission;
import io.choerodon.monitor.infra.feign.fallback.IamFeignFallback;


/**
 * User: Mr.Wang
 * Date: 2020/6/24
 */
@FeignClient(value = "hzero-iam", fallback = IamFeignFallback.class)
public interface IamFeign {
    @PostMapping("/choerodon/v1/permissions/list/code")
    ResponseEntity<List<Permission>> getPermission(@RequestBody(required = true) String[] code);

}
