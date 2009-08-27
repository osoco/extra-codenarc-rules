/*
 * Copyright 2009, Osoco. All Rights Reserved.
 */
package es.osoco.codenarc.rules;

import groovy.mock.interceptor.MockFor
import org.codenarc.source.SourceCode

/**
 * Test the LineLength custom Codenarc rule
 *
 * @author <a href="mailto:geli.crick@osoco.es">Geli Crick</a>
 */
public class LineLengthTest extends GroovyTestCase
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
     * Make sure the rule picks out the line that is too long
     */
    public void testRule()
    {
        def source = [getLines:{ ["abc", "def hij", "klm n"] }] as SourceCode
        def violations = []

        LineLength rule = new LineLength()
        rule.length = 5
        rule.applyTo(source, violations)

        assertEquals(1, violations.size())
        assertEquals(2, violations[0].lineNumber)
    }
}
