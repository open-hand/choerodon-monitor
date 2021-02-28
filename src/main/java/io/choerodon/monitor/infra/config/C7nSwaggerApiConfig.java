package io.choerodon.monitor.infra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Created by wangxiang on 2021/2/28
 */
@Configuration
public class C7nSwaggerApiConfig {
    public static final String CHOERODON_AUDIT_OP_LOG_C7N = "Choerodon Audit Log";

    @Autowired
    public C7nSwaggerApiConfig(Docket docket) {
        docket.tags(
                new Tag(CHOERODON_AUDIT_OP_LOG_C7N, "Choerodon系统配置")
        );
    }
}
