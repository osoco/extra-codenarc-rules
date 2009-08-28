/*
 * Copyright 2009, Osoco. All Rights Reserved.
 */
package es.osoco.codenarc.rules;

import org.codenarc.source.SourceFile
import org.codenarc.source.SourceCode

/**
 * Test the BracesForIfElse custom Codenarc rule
 *
 * @author <a href="mailto:geli.crick@osoco.es">Geli Crick</a>
 */
public class BracesForIfElseTest extends GroovyTestCase
{
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * Test a passing file with sameLine=false
     */
    public void testPassNewLine()
    {
        def testFile = this.getClass().getClassLoader()
            .getResource("BracesTestNewLine.txt")
        SourceCode source = new SourceFile(new File(testFile.toURI()))
        def violations = []

        BracesForIfElse rule = new BracesForIfElse()
        rule.applyTo(source, violations)

        assertEquals(0, violations.size())
    }

    /**
     * Test a failing file sameLine=false
     */
    public void testFailNewLine()
    {
        def testFile = this.getClass().getClassLoader()
            .getResource("BracesTestSameLine.txt")
        SourceCode source = new SourceFile(new File(testFile.toURI()))
        def violations = []

        BracesForIfElse rule = new BracesForIfElse()
        rule.applyTo(source, violations)

        assertEquals(3, violations.size())
    }

    /**
     * Test a passing file with sameLine=true
     */
    public void testPassSameLine()
    {
        def testFile = this.getClass().getClassLoader()
            .getResource("BracesTestSameLine.txt")
        SourceCode source = new SourceFile(new File(testFile.toURI()))
        def violations = []

        BracesForIfElse rule = new BracesForIfElse()
        rule.sameLine = true
        rule.applyTo(source, violations)

        assertEquals(0, violations.size())
    }

    /**
     * Test a failing file sameLine=true
     */
    public void testFailSameLine()
    {
        def testFile = this.getClass().getClassLoader()
            .getResource("BracesTestNewLine.txt")
        SourceCode source = new SourceFile(new File(testFile.toURI()))
        def violations = []

        BracesForIfElse rule = new BracesForIfElse()
        rule.sameLine = true
        rule.applyTo(source, violations)

        assertEquals(3, violations.size())
    }
}
