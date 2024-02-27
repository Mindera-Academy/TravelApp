package com.cart.cart.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app")
@Component
@Getter
@Setter
public class AppProperty {
    private Trip trip;

    @Getter
    @Setter
    public static class Trip {
        private String host;
        private Integer port;
        private String baseUrl;
    }
}
