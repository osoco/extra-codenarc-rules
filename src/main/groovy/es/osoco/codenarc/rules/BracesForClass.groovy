/*
 * Copyright 2009, Osoco. All Rights Reserved.
 */
package es.osoco.codenarc.rules

import org.codehaus.groovy.ast.ClassNode
import org.codenarc.rule.AbstractAstVisitor
import org.codenarc.rule.AbstractAstVisitorRule

/**
 * Checks the location of the opening brace ({) for classes. By
 * default, requires them on a new line, but the sameLine property can be set to
 * true to override this.
 *
 * @author <a href="mailto:geli.crick@osoco.es">Geli Crick</a>
 */
class BracesForClass extends AbstractAstVisitorRule
{
    String name = 'BracesForClass'
    int priority = 2
    Class astVisitorClass = BracesForClassAstVisitor
    boolean sameLine = false
}

class BracesForClassAstVisitor extends AbstractAstVisitor
{
    void visitClass(ClassNode node)
    {
        if (rule.sameLine)
        {
            if(!sourceLine(node)?.contains("{"))
            {
                addViolation(node, "Braces should start on the same line")
            }
        }
        else
        {
            if(sourceLine(node)?.contains("{"))
            {
                addViolation(node, "Braces should start on a new line")
            }
        }
        super.visitClass(node)
    }
}
