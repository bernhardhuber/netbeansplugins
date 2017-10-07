/*
 * HttpPostProcessor.java
 *
 * Created on 21. September 2006, 09:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.httppost.actions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import org.huberb.httppost.model.HttpPostForm;
import org.huberb.httppost.util.CSVHelper;
import org.openide.ErrorManager;

/**
 * A processor for posting to an http url
 *
 * @author HuberB1
 */
public class HttpPostProcessor implements Serializable {

    protected final static long serialVersionUID = 20060921094000L;

    /**
     * Creates a new instance of HttpPostProcessor
     */
    public HttpPostProcessor() {
    }

    /**
     * Post the httpPostForm
     *
     * @param httpPostForm the post details
     */
    public void post(HttpPostForm httpPostForm) {

        final String request = httpPostForm.getRequestData();

        String responseAsString = null;
        byte data[] = request.getBytes();

        try {
            final HttpURLConnection connection = httpPostForm.createHttpURLConnection();
            final Map<String, List<String>> requestHeaders = httpPostForm.getRequestHeaders();
            if (requestHeaders != null) {
                //----
                for (Map.Entry<String, List<String>> me : requestHeaders.entrySet()) {
                    final String requestHeaderKey = me.getKey();
                    final String requestHeaderValue = new CSVHelper().values(me.getValue()).build().toString();
                    if (requestHeaderKey != null
                            && requestHeaderKey.length() > 0
                            && requestHeaderValue != null
                            && requestHeaderValue.length() > 0) {
                        connection.setRequestProperty(requestHeaderKey, requestHeaderValue);
                    }
                }
            }

            if (httpPostForm.isPostMethod()) {
                try (OutputStream osOut = connection.getOutputStream()) {
                    osOut.write(data);
                }
            }

            int responseCode = connection.getResponseCode();
            httpPostForm.setResponseCode(responseCode);

            String responseMessage = connection.getResponseMessage();
            httpPostForm.setResponseMessage(responseMessage);

            final Map<String, List<String>> headerFields = connection.getHeaderFields();
            httpPostForm.setResponseHeaders(headerFields);

            try (InputStream isIn = connection.getInputStream()) {
                final byte response[] = readData(isIn);
                responseAsString = new String(response);
                httpPostForm.setResponseData(responseAsString);
            } catch (IOException ioex) {
                InputStream isErr = connection.getErrorStream();
                if (isErr != null) {
                    try {
                        final byte response[] = readData(isErr);
                        responseAsString = new String(response);
                        httpPostForm.setResponseData(responseAsString);
                    } finally {
                        isErr.close();
                    }
                }
            }
            connection.disconnect();
        } catch (IOException ioex) {
            ErrorManager.getDefault().notify(ioex);
        }
    }

    /**
     * read data, ie response data
     *
     * @param isIn the input stream to read from
     * @return byte[] the input stream data as byte[]
     */
    private byte[] readData(InputStream isIn) throws IOException {
        byte[] bData = null;
        byte[] bDataBlock = new byte[4096];
        int total = 0;
        int received = 0;
        while ((received = isIn.read(bDataBlock)) != -1) {
            if (total != 0 || bData == null) {
                final byte[] bOldData = bData;
                bData = new byte[total + received];
                if (total != 0) {
                    System.arraycopy(bOldData, 0, bData, 0, total);
                }
            }
            System.arraycopy(bDataBlock, 0, bData, total, received);
            total += received;
        }
        return bData;
    }
}
