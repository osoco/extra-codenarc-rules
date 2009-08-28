/*
 * Copyright 2009, Osoco. All Rights Reserved.
 */
package es.osoco.codenarc.rules

import org.codehaus.groovy.ast.ClassNode
import org.codenarc.rule.AbstractAstVisitor
import org.codenarc.rule.AbstractAstVisitorRule

/**
 * Checks that Grails domain classes redefine equals()
 *
 * @author <a href="mailto:geli.crick@osoco.es">Geli Crick</a>
 */
class GrailsDomainHasEquals extends AbstractAstVisitorRule
{
    String name = 'GrailsDomainHasEquals'
    int priority = 3
    Class astVisitorClass = GrailsDomainHasEqualsAstVisitor
    String applyToFilesMatching = /.*grails-app\/domain\/.*/
}

class GrailsDomainHasEqualsAstVisitor extends AbstractAstVisitor
{
    void visitClass(ClassNode classNode)
    {
        def methods = classNode.methods
        def equalsMethod = methods.find { m ->
            m.name == 'equals' &&
            m.parameters.size() == 1 &&
            m.parameters[0].type.name in ['Object', 'java.lang.Object']
        }

        if (!equalsMethod) {
            addViolation(classNode)
        }
        super.visitClass(classNode)
    }
}

