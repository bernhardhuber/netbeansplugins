/*
 * HttpPostForm.java
 *
 * Created on 20. September 2006, 23:12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.httppost.model;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HuberB1
 */
public class HttpPostForm implements Serializable, Cloneable {

    protected final static long serialVersionUID = 20060920231300L;
    private static final Map<String, List<String>> EMPTY = new HashMap<String, List<String>>();

    public static Map<String, List<String>> getEmptyHeaders() {
        return EMPTY;
    }

    /**
     * Holds value of property url.
     */
    private String url;
    /**
     * Holds value of property contentType.
     */
    private String contentType;
    /**
     * Holds value of property method.
     */
    private String method;
    /**
     * Holds value of property requestData.
     */
    private String requestData;
    /**
     * Holds value of property responseData.
     */
    private String responseData;
    private Map<String, List<String>> requestHeaders;
    private Map<String, List<String>> responseHeaders;
    /**
     * Holds value of property boolean.
     */
    private Boolean useCache;
    /**
     * Holds value of property followRedirects.
     */
    private Boolean followRedirects;
    /**
     * Holds value of property startDate.
     */
    private Long startDate;
    /**
     * Holds value of property endDate.
     */
    private Long endDate;
    private Integer responseCode;
    private String responseMessage;

    /**
     * Creates a new instance of HttpPostForm
     */
    public HttpPostForm() {
    }

    /**
     * Getter for property url.
     *
     * @return Value of property url.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Setter for property url.
     *
     * @param url New value of property url.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter for property contentType.
     *
     * @return Value of property contentType.
     */
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Setter for property contentType.
     *
     * @param contentType New value of property contentType.
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Getter for property method.
     *
     * @return Value of property method.
     */
    public String getMethod() {
        return this.method;
    }

    /**
     * Setter for property method.
     *
     * @param method New value of property method.
     */
    public void setMethod(String method) {
        this.method = method;
    }

    public boolean isPostMethod() {
        boolean isPostMethod = false;
        if (this.method != null) {
            isPostMethod = this.method.equalsIgnoreCase("POST");
        }
        return isPostMethod;
    }

    public boolean isGetMethod() {
        boolean isPostMethod = false;
        if (this.method != null) {
            isPostMethod = this.method.equalsIgnoreCase("GET");
        }
        return isPostMethod;
    }
    //---

    /**
     * Getter for property requestData.
     *
     * @return Value of property requestData.
     */
    public String getRequestData() {
        return this.requestData;
    }

    /**
     * Setter for property requestData.
     *
     * @param requestData New value of property requestData.
     */
    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    /**
     * Getter for property responseData.
     *
     * @return Value of property responseData.
     */
    public String getResponseData() {
        return this.responseData;
    }

    /**
     * Setter for property responseData.
     *
     * @param responseData New value of property responseData.
     */
    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public Map<String, List<String>> getRequestHeaders() {
        return this.requestHeaders;
    }

    public void setRequestHeaders(Map<String, List<String>> newRequestHeaders) {
        this.requestHeaders = new HashMap<>();
        for (Map.Entry<String, List<String>> me : newRequestHeaders.entrySet()) {
            final String key = me.getKey() != null ? me.getKey() : "";
            final List<String> l = me.getValue();

            this.requestHeaders.put(key, new ArrayList<String>(l));
        }
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.responseHeaders;
    }

    public void setResponseHeaders(Map<String, List<String>> newResponseHeaders) {
        this.responseHeaders = new HashMap<>();
        for (Map.Entry<String, List<String>> me : newResponseHeaders.entrySet()) {
            final String key = me.getKey() != null ? me.getKey() : "";
            final List<String> l = me.getValue();

            this.responseHeaders.put(key, new ArrayList<>(l));
        }
    }

    /**
     * Getter for property boolean.
     *
     * @return Value of property boolean.
     */
    public Boolean getUseCache() {
        return this.useCache;
    }

    /**
     * Setter for property boolean.
     *
     * @param boolean New value of property boolean.
     */
    public void setUseCache(Boolean useCache) {
        this.useCache = useCache;
    }

    /**
     * Getter for property followRedirects.
     *
     * @return Value of property followRedirects.
     */
    public Boolean getFollowRedirects() {
        return this.followRedirects;
    }

    /**
     * Setter for property followRedirects.
     *
     * @param followRedirects New value of property followRedirects.
     */
    public void setFollowRedirects(Boolean followRedirects) {
        this.followRedirects = followRedirects;
    }

    //---
    public HttpURLConnection createHttpURLConnection() throws MalformedURLException, IOException {
        final String sURL = this.getUrl();

        final HttpURLConnection connection = (HttpURLConnection) new URL(sURL).openConnection();
        connection.setFollowRedirects(this.getFollowRedirects());

        connection.setRequestMethod(this.getMethod());
        connection.setUseCaches(this.getUseCache());

        if (this.isPostMethod()) {
            connection.setDoOutput(true);
        }
        connection.setDoInput(true);

        if (this.isPostMethod()) {
            final String contentType = this.getContentType();
            connection.setRequestProperty("Content-type", contentType);
        }

        return connection;
    }

    public void clearAllResponseData() {
        this.responseCode = 0;
        this.responseMessage = "";
        this.responseData = "";
        this.responseHeaders = EMPTY;
        this.startDate = null;
        this.endDate = null;
    }

    /**
     * Getter for property startDate.
     *
     * @return Value of property startDate.
     */
    public Long getStartDate() {
        return this.startDate;
    }

    /**
     * Setter for property startDate.
     *
     * @param startDate New value of property startDate.
     */
    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter for property endDate.
     *
     * @return Value of property endDate.
     */
    public Long getEndDate() {
        return this.endDate;
    }

    /**
     * Setter for property endDate.
     *
     * @param endDate New value of property endDate.
     */
    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Date getStartDateAsDate() {
        return new Date(this.getStartDate());
    }

    public Date getEndDateAsDate() {
        return new Date(this.getEndDate());
    }

    public Long getDuration() {
        Long duration = null;

        if (this.endDate != null && this.startDate != null) {
            duration = this.endDate - this.startDate;
        }
        return duration;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        HttpPostForm retValue;

        retValue = (HttpPostForm) super.clone();
        return retValue;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Integer getResponseCode() {
        return this.responseCode;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public String getFileName() {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(this.getStartDate()));
        sb.append("-");
        sb.append(String.valueOf(this.getEndDate()));
        sb.append(".xml");
        return sb.toString();
    }

}
