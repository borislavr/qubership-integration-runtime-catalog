package org.qubership.integration.platform.runtime.catalog.service.deployment.properties.builders;

import java.util.HashMap;
import java.util.Map;

import org.qubership.integration.platform.catalog.persistence.configs.entity.chain.element.ChainElement;
import org.qubership.integration.platform.runtime.catalog.service.deployment.properties.ElementPropertiesBuilder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import org.qubership.integration.platform.catalog.consul.ConfigurationPropertiesConstants;
import org.qubership.integration.platform.catalog.model.constant.CamelNames;
import org.qubership.integration.platform.catalog.model.constant.CamelOptions;

@Slf4j
@Component
public class IdempotencyPropertiesBuilder implements ElementPropertiesBuilder {
    @Override
    public boolean applicableTo(ChainElement element) {
        String type = element.getType();
        return CamelNames.HTTP_TRIGGER_COMPONENT.equals(type)
            || CamelNames.RABBITMQ_TRIGGER_2_COMPONENT.equals(type)
            || CamelNames.KAFKA_TRIGGER_2_COMPONENT.equals(type)
            || CamelNames.ASYNC_API_TRIGGER_COMPONENT.equals(type);
    }

    @Override
    public Map<String, String> build(ChainElement element) {
        Map<String, String> properties = new HashMap<>();
        Boolean enableIdempotency = Boolean.valueOf(element.getPropertyAsString(CamelOptions.ENABLE_IDEMPOTENCY_PROP));
        properties.put(ConfigurationPropertiesConstants.IDEMPOTENCY_ENABLED, enableIdempotency.toString());
        if (enableIdempotency) {
            properties.put(ConfigurationPropertiesConstants.EXPIRY, element.getPropertyAsString(CamelOptions.EXPIRY_PROP));
        }
        return properties;
    }
}
