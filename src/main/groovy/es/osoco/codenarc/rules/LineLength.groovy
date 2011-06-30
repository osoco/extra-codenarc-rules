/*
 * Copyright 2011 the original author or authors.
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
package es.osoco.codenarc.rules

/**
 * Custom Codenarc rule that sets a maximum length for each line of source code.
 * It checks for number of characters, so lines that include tabs may appear
 * longer than the allowed number when viewing the file.
 *
 * @author <a href="mailto:geli.crick@osoco.es">Geli Crick</a>
 */
import org.codenarc.rule.AbstractRule
import org.codenarc.rule.Violation
import org.codenarc.source.SourceCode

class LineLength extends AbstractRule
{
    String name = 'LineLength'
    int priority = 3
    int length = 80 // The default max line length. Can be overridden 

    /**
     * Apply the rule to the given source, writing violations to the given list.
     * @param sourceCode The source to check
     * @param violations A list of Violations that may be added to. It can be
     * an empty list
     */
    void applyTo(SourceCode sourceCode, List violations)
    {
        int lineNumber = 0
        for (line in sourceCode.getLines())
        {
            lineNumber++
            if (line.length() > length)
            {
                violations << createViolation(lineNumber, line, "The line exceeds $length characters")
            }
        }
    }

}