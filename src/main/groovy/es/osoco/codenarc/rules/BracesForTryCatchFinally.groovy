/*
 * Copyright 2009, Osoco. All Rights Reserved.
 */
package es.osoco.codenarc.rules

import org.codenarc.rule.AbstractAstVisitor
import org.codenarc.rule.AbstractAstVisitorRule
import org.codehaus.groovy.ast.stmt.TryCatchStatement

/**
 * Checks the location of the opening brace ({) for try statements. By
 * default, requires them on a new line, but the sameLine property can be set to
 * true to override this.
 *
 * It should be possible to check catch and finally statements here too, but it
 * is not implemented.
 *
 * @author <a href="mailto:geli.crick@osoco.es">Geli Crick</a>
 */
class BracesForTryCatchFinally extends AbstractAstVisitorRule
{
    String name = 'BracesForTryCatchFinally'
    int priority = 3
    Class astVisitorClass = BracesForTryCatchFinallyAstVisitor
    boolean sameLine = false
}

class BracesForTryCatchFinallyAstVisitor extends AbstractAstVisitor
{

    //TODO Check catch and finally statements
    void visitTryCatchFinally(TryCatchStatement node)
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
        super.visitTryCatchFinally(node)
    }
}

