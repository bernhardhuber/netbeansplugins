/*
 * CryptographicServices.java
 *
 * Created on 27. Februar 2006, 12:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.model;

import java.security.Provider;
import java.security.Security;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Encapsulate accessing cryptographic services available in the JVM.
 *
 * @author HuberB1
 */
public class CryptographicServices {
    
    /** Creates a new instance of CryptographicServices */
    private CryptographicServices() {
    }
    
    /**
     * This method returns all available security services types
     */
    public static String[] getServiceTypes() {
        Set result = new HashSet();
        
        // All all providers
        Provider[] providers = Security.getProviders();
        for (int i=0; i<providers.length; i++) {
            // Get services provided by each provider
            Set keys = providers[i].keySet();
            for (Iterator it=keys.iterator(); it.hasNext(); ) {
                String key = (String)it.next();
                key = key.split(" ")[0];
                
                if (key.startsWith("Alg.Alias.")) {
                    // Strip the alias
                    key = key.substring(10);
                }
                // extract only serviceType from : serviceType.xxxx
                int ix = key.indexOf('.');
                if (ix > 0) {
                    result.add(key.substring(0, ix));
                }
            }
        }
        return (String[])result.toArray(new String[result.size()]);
    }
    /**
     * This method returns the available security implementations for a service type
     */
    public static String[] getCryptoImpls(String serviceType, boolean addAlias) {
        final Set result = new HashSet();
        
        // All all providers
        final Provider[] providers = Security.getProviders();
        for (int i=0; i<providers.length; i++) {
            // Get services provided by each provider
            final Set keys = providers[i].keySet();
            for (Iterator it=keys.iterator(); it.hasNext(); ) {
                String key = (String)it.next();
                key = key.split(" ")[0];
                
                if (key.startsWith(serviceType+".")) {
                    result.add(key.substring(serviceType.length()+1));
                } else if (addAlias && key.startsWith("Alg.Alias."+serviceType+".")) {
                    // This is an alias
                    result.add(key.substring(serviceType.length()+11));
                }
            }
        }
        return (String[])result.toArray(new String[result.size()]);
    }
}
