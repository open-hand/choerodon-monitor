package io.choerodon.monitor;

import org.hzero.autoconfigure.monitor.EnableHZeroMonitor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHZeroMonitor
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("io.choerodon")
public class MonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }

}
