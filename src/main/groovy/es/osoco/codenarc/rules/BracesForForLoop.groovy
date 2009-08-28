/*
 * Copyright 2009, Osoco. All Rights Reserved.
 */
package es.osoco.codenarc.rules

import org.codenarc.rule.AbstractAstVisitor
import org.codenarc.rule.AbstractAstVisitorRule
import org.codehaus.groovy.ast.stmt.ForStatement

/**
 * Checks the location of the opening brace ({) for for loops. By
 * default, requires them on a new line, but the sameLine property can be set to
 * true to override this.
 *
 *
 * @author <a href="mailto:geli.crick@osoco.es">Geli Crick</a>
 */
class BracesForForLoop extends AbstractAstVisitorRule
{
    String name = 'BracesForForLoop'
    int priority = 2
    Class astVisitorClass = BracesForForLoopAstVisitor
    boolean sameLine = false
}

class BracesForForLoopAstVisitor extends AbstractAstVisitor
{
    void visitForLoop(ForStatement node)
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
        super.visitForLoop(node)
    }
}

