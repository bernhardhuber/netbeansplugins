/*
 * CryptographicServices.java
 *
 * Created on 27. Februar 2006, 12:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.cryptoservices.helper;

import java.security.Provider;
import java.security.Security;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author HuberB1
 */
public class CryptographicServices {

    /**
     * Creates a new instance of CryptographicServices
     */
    public CryptographicServices() {
    }

    /**
     * This method returns all available services types
     */
    public static String[] getServiceTypes() {
        final Set<String> result = new HashSet<>();

        // All all providers
        final Provider[] providers = Security.getProviders();
        for (int i = 0; i < providers.length; i++) {
            // Get services provided by each provider
            final Set keys = providers[i].keySet();
            for (Iterator it = keys.iterator(); it.hasNext();) {
                String key = (String) it.next();
                key = key.split(" ")[0];

                if (key.startsWith("Alg.Alias.")) {
                    // Strip the alias
                    key = key.substring(10);
                }
                int ix = key.indexOf('.');
                result.add(key.substring(0, ix));
            }
        }
        return result.toArray(new String[result.size()]);
    }

    /**
     * This method returns the available implementations for a service type
     */
    public static String[] getCryptoImpls(String serviceType) {
        final Set<String> result = new HashSet<>();

        // All all providers
        final Provider[] providers = Security.getProviders();
        for (int i = 0; i < providers.length; i++) {
            // Get services provided by each provider
            Set keys = providers[i].keySet();
            for (Iterator it = keys.iterator(); it.hasNext();) {
                String key = (String) it.next();
                key = key.split(" ")[0];

                if (key.startsWith(serviceType + ".")) {
                    result.add(key.substring(serviceType.length() + 1));
                } else if (key.startsWith("Alg.Alias." + serviceType + ".")) {
                    // This is an alias
                    result.add(key.substring(serviceType.length() + 11));
                }
            }
        }
        return result.toArray(new String[result.size()]);
    }
}
