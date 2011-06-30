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
 * Makes sure each class and interface definition is preceeded by javadoc. Enum
 * definitions are not checked, due to strange behavior in the Groovy AST. It
 * seems when an enum is found, it is removed from the original ocntext.
 *
 * @author <a href="mailto:geli.crick@osoco.es">Geli Crick</a>
 */
import org.codenarc.rule.AbstractRule
import org.codenarc.rule.Violation
import org.codenarc.source.SourceCode

class ClassJavadoc extends AbstractRule
{
    String name = 'ClassJavadoc'
    int priority = 3

    /**
     * Apply the rule to the given source, writing violations to the given list.
     * @param sourceCode The source to check
     * @param violations A list of Violations that may be added to. It can be
     * an empty list
     */
    void applyTo(SourceCode sourceCode, List violations)
    {
        def lines = sourceCode.getLines()
        def classes = sourceCode.getAst().getClasses()
        classes.each(){classNode ->
            if (classNode.isPrimaryClassNode()
                && classNode.getSuperClass().getName() != 'java.lang.Enum')
            {
                def index = classNode.getLineNumber() - 1
                
                while (lines[--index].trim().startsWith("*"))
                {/* Do nothing, to simulate an until loop */}

                if (!lines[index].trim().startsWith("/**"))
                {
                    violations.add(
                        createViolation(
                            sourceCode, classNode, "Missing Javadoc"))
                }
            }
        }
    }

}