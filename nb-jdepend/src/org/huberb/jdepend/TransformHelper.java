/*
 * TransformHelper.java
 *
 * Created on 13. August 2006, 18:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.jdepend;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.TransformerException;
import org.huberb.jdepend.misc.*;
import org.huberb.jdepend.options.JDependSettings;

import org.openide.ErrorManager;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.Repository;

import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

/**
 * Encapsulate transforming jdepend xml to html using
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
     * @param result the xml content
     * @return String the transformed html content
     */
    public String transformResult(String result) {
        dumpResultToOutput( result );
        
        String html = "";
        
        final InputStream xsltIs = getXsltInputStreamFromRepository();
        final TransformProcessor tp = new TransformProcessor();
        try {
            tp.loadXslt( xsltIs );
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
        final String xsltResourceName = "org/huberb/jdepend/misc/jdependxml2html.xsl";
        
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
        
        FileObject fo = JDependSettings.getDefault().getXsltFileObject();
        if (fo == null) {
            final Repository repo = Repository.getDefault();
            final String standardResourceName = ConstantsHelper.getStandardXsltFileObjectName();
            fo = repo.getDefaultFileSystem().findResource(standardResourceName);
        }
        return fo;
    }
    
    /**
     * Dump text to the output tab "jdepend"
     *
     * @param sw the text to be dumped
     */
    protected void dumpResultToOutput(String sw) {
        final InputOutput io = IOProvider.getDefault().getIO("jdepend", false);
        final OutputWriter ow = io.getOut();
        ow.print( sw );
    }
}
