/*
 * ProjectHelper.java
 *
 * Created on 23. April 2006, 16:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbjaranalyzer.misc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.netbeans.api.java.classpath.ClassPath;
import org.netbeans.api.java.project.JavaProjectConstants;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.project.Sources;
import org.netbeans.spi.java.classpath.ClassPathProvider;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 * A simple helper class to determine the class directories
 * defined in a project.
 *
 * @author HuberB1
 */
public class ProjectHelper {
    
    /** Creates a new instance of ProjectHelper */
    public ProjectHelper() {
    }
    
    /**
     * Find directory names holding built classes of the Project
     *
     * @param project the project which is examined
     * @return List<String> the directories storing the build java classes,
     *   e.g. <code>build/classes</code>
     */
    public List<String> findClassDirectories( Project project ) {
        List<String> classDirectories = new ArrayList<String>();
        
        final ClassPathProvider cpp = (ClassPathProvider)project.getLookup().lookup(ClassPathProvider.class);
        final Sources s = (Sources)project.getLookup().lookup(Sources.class);
        final SourceGroup []sourceGroups = s.getSourceGroups(JavaProjectConstants.SOURCES_TYPE_JAVA);
        
        for (int j = 0; j < sourceGroups.length; j++ ) {
            final SourceGroup sg = sourceGroups[j];
            final FileObject sgRootFolder = sg.getRootFolder();
            
            final ClassPath cp = cpp.findClassPath( sgRootFolder, ClassPath.EXECUTE );
            
            for (FileObject root : cp.getRoots()) {
                final File file = FileUtil.toFile( root );
                if (file != null && file.isDirectory()) {
                    classDirectories.add( file.toString() );
                }
            }
        }
        
        return classDirectories;
    }
}
