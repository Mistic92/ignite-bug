package pl.gdgrzeszow.k8s;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class FoodResponse {

    private String foodType;
    @Builder.Default
    private OffsetDateTime time = OffsetDateTime.now();
    private String remoteAddr;
    private String forwardHeader;
}

