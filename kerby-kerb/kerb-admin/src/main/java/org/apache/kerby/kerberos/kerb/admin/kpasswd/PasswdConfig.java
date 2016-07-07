/**
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *
 */
package org.apache.kerby.kerberos.kerb.admin.kpasswd;

import org.apache.kerby.kerberos.kerb.common.Krb5Conf;

/**
 * Kerb client side configuration API.
 */
public class PasswdConfig extends Krb5Conf {
    private static final String LIBDEFAULT = "libdefaults";

    public boolean enableDebug() {
        return getBoolean(PasswdConfigKey.KRB_DEBUG, true, LIBDEFAULT);
    }

    /**
     * Get KDC host name
     *
     * @return The kdc host
     */
    public String getAdminHost() {
        return getString(
            PasswdConfigKey.ADMIN_HOST, true, LIBDEFAULT);
    }

    /**
     * Get KDC port, as both TCP and UDP ports
     *
     * @return The kdc host
     */
    public int getAdminPort() {
        Integer kdcPort = getInt(PasswdConfigKey.ADMIN_PORT, true, LIBDEFAULT);
        if (kdcPort != null) {
            return kdcPort.intValue();
        }
        return -1;
    }

    /**
     * Get KDC TCP port
     *
     * @return The kdc tcp port
     */
    public int getAdminTcpPort() {
        Integer kdcPort = getInt(PasswdConfigKey.ADMIN_TCP_PORT, true, LIBDEFAULT);
        if (kdcPort != null && kdcPort > 0) {
            return kdcPort.intValue();
        }
        return getAdminPort();
    }

    /**
     * Is to allow UDP for KDC
     *
     * @return true to allow UDP, false otherwise
     */
    public boolean allowUdp() {
        return getBoolean(PasswdConfigKey.ADMIN_ALLOW_UDP, true, LIBDEFAULT)
                || getInt(PasswdConfigKey.ADMIN_UDP_PORT, true, LIBDEFAULT) != null
            || getInt(PasswdConfigKey.ADMIN_PORT, false, LIBDEFAULT) != null;
    }

    /**
     * Is to allow TCP for KDC
     *
     * @return true to allow TCP, false otherwise
     */
    public boolean allowTcp() {
        return getBoolean(PasswdConfigKey.ADMIN_ALLOW_TCP, true, LIBDEFAULT)
                || getInt(PasswdConfigKey.ADMIN_TCP_PORT, true, LIBDEFAULT) != null
            || getInt(PasswdConfigKey.ADMIN_PORT, false, LIBDEFAULT) != null;
    }

    /**
     * Get KDC UDP port
     *
     * @return The kdc udp port
     */
    public int getAdminUdpPort() {
        Integer kdcPort = getInt(PasswdConfigKey.ADMIN_UDP_PORT, true, LIBDEFAULT);
        if (kdcPort != null && kdcPort > 0) {
            return kdcPort.intValue();
        }
        return getAdminPort();
    }

    /**
     * Get KDC realm.
     * @return The kdc realm
     */
    public String getAdminRealm() {
        String realm = getString(PasswdConfigKey.ADMIN_REALM, false, LIBDEFAULT);
        if (realm == null) {
            realm = getString(PasswdConfigKey.DEFAULT_REALM, false, LIBDEFAULT);
            if (realm == null) {
                realm = (String) PasswdConfigKey.ADMIN_REALM.getDefaultValue();
            }
        }

        return realm;
    }
}