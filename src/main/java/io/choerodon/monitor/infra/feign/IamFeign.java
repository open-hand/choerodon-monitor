package io.choerodon.monitor.infra.feign;

import java.util.List;

import org.hzero.common.HZeroService;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.choerodon.monitor.api.vo.IamUserDTO;
import io.choerodon.monitor.api.vo.Permission;
import io.choerodon.monitor.infra.feign.fallback.IamFeignFallback;


/**
 * User: Mr.Wang
 * Date: 2020/6/24
 */
@FeignClient(value = HZeroService.Iam.NAME, fallback = IamFeignFallback.class)
public interface IamFeign {
    @PostMapping("/choerodon/v1/permissions/list/code")
    ResponseEntity<List<Permission>> getPermission(@RequestBody(required = true) String[] code);

    @GetMapping(value = "/choerodon/v1/users/{id}/info")
    ResponseEntity<IamUserDTO> queryById(@PathVariable("id") Long id);

}
