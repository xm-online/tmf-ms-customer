package com.icthh.xm.ms.mstemplate.web.rest.errors;

import com.icthh.xm.commons.exceptions.BusinessException;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

// TODO replace by BusinessException
public class BadRequestAlertException extends BusinessException {

    private static final long serialVersionUID = 1L;

    private final String entityName;

    private final String errorKey;

    public BadRequestAlertException(String defaultMessage, String entityName, String errorKey) {
        this(ErrorConstants.DEFAULT_TYPE, defaultMessage, entityName, errorKey);
    }

    public BadRequestAlertException(URI type, String defaultMessage, String entityName, String errorKey) {
        super(type.toString(), defaultMessage, getAlertParameters(entityName, errorKey));
        this.entityName = entityName;
        this.errorKey = errorKey;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getErrorKey() {
        return errorKey;
    }

    private static Map<String, String> getAlertParameters(String entityName, String errorKey) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("message", "error." + errorKey);
        parameters.put("params", entityName);
        return parameters;
    }
}
