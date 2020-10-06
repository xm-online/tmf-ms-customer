package com.icthh.xm.tmf.ms.customer.domain.properties;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import javax.annotation.Nullable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"customer"})
@Accessors(chain = true)
public class CustomerCharacteristics {

    @JsonProperty("characteristics")
    private List<Characteristic> characteristics;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({"key", "defaultValue", "regexp", "max", "min", "predefinedValues"})
    @Accessors(chain = true)
    public static class Characteristic {

        @JsonProperty("keyRegexp")
        @Nullable
        private String keyRegexp;

        @Nullable
        @JsonProperty("defaultValue")
        private String defaultValue;

        @Nullable
        @JsonProperty("regexp")
        private String regexp;

        @Nullable
        @JsonProperty("min")
        private Integer minLength;

        @Nullable
        @JsonProperty("max")
        private Integer maxLength;

        @Nullable
        @JsonProperty("predefinedValues")
        private List<String> predefinedValues;
    }
}
