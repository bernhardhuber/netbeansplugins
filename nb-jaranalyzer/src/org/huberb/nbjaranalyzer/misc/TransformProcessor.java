/*
 * Transformer.java
 *
 * Created on 27. April 2006, 22:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbjaranalyzer.misc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Iterator;
import java.util.Map;
import javax.xml.transform.Result;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * A simple wrapper class for transforming an xml document using XSLT.
 *
 * @author HuberB1
 */
public class TransformProcessor {
    private Transformer transformer;
    
    /** Creates a new instance of Transformer */
    public TransformProcessor() {
    }
    
    public void initTransformer() throws TransformerConfigurationException {
        if (transformer == null) {
            // Use a Transformer for output
            final TransformerFactory tFactory = TransformerFactory.newInstance();
            this.transformer = tFactory.newTransformer();       
        }
    }
    
    /**
     * Init, and setup the xslt transformer
     *
     * @param xsltResource the xslt resource
     */
    public void initTransformer( InputStream is) throws TransformerConfigurationException {
        // Use a Transformer for output
        final TransformerFactory tFactory = TransformerFactory.newInstance();
        
        try {
            final Source xslSource = new StreamSource( is );
            this.transformer = tFactory.newTransformer(xslSource);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    throw new TransformerConfigurationException( "Cannot close xsl input stream", ex );
                }
            }
        }
    }
    
    /**
     * Set xslt parameter
     *
     * @param params the parameters which will set as xslt parameters
     */
    public void setParameters( Map params ) {
        for (Iterator i = params.entrySet().iterator(); i.hasNext();) {
            Map.Entry me = (Map.Entry)i.next();
            String name = (String)me.getKey();
            Object value = me.getValue();
            
            transformer.setParameter( name, value );
        }
    }
    
    /**
     * Transform the xml.
     *
     * @param processedResponse the original xml document
     * @param os the transformed xml document
     */
    public void transform(String processedResponse, OutputStream os) throws TransformerException {
        final Source xmlSource = new StreamSource( new StringReader(processedResponse));
        final StreamResult outputTarget = new StreamResult(os);
        
        transform( xmlSource, outputTarget );
    }
    
    /**
     * Transform the xml.
     *
     * @param processedResponse the original xml document
     * @param os the transformed xml document
     */
    public void transform( InputStream is, OutputStream os ) throws TransformerException {
        final Source xmlSource = new StreamSource( is );
        final StreamResult outputTarget = new StreamResult(os);
        
        transform( xmlSource, outputTarget );
    }
    
    public void transform( Source source, Result result ) throws TransformerException {
        transformer.transform( source, result );
    }
}
