/*
 * Copyright 2009, Osoco. All Rights Reserved.
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

