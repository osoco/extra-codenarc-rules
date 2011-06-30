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
        def testFile = this.getClass().getClassLoader()
            .getResource("ClassJavadocPass.txt")
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
        def testFile = this.getClass().getClassLoader()
            .getResource("ClassJavadocFail.txt")
        SourceCode source = new SourceFile(new File(testFile.toURI()))
        def violations = []

        ClassJavadoc rule = new ClassJavadoc()
        rule.applyTo(source, violations)

        assertEquals(2, violations.size())
    }
}
