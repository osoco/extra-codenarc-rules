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

import org.codehaus.groovy.ast.ClassNode
import org.codenarc.rule.AbstractAstVisitor
import org.codenarc.rule.AbstractAstVisitorRule

/**
 * Checks that Grails domain classes redefine toString()
 *
 * @author <a href="mailto:geli.crick@osoco.es">Geli Crick</a>
 */
class GrailsDomainHasToString extends AbstractAstVisitorRule
{
    String name = 'GrailsDomainHasToString'
    int priority = 3
    Class astVisitorClass = GrailsDomainHasToStringAstVisitor
    String applyToFilesMatching = /.*grails-app\/domain\/.*/
}

class GrailsDomainHasToStringAstVisitor extends AbstractAstVisitor
{

    void visitClassComplete(ClassNode classNode)
    {
        def methods = classNode.methods
        def toStringMethod = methods.find { m ->
            m.name == 'toString' &&
            m.parameters.size() == 0
        }

        if (!toStringMethod)
        {
            addViolation(classNode)
        }
    }
}

