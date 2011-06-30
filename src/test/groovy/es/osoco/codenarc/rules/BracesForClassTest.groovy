/*
 * Copyright 2010 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.osoco.codenarc.rules;

import org.codenarc.source.SourceCode
import org.codenarc.source.SourceFile


/**
 * Test the BracesForClass custom Codenarc rule
 *
 * @author <a href="mailto:geli.crick@osoco.es">Geli Crick</a>
 */
public class BracesForClassTest extends GroovyTestCase
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
     * Test a passing file with sameLine = false
     */
    public void testPassNewLine()
    {
        def testFile = this.getClass().getClassLoader()
            .getResource("BracesTestNewLine.txt")
        SourceCode source = new SourceFile(new File(testFile.toURI()))
        def violations = []

        BracesForClass rule = new BracesForClass()
        rule.applyTo(source, violations)

        assertEquals(0, violations.size())
    }

    /**
     * Test a failing file with sameLine = false
     */
    public void testFailNewLine()
    {
        def testFile = this.getClass().getClassLoader()
            .getResource("BracesTestSameLine.txt")
        SourceCode source = new SourceFile(new File(testFile.toURI()))
        def violations = []

        BracesForClass rule = new BracesForClass()
        rule.applyTo(source, violations)

        assertEquals(3, violations.size())
    }

    /**
     * Test a passing file with sameLine = true
     */
    public void testPassSameLine()
    {
        def testFile = this.getClass().getClassLoader()
            .getResource("BracesTestSameLine.txt")
        SourceCode source = new SourceFile(new File(testFile.toURI()))
        def violations = []

        BracesForClass rule = new BracesForClass()
        rule.sameLine = true
        rule.applyTo(source, violations)

        assertEquals(0, violations.size())
    }

    /**
     * Test a failing file with sameLine = true
     */
    public void testFailSameLine()
    {
        def testFile = this.getClass().getClassLoader()
            .getResource("BracesTestNewLine.txt")
        SourceCode source = new SourceFile(new File(testFile.toURI()))
        def violations = []

        BracesForClass rule = new BracesForClass()
        rule.sameLine = true
        rule.applyTo(source, violations)

        assertEquals(3, violations.size())
    }
}
