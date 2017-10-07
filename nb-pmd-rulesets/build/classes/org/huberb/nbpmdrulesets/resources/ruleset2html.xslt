<?xml version="1.0" encoding="UTF-8"?>
<!-- Stylesheet to turn ruleset XML into a nice-looking HTML page -->
<!-- $Id$ -->
<xsl:stylesheet version="1.0" 
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rs="http://pmd.sf.net/ruleset/1.0.0"
>
    <xsl:output method="html" encoding="UTF-8" 
                doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" 
                indent="yes"
    />
    
    <xsl:template match="rs:ruleset">
        <html>
            <head>
                <title>Ruleset <xsl:value-of select="@name"/></title>
                <style type="text/css">
                    body {
                    margin: 10px;
                    font-family: Verdana, sans-serif;
                    }
                    h1, h2, h3 {
                    border: 1px solid lightgrey;
                    padding: 4px;
                    }
                    h1 {
                    background-color: #FFDA36;
                    }
                    h2 {
                    background-color: #FFEEA3;
                    }
                    h3 {
                    background-color: #FFBC36;
                    }
                    dt {
                    font-weight:bold;
                    }
                    dd {
                    }
                    
                    .rulesetDescription {
                    padding: 1px;
                    }
                    .rule {
                    border-top: 1px solid grey;
                    }
                    .ruleDescription {
                    padding: 1px;
                    }
                    
                    .property {
                    border-top: 1px solid grey;
                    }
                    
                    .propertyName {
                    }
                    .propertyDescription  {
                    }
                    .propertyValue {
                    background-color: lightgrey;
                    }
                    
                    .example {
                    padding: 1px;
                    background-color: lightgrey;
                    }
                </style>
            </head>
            <body>
                <a name="toc"></a>
                <h1>Ruleset <xsl:value-of select="@name"/></h1>
                <!--div>
                    <a href="index.html">Index</a>
                </div-->
                <div>
                    Language <xsl:call-template name="text"><xsl:with-param name="text"><xsl:value-of select="@language"/></xsl:with-param></xsl:call-template>
                </div>
                
                <h2>Description</h2>
                <pre class="rulesetDescription"><xsl:value-of select="rs:description"/></pre>
                
                <ul>
                    <xsl:for-each select="rs:rule">
                        <li>
                            <a>
                                <xsl:attribute name="href">#<xsl:value-of select="@name"/>::<xsl:value-of select="@ref"/></xsl:attribute>
                                <xsl:value-of select="@name"/>::<xsl:value-of select="@ref"/>
                            </a>
                        </li>
                    </xsl:for-each>
                </ul>
                <xsl:apply-templates select="rs:rule"/>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="rs:rule">
        <a>
            <xsl:attribute name="name"><xsl:value-of select="@name"/>::<xsl:value-of select="@ref"/></xsl:attribute>
        </a>
        
        <h2>Rule <xsl:value-of select="@name"/>::<xsl:value-of select="@ref"/></h2>
        <xsl:call-template name="toc-href"/>
        
        <h3>Description</h3>
        <pre class="ruleDescription"><xsl:value-of select="rs:description"/></pre>
        
        <dl class="rule">
            <dt>Message</dt><dd>
                <xsl:call-template name="text"><xsl:with-param name="text"><xsl:value-of select="@message"/></xsl:with-param></xsl:call-template>
            </dd>
            <dt>Class</dt><dd><xsl:value-of select="@class"/></dd>
            <dt>Reference</dt>
            <dd>
                <xsl:call-template name="text">
                    <xsl:with-param name="text"><xsl:value-of select="@ref"/></xsl:with-param>
                </xsl:call-template>                
            </dd>
            <dt>ExternalInfoUrl</dt>
            <dd>
                <xsl:call-template name="text"><xsl:with-param name="text"><xsl:value-of select="@externalInfoUrl"/></xsl:with-param></xsl:call-template>
            </dd>
            <dt>TypeResolution</dt>
            <dd>
                <xsl:call-template name="text"><xsl:with-param name="text"><xsl:value-of select="@typeResolution"/></xsl:with-param></xsl:call-template>
            </dd>
            <dt>Priority</dt>
            <dd>
                <xsl:call-template name="text"><xsl:with-param name="text"><xsl:value-of select="rs:priority"/></xsl:with-param></xsl:call-template>
            </dd>            
        </dl>
        
        <xsl:if test="rs:example">
            <h3>Example</h3>
            <div>Example code snippet of this rule.
            </div>
            <pre class="example"><xsl:text><xsl:value-of select="rs:example"/></xsl:text></pre>
        </xsl:if>
        
        <xsl:if test="rs:properties">
            <h3>Properties</h3>
            <div>Following properties are defined:
            </div>
            <xsl:apply-templates select="rs:properties"/>
        </xsl:if>
        
        <xsl:if test="rs:exclude">
            <h3>Excludes</h3>
            <xsl:call-template name="text"><xsl:with-param name="text"><xsl:value-of select="rs:excludes"/></xsl:with-param></xsl:call-template>
        </xsl:if>
        <br></br>
        <xsl:call-template name="toc-href"/>
        
    </xsl:template>
    
    <xsl:template match="rs:property">
        <dl class="property">
            <dt>Name</dt>
            <dd class="propertyName">
                <xsl:value-of select="@name"/>
            </dd>
            
            <dt>Description</dt>
            <dd class="propertyDescription">
                <xsl:call-template name="text">
                    <xsl:with-param name="text"><xsl:value-of select="@description"/></xsl:with-param>
                </xsl:call-template>                
            </dd>
            
            <dt>Value</dt>
            <dd>
                <pre class="propertyValue"><xsl:value-of select="rs:value"/><xsl:value-of select="@value"/></pre>
            </dd>
        </dl>
    </xsl:template>
    
    <xsl:template match="rs:exclude">
        <xsl:value-of select="@name"/>
    </xsl:template>
    
    
    <xsl:template name="text">
        <xsl:param name="text">-</xsl:param>
        <xsl:variable name="_text"><xsl:value-of select="normalize-space($text)"/></xsl:variable>
        
        <xsl:choose>
            <xsl:when test="string-length($_text)&gt;0"><xsl:value-of select="$_text"/></xsl:when>
            <xsl:otherwise>-</xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    <xsl:template name="toc-href">
        <a href="#toc">TOC</a>
    </xsl:template>
    
</xsl:stylesheet>

