/*
 * Copyright 2009, Osoco. All Rights Reserved.
 */
package es.osoco.codenarc.rules


import org.codenarc.rule.AbstractRule
import org.codenarc.rule.Violation
import org.codenarc.source.SourceCode

/**
 * Checks that each class starts with a copyright notice.
 *
 * @author <a href="mailto:geli.crick@osoco.es">Geli Crick</a>
 */
class CopyrightComment extends AbstractRule
{
    String name = 'CopyrightComment'
    int priority = 3

    /**
     * Apply the rule to the given source, writing violations to the given list.
     * @param sourceCode The source to check
     * @param violations A list of Violations that may be added to. It can be
     * an empty list
     */
    void applyTo(SourceCode sourceCode, List violations)
    {
        Iterator lines = sourceCode.getLines().iterator()
        String line = ""
        while (lines.hasNext() && line.isEmpty())
        {
            line = lines.next().trim()
        }

        if (line.startsWith("/*"))
        {
            boolean foundCopyright = false
            while (lines.hasNext() && !line.contains("*/")
                && foundCopyright == false)
            {
                line = lines.next().toLowerCase()
                if (line.contains("copyright") || line.contains("©"))
                {
                    foundCopyright = true
                }
            }
            if (!foundCopyright)
            {
                violations.add(new Violation(rule:this,
                lineNumber:1, sourceLine:"",
                message:"The copyright and license notice should contain the " +
                    "word \"copyright\" or the symbol ©"))
            }
        }
        else
        {
            violations.add(new Violation(rule:this,
                lineNumber:1, sourceLine:line,
                message:"The file should start with a copyright and license " +
                    "notice"))
        }

    }

}