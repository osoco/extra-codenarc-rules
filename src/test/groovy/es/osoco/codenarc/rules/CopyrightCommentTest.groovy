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
/**
 * Test the CopyrightComment custom Codenarc rule
 *
 * @author <a href="mailto:geli.crick@osoco.es">Geli Crick</a>
 */
public class CopyrightCommentTest extends GroovyTestCase
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
     * Test a case where there is no comment
     */
    public void testNoComment()
    {
        def source = [getLines:{ ["package com.test", "", "/**", " * class",
                " * comment", " * copyright 2007"," */"] }] as SourceCode
        def violations = []

        CopyrightComment rule = new CopyrightComment()
        rule.applyTo(source, violations)

        assertEquals(1, violations.size())
    }

    /**
     * Test a case where the comment doesn't contain 'copyright'
     */
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

    /**
     * Test a correct case, with the text 'copyright'
     */
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

    /**
     * Test a correct case, with the text 'copyright', and case insensitivity
     */
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

    /**
     * Test a correct case, with the symbol
     */
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
