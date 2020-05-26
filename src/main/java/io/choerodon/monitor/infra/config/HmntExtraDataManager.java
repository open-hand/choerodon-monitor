package io.choerodon.monitor.infra.config;

import io.choerodon.core.swagger.ChoerodonRouteData;
import io.choerodon.swagger.annotation.ChoerodonExtraData;
import io.choerodon.swagger.swagger.extra.ExtraData;
import io.choerodon.swagger.swagger.extra.ExtraDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@ChoerodonExtraData
public class HmntExtraDataManager implements ExtraDataManager {
    @Autowired
    private Environment environment;

    public HmntExtraDataManager() {
    }

    public ExtraData getData() {
        ChoerodonRouteData choerodonRouteData = new ChoerodonRouteData();
        choerodonRouteData.setName(this.environment.getProperty("hzero.service.current.name", "hzero-monitor"));
        choerodonRouteData.setPath(this.environment.getProperty("hzero.service.current.path", "/hmnt/**"));
        choerodonRouteData.setServiceId(this.environment.getProperty("hzero.service.current.service-name", "${hzero.service.iam.name:hzero-monitor}"));
        choerodonRouteData.setPackages("org.hzero.monitor");
        extraData.put("choerodon_route", choerodonRouteData);
        return extraData;
    }
}
