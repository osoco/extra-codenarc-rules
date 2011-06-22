/*
 * Copyright 2009, Osoco. All Rights Reserved.
 */
package es.osoco.codenarc.rules

import org.codenarc.rule.AbstractAstVisitor
import org.codenarc.rule.AbstractAstVisitorRule
import org.codehaus.groovy.ast.stmt.IfStatement

/**
 * Checks the location of the opening brace ({) for if statements. By
 * default, requires them on a new line, but the sameLine property can be set to
 * true to override this.
 *
 * It should be possible to check else statements here too, but the source code
 * returned seems to be inconsistent, and I haven't figured out how yet.
 *
 * @author <a href="mailto:geli.crick@osoco.es">Geli Crick</a>
 */
class BracesForIfElse extends AbstractAstVisitorRule
{
    String name = 'BracesForIfElse'
    int priority = 3
    Class astVisitorClass = BracesForIfElseAstVisitor
    boolean sameLine = false
}

class BracesForIfElseAstVisitor extends AbstractAstVisitor
{
    //TODO check else statements
    void visitIfElse(IfStatement node)
    {
        if (isFirstVisit(node)) {
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
        super.visitIfElse(node)
    }


}

