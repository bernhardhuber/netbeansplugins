/*
 * PersistanceTest.java
 * JUnit based test
 *
 * Created on 08. Oktober 2006, 21:36
 */
package org.huberb.httppost.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import junit.framework.*;
import java.util.List;
import java.util.Map;
import org.junit.Before;

/**
 *
 * @author HuberB1
 */
public class PersistanceTest {

    private Persistance instance;

    @Before
    public void setUp() throws Exception {
        this.instance = new Persistance();
    }

    /**
     * Test of getSaveFolder method, of {@code Persistance}.
     */
    public void testGetSaveFolder() {
    }

    /**
     * Test of getFile method, of {@code Persistance}.
     */
    public void testGetFile() {
    }

    /**
     * Test of save method, of {@code Persistance}.
     */
    public void testSave() {
        final HttpPostForm hpf = createHttpPostForm();
        this.instance.save(hpf);
    }

    /**
     * Test of delete method, of {@code Persistance}.
     */
    public void testDelete() {
    }

    /**
     * Test of load method, of {@code Persistance}.
     */
    public void testLoad() {
    }

    protected HttpPostForm createHttpPostForm() {
        HttpPostForm hpf = new HttpPostForm();

        hpf.setContentType("text/xml");
        hpf.setEndDate(2L);
        hpf.setFollowRedirects(Boolean.TRUE);
        hpf.setMethod("POST");
        hpf.setRequestData("requestData");
        Map<String, List<String>> requestHeaders = new HashMap<>();
        //requestHeaders.put( "User-Agent", Arrays.asList( "TestBrowser" ) );
        List<String> l1 = new ArrayList<>();
        l1.add("TestBrowser");
        requestHeaders.put("User-Agent", l1);
        hpf.setRequestHeaders(requestHeaders);
        hpf.setResponseCode(200);
        hpf.setResponseData("responseData");
        Map<String, List<String>> responseHeaders = new HashMap<>();
        responseHeaders.put("Server", Arrays.asList("TestServer"));
        hpf.setResponseHeaders(responseHeaders);
        hpf.setResponseMessage("OK");
        hpf.setStartDate(1L);
        hpf.setUseCache(Boolean.TRUE);
        hpf.setUrl("http://some.url/");

        return hpf;
    }
}
