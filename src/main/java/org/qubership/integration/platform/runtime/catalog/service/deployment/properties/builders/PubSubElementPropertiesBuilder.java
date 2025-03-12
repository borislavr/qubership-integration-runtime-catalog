package org.qubership.integration.platform.runtime.catalog.service.deployment.properties.builders;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.qubership.integration.platform.catalog.model.constant.CamelNames;
import org.qubership.integration.platform.catalog.persistence.configs.entity.chain.element.ChainElement;
import org.qubership.integration.platform.runtime.catalog.service.deployment.properties.ElementPropertiesBuilder;
import org.springframework.stereotype.Component;

@Component
public class PubSubElementPropertiesBuilder implements ElementPropertiesBuilder {
    public static final String PUBSUB_PROJECT_ID = "projectId";
    public static final String PUBSUB_DESTINATION_NAME = "destinationName";

    @Override
    public boolean applicableTo(ChainElement element) {
        return Set.of(
                CamelNames.PUBSUB_TRIGGER_COMPONENT,
                CamelNames.PUBSUB_SENDER_COMPONENT
        ).contains(element.getType());
    }

    @Override
    public Map<String, String> build(ChainElement element) {
        return Stream.of(
            PUBSUB_PROJECT_ID,
            PUBSUB_DESTINATION_NAME
        ).collect(Collectors.toMap(Function.identity(), element::getPropertyAsString));
    }

}
