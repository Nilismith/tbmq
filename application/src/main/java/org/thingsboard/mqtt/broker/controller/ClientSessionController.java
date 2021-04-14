/**
 * Copyright © 2016-2020 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.mqtt.broker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thingsboard.mqtt.broker.common.data.exception.ThingsboardException;
import org.thingsboard.mqtt.broker.service.mqtt.client.ClientSessionInfo;
import org.thingsboard.mqtt.broker.service.mqtt.client.ClientSessionService;
import org.thingsboard.mqtt.broker.service.mqtt.client.cleanup.ClientSessionCleanUpService;

@RestController
@RequestMapping("/api/client-session")
@RequiredArgsConstructor
public class ClientSessionController extends BaseController {

    private final ClientSessionCleanUpService clientSessionCleanUpService;
    private final ClientSessionService clientSessionService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/{clientId}/clear", method = RequestMethod.DELETE)
    @ResponseBody
    public void clearClientSession(@PathVariable("clientId") String clientId) throws ThingsboardException {
        try {
            clientSessionCleanUpService.removeClientSession(clientId);
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
    @ResponseBody
    public ClientSessionInfo getClientSessionInfo(@PathVariable("clientId") String clientId) throws ThingsboardException {
        try {
            return clientSessionService.getClientSessionInfo(clientId);
        } catch (Exception e) {
            throw handleException(e);
        }
    }

}
