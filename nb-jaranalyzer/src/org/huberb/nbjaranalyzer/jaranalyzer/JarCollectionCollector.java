/*
 * JarCollectionCollector.java
 *
 * Created on 17. Februar 2007, 15:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbjaranalyzer.jaranalyzer;

import com.kirkk.analyzer.framework.Jar;
import com.kirkk.analyzer.framework.JarCollection;
import com.kirkk.analyzer.framework.bcelbundle.JarBuilderImpl;
import com.kirkk.analyzer.framework.jar.JarFile;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A jar collection collector, collecting jars
 *
 * @author HuberB1
 */
public class JarCollectionCollector implements JarCollection {
    private List<Jar> jars;
    private Iterator<Jar> jarIterator;
    
    /** Creates a new instance of JarCollectionCollector */
    public JarCollectionCollector(File srcDir, List<String> ignorePackages, List<String> ignoreJars) throws Exception {
        this.jars = getJars( srcDir, ignorePackages, ignoreJars );
        this.jarIterator = this.jars.iterator();
    }
    public JarCollectionCollector( List<File> jars, List<String> ignorePackages, List<String> ignoreJars ) throws Exception {
        this.jars = getJars( jars, ignorePackages, ignoreJars );
        this.jarIterator = this.jars.iterator();
    }
    
    /**
     * Get jars by specifying the parent directory of the jar files
     */
    private List<Jar> getJars(File srcDir, List<String> ignorePackages, List<String> ignoreJars) throws Exception {
        List<Jar> arraylist = new ArrayList<Jar>();
        
        if(srcDir.isDirectory()) {
            File afile[] = getJarFiles(srcDir);
            for(int i = 0; i < afile.length; i++) {
                File aJar = afile[i];
                addJar( arraylist, aJar, ignorePackages, ignoreJars );
            }
            return arraylist;
        } else {
            throw new IOException("File must be a directory");
        }
    }
    private File[] getJarFiles(File srcDir) {
        FilenameFilter filenamefilter = new FilenameFilter() {
            public boolean accept(File file1, String s) {
                return s.endsWith(".jar");
            }
        };
        return srcDir.listFiles(filenamefilter);
    }
    
    /**
     * Get jars by specifying all jar files
     */
    private List<Jar> getJars( List<File> jars, List<String> ignorePackages, List<String> ignoreJars) throws Exception {
        List<Jar> arraylist = new ArrayList<Jar>();
        for(int i = 0; i < jars.size(); i++) {
            File aJar = jars.get(i);
            addJar( arraylist, aJar, ignorePackages, ignoreJars );
        }
        return arraylist;
    }
    
    /**
     * Add a Jar to a list.
     */
    private void addJar( List<Jar> arrayList, File aJar, List<String> ignorePackages, List<String> ignoreJars ) throws Exception {
        JarBuilderImpl jarbuilderimpl = new JarBuilderImpl();
        JarFile jarfile = new JarFile(aJar);
        Jar jar = jarbuilderimpl.buildJar(jarfile, ignorePackages);
        String jarFileName = jar.getJarFileName();
        if(jar.getClassCount() > 0 && !ignoreJars.contains(jarFileName)) {
            arrayList.add(jar);
        }
    }
    
    //---
    // implement JarCollection interface
    
    public int getJarCount() {
        return this.jars.size();
    }
    
    public boolean hasNext() {
        return this.jarIterator.hasNext();
    }
    
    public Jar nextJar() {
        return (Jar)jarIterator.next();
    }
    
    public Jar getJarContainingPackage(String s) {
        for(Iterator iterator = jars.iterator(); iterator.hasNext();) {
            Jar jar = (Jar)iterator.next();
            if(jar.containsPackage(s)) {
                return jar;
            }
        }
        return null;
    }
    
    public void first() {
        jarIterator = jars.iterator();
    }
    
    public Jar getJar(String s) {
        for(Iterator iterator = jars.iterator(); iterator.hasNext();) {
            Jar jar = (Jar)iterator.next();
            if(jar.getJarFileName().equals(s)) {
                return jar;
            }
        }
        return null;
    }
    
    public Jar[] toArray() {
        Jar[] jars = (Jar[])this.jars.toArray( new Jar[this.jars.size()]);
        return jars;
    }
    
}
