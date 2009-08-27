/*
 * Copyright 2009, Osoco. All Rights Reserved.
 */
package es.osoco.codenarc.rules;

import org.codenarc.source.SourceCode
import org.codenarc.source.SourceFile


/**
 * Test the ClassJavadoc custom Codenarc rule
 *
 * @author <a href="mailto:geli.crick@osoco.es">Geli Crick</a>
 */
public class ClassJavadocTest extends GroovyTestCase
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
     * Test a passing file
     */
    public void testPass()
    {
        def testFile = this.getClass().getClassLoader().getResource("pass.test")
        SourceCode source = new SourceFile(new File(testFile.toURI()))
        def violations = []

        ClassJavadoc rule = new ClassJavadoc()
        rule.applyTo(source, violations)

        assertEquals(0, violations.size())
    }

    /**
     * Test a failing file
     */
    public void testFail()
    {
        def testFile = this.getClass().getClassLoader().getResource("fail.test")
        SourceCode source = new SourceFile(new File(testFile.toURI()))
        def violations = []

        ClassJavadoc rule = new ClassJavadoc()
        rule.applyTo(source, violations)

        assertEquals(2, violations.size())
    }
}
