package es.osoco.codenarc.rules;

import org.codenarc.source.SourceCode
/**
 * Test the CopyrightComment custom Codenarc rule
 * @author gcrick
 */
public class CopyrightCommentTest extends GroovyTestCase {
    

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    
    public void testNoComment()
    {
        def source = [getLines:{ ["package com.test", "", "/**", " * class",
                " * comment", " * copyright 2007"," */"] }] as SourceCode
        def violations = []

        CopyrightComment rule = new CopyrightComment()
        rule.applyTo(source, violations)

        assertEquals(1, violations.size())
    }

    public void testNoText()
    {
        def source = [getLines:{ ["   ", "/*", " * license",
                " * comment", " * 2007"," */", "", "package",
                "/*", " * copyright", " */"] }] as SourceCode
        def violations = []

        CopyrightComment rule = new CopyrightComment()
        rule.applyTo(source, violations)

        assertEquals(1, violations.size())
    }

    public void testCorrectText()
    {
        def source = [getLines:{ ["   ", "/*", " * license",
                " * comment", " * copyright 2007"," */", "",
                "package"] }] as SourceCode
        def violations = []

        CopyrightComment rule = new CopyrightComment()
        rule.applyTo(source, violations)

        assertEquals(0, violations.size())
    }

    public void testCaseInsensitive()
    {
        def source = [getLines:{ ["   ", "/*", " * license",
                " * comment", " * CopyRighT 2007"," */", "",
                "package"] }] as SourceCode
        def violations = []

        CopyrightComment rule = new CopyrightComment()
        rule.applyTo(source, violations)

        assertEquals(0, violations.size())
    }

    public void testCorrectSymbol()
    {
        def source = [getLines:{ ["   ", "/*", " * license",
                " * comment", " * © 2007"," */", "",
                "package"] }] as SourceCode
        def violations = []

        CopyrightComment rule = new CopyrightComment()
        rule.applyTo(source, violations)

        assertEquals(0, violations.size())
    }
}
