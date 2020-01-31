package com.icthh.xm.tmf.ms.customer.domain.properties;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.annotation.Nullable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"customer"})
public class CustomerCharacteristics {

    @JsonProperty("characteristics")
    private List<Characteristic> characteristics;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({"key", "defaultValue", "regexp", "max", "min", "predefinedValues"})
    public static class Characteristic {

        @JsonProperty("key")
        private String key;

        @Nullable
        @JsonProperty("defaultValue")
        private String defaultValue;

        @Nullable
        @JsonProperty("regexp")
        private String regexp;

        @Nullable
        @JsonProperty("min")
        private Integer min;

        @Nullable
        @JsonProperty("max")
        private Integer max;

        @Nullable
        @JsonProperty("predefinedValues")
        private List<String> predefinedValues;
    }
}
