/*
 * JarAnalyzerProcessorIF.java
 *
 * Created on 02. März 2007, 23:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbjaranalyzer.jaranalyzer;

import java.io.File;
import java.util.List;

/**
 *
 * @author HuberB1
 */
public interface JarAnalyzerProcessorIF {
    void createSummary(File srcDir) throws Exception;
    void createSummary(List<File> jars) throws Exception;
    
}
