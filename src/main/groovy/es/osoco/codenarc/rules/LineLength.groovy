/*
 * Copyright 2009, Osoco. All Rights Reserved.
 */
package es.osoco.codenarc.rules

/**
 * Custom Codenarc rule that sets a maximum length for each line of source code.
 * It checks for number of characters, so lines that include tabs may appear
 * longer than the allowed number when viewing the file.
 *
 * @author <a href="mailto:geli.crick@osoco.es">Geli Crick</a>
 */
import org.codenarc.rule.AbstractRule
import org.codenarc.rule.Violation
import org.codenarc.source.SourceCode

class LineLength extends AbstractRule
{
    String name = 'LineLength'
    int priority = 3
    int length = 80 // The default max line length. Can be overridden 

    /**
     * Apply the rule to the given source, writing violations to the given list.
     * @param sourceCode The source to check
     * @param violations A list of Violations that may be added to. It can be
     * an empty list
     */
    void applyTo(SourceCode sourceCode, List violations)
    {
        int lineNumber = 0
        for (line in sourceCode.getLines())
        {
            lineNumber++
            if (line.length() > length)
            {
                violations << createViolation(lineNumber, line, "The line exceeds $length characters")
            }
        }
    }

}