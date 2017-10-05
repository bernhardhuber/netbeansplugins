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
import org.huberb.nbjaranalyzer.options.NbjaranalyzerSettings;

/**
 * Encapsulate analyzing jars using JarAnalyzer
 * <p>This implementation uses a PrintWriter directly which produces non-escaped xml content.
 *
 * @author HuberB1
 */
public class JarAnalyzerProcessor implements JarAnalyzerProcessorIF {
    private PrintWriter writer;
    
    /** Creates a new instance of JarAnalyzer */
    public JarAnalyzerProcessor() {
        this(new PrintWriter(System.out));
    }
    public JarAnalyzerProcessor(PrintWriter pw ) {
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
    
    private void outputAll(Jar ajar[]) {
        printHeader();
        output(ajar);
        printFooter();
        this.writer.flush();
        this.writer.close();
    }
    
    private void printHeader() {
        writer.println("<?xml version=\"1.0\"?>");
        writer.println("<JarAnalyzer>");
    }
    
    private void printFooter() {
        writer.println("</JarAnalyzer>");
    }
    
    private void output(Jar ajar[]) {
        writer.println(tab() + "<Jars>");
        writer.println();
        for(int i = 0; i < ajar.length; i++) {
            String s = ajar[i].getJarFileName().substring(ajar[i].getJarFileName().lastIndexOf("\\") + 1, ajar[i].getJarFileName().length());
            writer.println(tab(2) + "<Jar name=\"" + s + "\">");
            writer.println(tab(3) + "<Summary>");
            statistics(ajar[i]);
            writer.println();
            metrics(ajar[i].calculateMetrics());
            writer.println();
            jarPackages(ajar[i]);
            writer.println();
            outgoingDependencies(ajar[i]);
            incomingDependencies(ajar[i]);
            cycles(ajar[i]);
            unresolveableDependencies(ajar[i]);
            writer.println(tab(3) + "</Summary>");
            writer.println();
            writer.println(tab(2) + "</Jar>");
            writer.println();
        }
        
        writer.println(tab() + "</Jars>");
    }
    
    private void statistics(Jar jar) {
        writer.println(tab(4) + "<Statistics>");
        writer.println(tab(5) + "<ClassCount>" + jar.getClassCount() + "</ClassCount>");
        writer.println(tab(5) + "<AbstractClassCount>" + jar.getAbstractClassCount() + "</AbstractClassCount>");
        writer.println(tab(5) + "<PackageCount>" + jar.getPackageCount() + "</PackageCount>");
        writer.println(tab(4) + "</Statistics>");
    }
    
    private void metrics(JarMetrics jarmetrics) {
        writer.println(tab(4) + "<Metrics>");
        writer.println(tab(5) + "<Abstractness>" + jarmetrics.calculateAbstractness().toString() + "</Abstractness>");
        writer.println(tab(5) + "<Efferent>" + jarmetrics.calculateEfferentCoupling() + "</Efferent>");
        writer.println(tab(5) + "<Afferent>" + jarmetrics.calculateAfferentCoupling() + "</Afferent>");
        writer.println(tab(5) + "<Instability>" + jarmetrics.calculateInstability().toString() + "</Instability>");
        writer.println(tab(5) + "<Distance>" + jarmetrics.calculateDistance().toString() + "</Distance>");
        writer.println(tab(4) + "</Metrics>");
    }
    
    private void cycles(Jar jar) {
        writer.println(tab(4) + "<Cycles>");
        if(jar.hasCycles()) {
            String s;
            for(Iterator iterator = jar.getCyclicJars().iterator(); iterator.hasNext(); writer.println(tab(5) + "<Cycle>" + s + "</Cycle>")) {
                Jar jar1 = (Jar)iterator.next();
                s = jar1.getJarFileName();
            }
            
        }
        writer.println(tab(4) + "</Cycles>");
        writer.println();
    }
    
    private void outgoingDependencies(Jar jar) {
        writer.println(tab(4) + "<OutgoingDependencies>");
        String s;
        for(Iterator iterator = jar.getOutgoingDependencies().iterator(); iterator.hasNext(); writer.println(tab(5) + "<Jar>" + s + "</Jar>")) {
            Jar jar1 = (Jar)iterator.next();
            s = jar1.getJarFileName();
        }
        
        writer.println(tab(4) + "</OutgoingDependencies>");
        writer.println();
    }
    
    private void incomingDependencies(Jar jar) {
        writer.println(tab(4) + "<IncomingDependencies>");
        String s;
        for(Iterator iterator = jar.getIncomingDependencies().iterator(); iterator.hasNext(); writer.println(tab(5) + "<Jar>" + s + "</Jar>")) {
            Jar jar1 = (Jar)iterator.next();
            s = jar1.getJarFileName();
        }
        
        writer.println(tab(4) + "</IncomingDependencies>");
        writer.println();
    }
    
//    private void externalDependencies(Jar jar) {
//        String s;
//        for(Iterator iterator = jar.getAllExternallyReferencedPackages().iterator(); iterator.hasNext(); writer.println(tab(5) + "<Package>" + s + "</Package>"))
//            s = (String)iterator.next();
//
//    }
    
    private void unresolveableDependencies(Jar jar) {
        writer.println(tab(4) + "<UnresolvedDependencies>");
        String s;
        for(Iterator iterator = jar.getAllUnidentifiableExternallyReferencedPackages().iterator(); iterator.hasNext(); writer.println(tab(5) + "<Package>" + s + "</Package>"))
            s = (String)iterator.next();
        
        writer.println(tab(4) + "</UnresolvedDependencies>");
    }
    
    private void jarPackages(Jar jar) {
        writer.println(tab(4) + "<Packages>");
        JarPackage jarpackage;
        for(Iterator iterator = jar.getAllContainedPackages().iterator(); iterator.hasNext(); writer.println(tab(5) + "<Package>" + jarpackage.getLongName() + "</Package>"))
            jarpackage = (JarPackage)iterator.next();
        
        writer.println(tab(4) + "</Packages>");
    }
    
    private String tab() {
        return "    ";
    }
    
    private String tab(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append( tab() );
        for(int j = 1; j < i; j++) {
            sb.append( tab() );
        }
        
        return sb.toString();
    }
    
}
