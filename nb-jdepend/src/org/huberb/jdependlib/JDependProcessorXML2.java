/*
 * JDependProcessorXML2.java
 *
 * Created on 3. March 2006, 15:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.jdependlib;

import java.io.PrintWriter;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.Locale;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import jdepend.framework.JavaClass;
import jdepend.framework.JavaPackage;
import org.openide.util.NbBundle;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * The <code>JDepend</code> class analyzes directories of Java class files,
 * generates metrics for each Java package, and reports the metrics in an XML
 * format.
 * <p>
 * This implementation uses a SAXTransformerFactory for creating XML
 *
 * @see javax.xml.transform.sax.SAXTransformerFactory
 *
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 * @author HuberB1
 */
public class JDependProcessorXML2 extends JDependProcessor {
    private XmlEmitter xmlEmitter;
    
    /**
     * Constructs a <code>JDepend</code> instance using standard output.
     * Creates a new instance of JDependProcessorXML
     */
    public JDependProcessorXML2() {
        this(new PrintWriter(System.out));
    }
    
    /**
     * Constructs a <code>JDepend</code> instance with the specified writer.
     * Creates a new instance of JDependProcessorXML
     *
     *
     * @param writer Writer.
     */
    public JDependProcessorXML2(PrintWriter writer)  {
        super(writer);
        
        formatter = NumberFormat.getInstance(Locale.ENGLISH);
        formatter.setMaximumFractionDigits(2);
        
        try {
            SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
            TransformerHandler hd = tf.newTransformerHandler();
            Transformer serializer = hd.getTransformer();
            serializer.setOutputProperty(OutputKeys.INDENT,"yes");
            
            // use getWriter as transform result destination
            hd.setResult(new StreamResult(this.getWriter()) );
            
            // create the XmlEmitter
            this.xmlEmitter = new XmlEmitter( hd );
        } catch (TransformerConfigurationException tce) {
            throw new XmlEmitterException(tce);
        }
    }
    
    protected void printHeader() {
        xmlEmitter.startDocument();
        xmlEmitter.startElement("JDepend");
    }
    
    protected void printFooter() {
        xmlEmitter.endElement("JDepend");
        xmlEmitter.endDocument();
    }
    
    protected void printPackagesHeader() {
        xmlEmitter.startElement( "Packages");
    }
    
    protected void printPackagesFooter() {
        xmlEmitter.endElement("Packages");
    }
    
    protected void printPackageHeader(JavaPackage jPackage) {
        xmlEmitter.addAttribute( "name", jPackage.getName() );
        xmlEmitter.startElement("Package");
    }
    
    protected void printPackageFooter(JavaPackage jPackage) {
        xmlEmitter.endElement("Package");
    }
    
    protected void printNoStats() {
        final String noStatsMessage = NbBundle.getMessage(JDependProcessorXML2.class,"JDP_NON_STATS");
        xmlEmitter.startElement("error");
        xmlEmitter.characters( noStatsMessage );
        xmlEmitter.endElement( "error" );
    }
    
    protected void printStatistics(JavaPackage jPackage) {
        xmlEmitter.startElement( "Stats");
        
        xmlEmitter.startCharacterEndElement( "TotalClasses", toFormattedString(jPackage.getClassCount()) );
        xmlEmitter.startCharacterEndElement( "ConcreteClasses", toFormattedString(jPackage.getConcreteClassCount()) );
        xmlEmitter.startCharacterEndElement( "AbstractClasses", toFormattedString(jPackage.getAbstractClassCount()) );
        xmlEmitter.startCharacterEndElement( "Ca", toFormattedString(jPackage.afferentCoupling()) );
        xmlEmitter.startCharacterEndElement( "Ce", toFormattedString(jPackage.efferentCoupling()) );
        xmlEmitter.startCharacterEndElement( "A", toFormattedString(jPackage.abstractness()) );
        xmlEmitter.startCharacterEndElement( "I", toFormattedString(jPackage.instability()) );
        xmlEmitter.startCharacterEndElement( "D", toFormattedString(jPackage.distance()) );
        xmlEmitter.startCharacterEndElement( "V", toFormattedString(jPackage.getVolatility()) );
        
        xmlEmitter.endElement("Stats");
    }
    
