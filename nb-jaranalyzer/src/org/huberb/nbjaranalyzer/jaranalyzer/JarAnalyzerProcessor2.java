/*
 * JarAnalyzer.java
 *
 * Created on 16. Februar 2007, 20:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbjaranalyzer.jaranalyzer;

import com.kirkk.analyzer.framework.Jar;
import com.kirkk.analyzer.framework.JarMetrics;
import com.kirkk.analyzer.framework.JarPackage;
import com.kirkk.analyzer.framework.bcelbundle.JarRelationshipDecorator;
import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import org.huberb.nbjaranalyzer.options.NbjaranalyzerSettings;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Encapsulate analyzing jars using JarAnalyzer
 * <p>This implementation uses a safe SAXTransformerFactory for emitting xml.
 *
 * @author HuberB1
 */
public class JarAnalyzerProcessor2 implements JarAnalyzerProcessorIF {
    private PrintWriter writer;
    
    /** Creates a new instance of JarAnalyzer */
    public JarAnalyzerProcessor2() {
        this(new PrintWriter(System.out));
    }
    public JarAnalyzerProcessor2(PrintWriter pw ) {
        this.writer = pw;        
    }
    
    public void createSummary(File srcDir) throws Exception {
        final List<String> packageFilterList = NbjaranalyzerSettings.getDefault().getFilterPackagesAsList();
        final List<String> jarFilterList = NbjaranalyzerSettings.getDefault().getFilterJarsAsList();
        
        JarCollectionCollector jci = new JarCollectionCollector(srcDir, packageFilterList, jarFilterList);
        JarRelationshipDecorator jarrelationshipdecorator = new JarRelationshipDecorator(jci);
        Jar ajar[] = jarrelationshipdecorator.toArray();
        outputAll(ajar);
    }
    public void createSummary(List<File> jars) throws Exception {
        final List<String> packageFilterList = NbjaranalyzerSettings.getDefault().getFilterPackagesAsList();
        final List<String> jarFilterList = NbjaranalyzerSettings.getDefault().getFilterJarsAsList();
        
        JarCollectionCollector jci = new JarCollectionCollector( jars, packageFilterList, jarFilterList );
        JarRelationshipDecorator jarrelationshipdecorator = new JarRelationshipDecorator(jci);
        Jar ajar[] = jarrelationshipdecorator.toArray();
        outputAll(ajar);
    }
    
    private void outputAll(Jar ajar[]) throws Exception {
        SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        TransformerHandler hd = tf.newTransformerHandler();
        Transformer serializer = hd.getTransformer();
        serializer.setOutputProperty(OutputKeys.INDENT,"yes");
        hd.setResult(new StreamResult(this.writer));
        
        XmlEmitter xmlEmitter = new XmlEmitter( hd, ajar );
        xmlEmitter.emit();
        
        this.writer.flush();
        this.writer.close();
    }
    
    static class XmlEmitter {
        private TransformerHandler hd ;
        private AttributesImpl atts = new AttributesImpl();
        private Jar ajar[];
        
        public XmlEmitter( TransformerHandler hd, Jar ajar[] ) {
            this.hd = hd;
            this.ajar = ajar;
        }
        public void emit() throws SAXException {
            printHeader();
            output(ajar);
            printFooter();
        }
        private void _se( String name) throws SAXException {
            hd.startElement( "", name, name, atts );
            atts.clear();
        }
        private void _ee( String name ) throws SAXException {
            hd.endElement( "", name, name );
        }
        private void _cc( Object obj ) throws SAXException {
            String value = String.valueOf(obj);
            hd.characters( value.toCharArray(), 0, value.length() );
        }
        
        private void printHeader() throws SAXException {
            hd.startDocument();
            _se( "JarAnalyzer" );
        }
        
        private void printFooter() throws SAXException {
            _ee( "JarAnalyzer" );
            hd.endDocument();
        }
        
