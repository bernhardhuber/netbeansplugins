/*
 * JDependProcessorXML.java
 *
 * Created on 23. April 2006, 15:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.jdependlib;

import java.io.PrintWriter;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.Locale;

import jdepend.framework.JavaClass;
import jdepend.framework.JavaPackage;
import org.openide.util.NbBundle;

/**
 * The <code>JDepend</code> class analyzes directories of Java class files,
 * generates metrics for each Java package, and reports the metrics in an XML
 * format.
 *
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 * @author HuberB1
 */
public class JDependProcessorXML extends JDependProcessor {

    /**
     * Constructs a <code>JDepend</code> instance using standard output. Creates
     * a new instance of JDependProcessorXML
     */
    public JDependProcessorXML() {
        this(new PrintWriter(System.out));
    }

    /**
     * Constructs a <code>JDepend</code> instance with the specified writer.
     * Creates a new instance of JDependProcessorXML
     *
     *
     * @param writer Writer.
     */
    public JDependProcessorXML(PrintWriter writer) {
        super(writer);

        formatter = NumberFormat.getInstance(Locale.ENGLISH);
        formatter.setMaximumFractionDigits(2);

    }

    @Override
    protected void printHeader() {
        getWriter().println("<?xml version=\"1.0\"?>");
        getWriter().println("<JDepend>");
    }

    @Override
    protected void printFooter() {
        getWriter().println("</JDepend>");
    }

    @Override
    protected void printPackagesHeader() {
        getWriter().println(tab() + "<Packages>");
    }

    @Override
    protected void printPackagesFooter() {
        getWriter().println(tab() + "</Packages>");
    }

    @Override
    protected void printPackageHeader(JavaPackage jPackage) {
        printSectionBreak();
        getWriter().println(
                tab(2) + "<Package name=\"" + jPackage.getName() + "\">");
    }

    @Override
    protected void printPackageFooter(JavaPackage jPackage) {
        getWriter().println(tab(2) + "</Package>");
    }

    @Override
    protected void printNoStats() {
        final String noStatsMessage = NbBundle.getMessage(JDependProcessorXML2.class, "JDP_NON_STATS");
        getWriter().println(
                tab(3) + "<error>" + noStatsMessage + "</error>");
    }

    @Override
    protected void printStatistics(JavaPackage jPackage) {
        getWriter().println(tab(3) + "<Stats>");
        getWriter().println(
                tab(4) + "<TotalClasses>" + jPackage.getClassCount()
                + "</TotalClasses>");
        getWriter().println(
                tab(4) + "<ConcreteClasses>" + jPackage.getConcreteClassCount()
                + "</ConcreteClasses>");
        getWriter().println(
                tab(4) + "<AbstractClasses>" + jPackage.getAbstractClassCount()
                + "</AbstractClasses>");
        getWriter().println(
                tab(4) + "<Ca>" + jPackage.afferentCoupling() + "</Ca>");
        getWriter().println(
                tab(4) + "<Ce>" + jPackage.efferentCoupling() + "</Ce>");
        getWriter().println(
                tab(4) + "<A>" + toFormattedString(jPackage.abstractness())
                + "</A>");
        getWriter().println(
                tab(4) + "<I>" + toFormattedString(jPackage.instability())
                + "</I>");
        getWriter().println(
                tab(4) + "<D>" + toFormattedString(jPackage.distance())
                + "</D>");
        getWriter().println(tab(4) + "<V>" + jPackage.getVolatility() + "</V>");
        getWriter().println(tab(3) + "</Stats>");
    }

    @Override
    protected void printClassName(JavaClass jClass) {
        getWriter().println(
                tab(4) + "<Class sourceFile=\"" + jClass.getSourceFile()
                + "\">");
        getWriter().println(tab(5) + jClass.getName());
        getWriter().println(tab(4) + "</Class>");
    }

    @Override
    protected void printPackageName(JavaPackage jPackage) {
        getWriter().println(
                tab(4) + "<Package>" + jPackage.getName() + "</Package>");
    }

    @Override
    protected void printAbstractClassesHeader() {
        getWriter().println(tab(3) + "<AbstractClasses>");
    }

    @Override
    protected void printAbstractClassesFooter() {
        getWriter().println(tab(3) + "</AbstractClasses>");
    }

    @Override
    protected void printConcreteClassesHeader() {
        getWriter().println(tab(3) + "<ConcreteClasses>");
    }

    @Override
    protected void printConcreteClassesFooter() {
        getWriter().println(tab(3) + "</ConcreteClasses>");
    }

    @Override
    protected void printEfferentsHeader() {
        getWriter().println(tab(3) + "<DependsUpon>");
    }

    @Override
    protected void printEfferentsFooter() {
        getWriter().println(tab(3) + "</DependsUpon>");
    }

    @Override
    protected void printEfferentsError() {
        // do nothing
    }

    @Override
    protected void printAfferentsHeader() {
        getWriter().println(tab(3) + "<UsedBy>");
    }

    @Override
    protected void printAfferentsFooter() {
        getWriter().println(tab(3) + "</UsedBy>");
    }

    @Override
    protected void printAfferentsError() {
        // do nothing
    }

    @Override
    protected void printCyclesHeader() {
        printSectionBreak();
        getWriter().println(tab() + "<Cycles>");
    }

    @Override
    protected void printCyclesFooter() {
        getWriter().println(tab() + "</Cycles>");
    }

    @Override
    protected void printCycleHeader(JavaPackage jPackage) {
        getWriter().println(
                tab(2) + "<Package Name=\"" + jPackage.getName() + "\">");
    }

    @Override
    protected void printCycleFooter() {
        getWriter().println(tab(2) + "</Package>");
        printSectionBreak();
    }

    @Override
    protected void printCycleTarget(JavaPackage jPackage) {
        printCycleContributor(jPackage);
    }

    @Override
    protected void printCycleContributor(JavaPackage jPackage) {
        getWriter().println(
                tab(3) + "<Package>" + jPackage.getName() + "</Package>");
    }

    @Override
    protected void printSummary(Collection packages) {
        // do nothing
    }

}
