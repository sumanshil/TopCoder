/*
 * Copyright (c) 2018 VMware, Inc. All rights reserved. VMware Confidential
 */

package com.vmware;

import java.util.List;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComponentStatus {

    @JsonProperty("connection_status")
    private String connectionStatus;

    @JsonProperty("registration_status")
    private String registrationStatus;

    @JsonProperty("registration_errors")
    private List<RegistrationError> registrationErrors;

    @JsonProperty("connection_errors")
    private List<RegistrationError> connectionErrors;

    @Data
    public static class RegistrationError {
        @JsonProperty("error_message")
        private String errorMessage;

        private String timestamp;
    }
}