        private void output(Jar ajar[]) throws SAXException {
            _se( "Jars" );
            
            for(int i = 0; i < ajar.length; i++) {
                String s = ajar[i].getJarFileName().substring(ajar[i].getJarFileName().lastIndexOf("\\") + 1, ajar[i].getJarFileName().length());
                atts.addAttribute( "", "name", "name", "CDATA", s );
                _se( "Jar" );
                _se( "Summary");
                statistics(ajar[i]);
                //
                metrics(ajar[i].calculateMetrics());
                //
                jarPackages(ajar[i]);
                //
                outgoingDependencies(ajar[i]);
                incomingDependencies(ajar[i]);
                cycles(ajar[i]);
                unresolveableDependencies(ajar[i]);
                _ee( "Summary");
                _ee( "Jar");
            }
            _ee( "Jars" );
        }
        
        private void statistics(Jar jar) throws SAXException {
            _se( "Statistics");
            _se( "ClassCount" ); _cc( jar.getClassCount() ); _ee( "ClassCount");
            _se( "AbstractClassCount" ); _cc( jar.getAbstractClassCount() ); _ee( "AbstractClassCount");
            _se( "PackageCount" ); _cc( jar.getPackageCount() ); _ee( "PackageCount" );
            _ee( "Statistics");
        }
        
        private void metrics(JarMetrics jarmetrics) throws SAXException {
            _se("Metrics");
            _se("Abstractness" ); _cc( jarmetrics.calculateAbstractness().toString() ); _ee("Abstractness");
            _se("Efferent" ); _cc(jarmetrics.calculateEfferentCoupling() ); _ee("Efferent");
            _se("Afferent"); _cc(jarmetrics.calculateAfferentCoupling() ); _ee("Afferent");
            _se("Instability"); _cc(jarmetrics.calculateInstability().toString() ); _ee("Instability");
            _se("Distance" ); _cc(jarmetrics.calculateDistance().toString() ); _ee("Distance");
            _ee("Metrics");
        }
        
        private void cycles(Jar jar) throws SAXException {
            _se( "Cycles");
            if(jar.hasCycles()) {
                for(Iterator iterator = jar.getCyclicJars().iterator(); iterator.hasNext(); ) {
                    final Jar jar1 = (Jar)iterator.next();
                    final String s = jar1.getJarFileName();
                    _se( "Cycle" );
                    _cc( s );
                    _ee( "Cycle");
                }
                
            }
            _ee( "Cycles" );
        }
        
        private void outgoingDependencies(Jar jar) throws SAXException {
            _se("OutgoingDependencies");
            for(Iterator iterator = jar.getOutgoingDependencies().iterator(); iterator.hasNext(); ) {
                final Jar jar1 = (Jar)iterator.next();
                final String s = jar1.getJarFileName();
                _se("Jar" );
                _cc( s );
                _ee( "Jar" );
            }
            _ee("OutgoingDependencies");
        }
        
        private void incomingDependencies(Jar jar) throws SAXException {
            _se("IncomingDependencies");
            for(Iterator iterator = jar.getIncomingDependencies().iterator(); iterator.hasNext(); ) {
                final Jar jar1 = (Jar)iterator.next();
                final String s = jar1.getJarFileName();
                _se("Jar");
                _cc( s );
                _ee("Jar");
            }
            
            _ee("IncomingDependencies");
        }
        
//    private void externalDependencies(Jar jar) {
//        String s;
//        for(Iterator iterator = jar.getAllExternallyReferencedPackages().iterator(); iterator.hasNext(); writer.println(tab(5) + "<Package>" + s + "</Package>"))
//            s = (String)iterator.next();
//
//    }
        
        private void unresolveableDependencies(Jar jar) throws SAXException {
            _se("UnresolvedDependencies");
            for(Iterator iterator = jar.getAllUnidentifiableExternallyReferencedPackages().iterator(); iterator.hasNext(); ) {
                final String s = (String)iterator.next();
                _se( "Package" );
                _cc( s );
                _ee( "Package") ;
            }
            _ee("UnresolvedDependencies");
        }
        
        private void jarPackages(Jar jar) throws SAXException {
            _se("Packages");
            for(Iterator iterator = jar.getAllContainedPackages().iterator(); iterator.hasNext(); ) {
                final JarPackage jarpackage = (JarPackage)iterator.next();
                _se( "Package" );
                _cc( jarpackage.getLongName() );
                _ee( "Package" );
            }
            _ee("Packages");
        }
        
    }
}
