/*
 * Copyright 2009, Osoco. All Rights Reserved.
 */
package es.osoco.codenarc.rules

import org.codenarc.rule.AbstractAstVisitor
import org.codenarc.rule.AbstractAstVisitorRule
import org.codehaus.groovy.ast.MethodNode

/**
 * Checks the location of the opening brace ({) for constructors and methods. By
 * default, requires them on a new line, but the sameLine property can be set to
 * true to override this.
 *
 * @author <a href="mailto:geli.crick@osoco.es">Geli Crick</a>
 */
class BracesForMethod extends AbstractAstVisitorRule
{
    String name = 'BracesForMethod'
    int priority = 3
    Class astVisitorClass = BracesForMethodAstVisitor
    boolean sameLine = false
}

class BracesForMethodAstVisitor extends AbstractAstVisitor
{

    void visitConstructorOrMethodEx(MethodNode node, boolean isConstructor)
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
    }
}

