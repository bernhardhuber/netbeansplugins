/*
 * ServiceTypeHelper.java
 *
 * Created on 09. Juni 2005, 10:57
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package org.huberb.digestnb.helper;

import java.security.Provider;
import java.security.Security;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Encapsulate the retrieval of <code>java.secuirty.Provider</code> values.
 *
 * <p>
 * Usually for a JVM 1.5 you can expect following values:
 * </p>
 * <ul>
 * <li>AlgorithmParameterGenerator
 * <li>AlgorithmParameters
 * <li>CertPathBuilder
 * <li>CertPathValidator
 * <li>CertStore
 * <li>CertificateFactory
 * <li>Cipher
 * <li>GssApiMechanism
 * <li>KeyAgreement
 * <li>KeyFactory
 * <li>KeyGenerator
 * <li>KeyManagerFactory
 * <li>KeyPairGenerator
 * <li>KeyStore
 * <li>Mac
 * <li>MessageDigest
 * <li>SSLContext
 * <li>SecretKeyFactory
 * <li>SecureRandom
 * <li>Signature
 * <li>TrustManagerFactory
 * </ul>
 *
 * <p>
 * This class provides a simple method for retrieving all algorithm
 * implementations for the service type <code>MessageDigest</code>.
 *
 * @see #getMessageDigestImpls
 *
 * @author HuberB1
 */
public class ServiceTypeHelper {

    /**
     * Creates a new instance of ServiceTypeHelper
     */
    private ServiceTypeHelper() {
    }
    /*
     */
    final static private String ALG_ALIAS_PREFIX = "Alg.Alias.";

    /**
     * This method returns all available services types.
     *
     * @return String[] the service types supported by this JVM instance.
     */
    public static String[] getServiceTypes() {
        final Set<String> result = new HashSet<>();

        // All all providers
        Provider[] providers = Security.getProviders();
        for (int i = 0; i < providers.length; i++) {
            // Get services provided by each provider
            Set keys = providers[i].keySet();
            for (Iterator it = keys.iterator(); it.hasNext();) {
                String key = (String) it.next();
                key = key.split(" ")[0];
                if (key.startsWith(ALG_ALIAS_PREFIX)) {
                    // Strip the alias
                    key = key.substring(ALG_ALIAS_PREFIX.length());
                }
                int ix = key.indexOf('.');
                result.add(key.substring(0, ix));
            }
        }
        return (String[]) result.toArray(new String[result.size()]);
    }

    /**
     * This method returns the available implementations for a service type.
     *
     * @param serviceType the service type for which implementations are
     * requested
     * @return String[] implementations supporting the requested service type.
     */
    public static String[] getCryptoImpls(String serviceType) {
        Set<String> result = new HashSet<>();

        // All providers
        Provider[] providers = Security.getProviders();
        for (int i = 0; i < providers.length; i++) {
            // Get services provided by each provider
            Set keys = providers[i].keySet();
            for (Iterator it = keys.iterator(); it.hasNext();) {
                String key = (String) it.next();
                key = key.split(" ")[0];

                if (key.startsWith(serviceType + ".")) {
                    result.add(key.substring(serviceType.length() + 1));
                } else if (key.startsWith(ALG_ALIAS_PREFIX + serviceType + ".")) {
                    // This is an alias
                    result.add(key.substring(serviceType.length() + ALG_ALIAS_PREFIX.length() + 1));
                }
            }
        }
        return (String[]) result.toArray(new String[result.size()]);
    }

    /**
     * Retrieve the list of available message digest algorithms.
     * <p>
     * This method requests all implementation for the service type
     * <code>MessageDigest</code>.
     * <p>
     * This requires checking the services provided by all registered providers.
     * The retrieved list of names can be used in creating a MessageDigest
     * object.
     *
     * @param String[] implementations of message digest algorithms, like
     * <code>MD5</code>.
     */
    public static String[] getMessageDigestImpls() {
        final String MESSAGE_DIGEST_SERVICE_TYPE = "MessageDigest";
        final String[] names = getCryptoImpls(MESSAGE_DIGEST_SERVICE_TYPE);
        return names;
    }

    /**
     * Simply retrieve the service types, and dump it to stdout.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        String[] providers;

        providers = getServiceTypes();
        printProviders("ServiceTypes", providers);

        providers = getMessageDigestImpls();
        printProviders("MessageDigestImpls", providers);
    }

    private static void printProviders(String header, String[] providers) {
        System.out.println(header);
        for (int i = 0; i < providers.length; i++) {
            System.out.println(i + ": " + providers[i]);
        }
    }
}
