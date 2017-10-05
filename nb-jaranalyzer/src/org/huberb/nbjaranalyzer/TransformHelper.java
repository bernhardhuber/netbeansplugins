/*
 * TransformHelper.java
 *
 * Created on 13. August 2006, 18:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbjaranalyzer;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.TransformerException;
import org.huberb.nbjaranalyzer.misc.ConstantsHelper;
import org.huberb.nbjaranalyzer.misc.TransformProcessor;
import org.huberb.nbjaranalyzer.options.NbjaranalyzerSettings;

import org.openide.ErrorManager;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.Repository;

import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

/**
 * Encapsulate transforming nbjaranalyzer xml to html using
 * xslt.
 *
 * @author HuberB1
 */
public class TransformHelper {
    
    /** Creates a new instance of TransformHelper */
    public TransformHelper() {
    }
    
    /**
     * Transform xml content to html
     *
     * @param sw the xml content
     * @return String the transformed html content
     */
    public String transformResult(String result) {
        dumpResultToOutput( result );
        
        String html = "";
        
        final InputStream xsltIs = getXsltInputStreamFromRepository();
        //final InputStream xsltIs = getXsltInputStreamFromClasspath();
        final TransformProcessor tp = new TransformProcessor();
        try {
            tp.initTransformer( xsltIs );
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            tp.transform( result, baos );
            
            html = baos.toString();
            
        }  catch (TransformerException ex) {
            ErrorManager.getDefault().log(ErrorManager.WARNING, "Cannot create html content" );
        } finally {
            if (xsltIs != null) {
                try {
                    xsltIs.close();
                } catch (IOException ex) {
                    // ignore failing to close
                }
            }
        }
        
        return html;
    }
    
    /**
     * Get xslt input stream from classpath
     *
     * @return InputStream of xslt, or null if xslt resource is not available
     */
    protected InputStream getXsltInputStreamFromClasspath() {
        final String xsltResourceName = "org/huberb/nbjaranalyzer/resources/jaranalyzer_default.xsl";
        
        final InputStream xsltIs = this.getClass().getClassLoader().getResourceAsStream(xsltResourceName);
        return xsltIs;
    }
    
    /**
     * Get xslt input stream from netbeans repository
     *
     * @return InputStream of xslt, or null if xslt resource is not available
     */
    protected InputStream getXsltInputStreamFromRepository() {
        final FileObject fo = getXsltFileObject();
        InputStream is;
        try {
            is = fo.getInputStream();
        } catch (FileNotFoundException ex) {
            ErrorManager.getDefault().notify( ex );
            is = null;
        }
        return is;
    }
    
    protected FileObject getXsltFileObject() {
       
        FileObject fo = NbjaranalyzerSettings.getDefault().getXsltFileObject();
        if (fo == null) {
            final Repository repo = Repository.getDefault();
            final String standardResourceName = ConstantsHelper.getStandardXsltFileObjectName();
            fo = repo.getDefaultFileSystem().findResource(standardResourceName);
        }
        return fo;
    }
    
    /**
     * Dump text to the output tab "jaranalyzer"
     *
     * @param sw the text to be dumped
     */
    protected void dumpResultToOutput(String sw) {
        final InputOutput io = IOProvider.getDefault().getIO("jaranalyzer", false);
        final OutputWriter ow = io.getOut();
        ow.print( sw );
    }
}
