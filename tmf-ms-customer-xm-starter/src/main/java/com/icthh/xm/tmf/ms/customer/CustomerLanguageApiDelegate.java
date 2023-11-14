package com.icthh.xm.tmf.ms.customer;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class CustomerLanguageApiDelegate implements SpringCustomerApiDelegate {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CustomerLanguageApiDelegate.class);
    /**
     * RUS is explicitly mapped to Ukrainian language
     */
    private static final Map<String, String> LANG_MAP = Map.of("RUS", "uk", "UKR", "uk", "ENU", "en");
    private final JdbcTemplate jdbcTemplate;

    public CustomerLanguageApiDelegate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ResponseEntity retrieveCustomer(String id, String profile, String fields) {
        String msisdn = id;
        String query = "select LANG from table(XM_CUSTOMER.NCC_UTILS.GET_CALL_PARAM(%s))".formatted(msisdn);
        String originalLang = "UKR";//jdbcTemplate.queryForObject(query, String.class);
        log.info("original lang: {}", originalLang);
        String lang = LANG_MAP.get(originalLang);
        List characteristic = Arrays.asList(Map.of("name", "LANGUAGE", "value", lang));
        return ResponseEntity.ok().body(Map.of("id", msisdn, "characteristic", characteristic));
    }

    @Override
    public String getProfile() {
        return "LANGUAGE";
    }

    @Override
    public String getTenant() {
        return "XM";
    }
}