    protected void printClassName(JavaClass jClass) {
        xmlEmitter.addAttribute( "sourceFile", jClass.getSourceFile() );
        xmlEmitter.startElement( "Class" );
        xmlEmitter.characters( jClass.getName());
        xmlEmitter.endElement( "Class");
    }
    
    protected void printPackageName(JavaPackage jPackage) {
        xmlEmitter.startElement("Package" );
        xmlEmitter.characters( jPackage.getName() );
        xmlEmitter.endElement("Package");
    }
    
    protected void printAbstractClassesHeader() {
        xmlEmitter.startElement( "AbstractClasses");
    }
    
    protected void printAbstractClassesFooter() {
        xmlEmitter.endElement("AbstractClasses");
    }
    
    protected void printConcreteClassesHeader() {
        xmlEmitter.startElement( "ConcreteClasses");
    }
    
    protected void printConcreteClassesFooter() {
        xmlEmitter.endElement("ConcreteClasses");
    }
    
    protected void printEfferentsHeader() {
        xmlEmitter.startElement( "DependsUpon");
    }
    
    protected void printEfferentsFooter() {
        xmlEmitter.endElement("DependsUpon");
    }
    
    protected void printEfferentsError() {
        // do nothing
    }
    
    protected void printAfferentsHeader() {
        xmlEmitter.startElement( "UsedBy");
    }
    
    protected void printAfferentsFooter() {
        xmlEmitter.endElement( "UsedBy");
    }
    
    protected void printAfferentsError() {
        // do nothing
    }
    
    protected void printCyclesHeader() {
        xmlEmitter.startElement( "Cycles");
    }
    
    protected void printCyclesFooter() {
        xmlEmitter.endElement( "Cycles");
    }
    
    protected void printCycleHeader(JavaPackage jPackage) {
        xmlEmitter.addAttribute( "Name", jPackage.getName() );
        xmlEmitter.startElement( "Package" );
    }
    
    protected void printCycleFooter() {
        xmlEmitter.endElement( "Package");
    }
    
    protected void printCycleTarget(JavaPackage jPackage) {
        printCycleContributor(jPackage);
    }
    
    protected void printCycleContributor(JavaPackage jPackage) {
        xmlEmitter.startElement( "Package" );
        xmlEmitter.characters( jPackage.getName() );
        xmlEmitter.endElement( "Package" );
    }
    
    protected void printSummary(Collection packages) {
        // do nothing
    }
    
    /**
     * Simple help method for converting int to String in order to
     * use the XmlEmitter.character method
     */
    private String toFormattedString( int v ) {
        return String.valueOf(v);
    }
    
    /**
     * Wrap SAXException, and other Throwable instances as 
     * RuntimeException.
     * <p>This is neccessary for not-breaking the JDependProcessor method contract
     */
    public static class XmlEmitterException extends RuntimeException {
        public XmlEmitterException(Throwable t) {
            super(t);
        }
    }

    /**
     * An XmlEmitter using a TransformHandler for emitting XML content
     */
    static class XmlEmitter {
        private TransformerHandler hd ;
        private AttributesImpl atts = new AttributesImpl();
        
        public XmlEmitter( TransformerHandler hd ) {
            this.hd = hd;
        }
        void startDocument() throws XmlEmitterException {
            try {
                hd.startDocument();
            } catch (SAXException ex) {
                throw new XmlEmitterException(ex);
            }
        }
        void endDocument() throws XmlEmitterException {
            try {
                hd.endDocument();
            } catch (SAXException ex) {
                throw new XmlEmitterException(ex);
            }
        }
        
        void startElement( String name) throws XmlEmitterException {
            try {
                hd.startElement( "", name, name, atts );
            } catch (SAXException ex) {
                throw new XmlEmitterException(ex);
            }
            atts.clear();
        }
        void endElement( String name ) throws XmlEmitterException {
            try {
                hd.endElement( "", name, name );
            } catch (SAXException ex) {
                throw new XmlEmitterException(ex);
            }
        }
        void characters( String value ) throws XmlEmitterException {
            try {
                hd.characters( value.toCharArray(), 0, value.length() );
            } catch (SAXException ex) {
                throw new XmlEmitterException(ex);
            }
        }
        void addAttribute( String name, String value ) {
            atts.addAttribute( "", name, name, "CDATA", value );
        }
        
        void startCharacterEndElement( String name, String value ) throws XmlEmitterException {
            this.startElement( name );
            this.characters(value);
            this.endElement( name );
        }
    }
}
