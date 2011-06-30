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
