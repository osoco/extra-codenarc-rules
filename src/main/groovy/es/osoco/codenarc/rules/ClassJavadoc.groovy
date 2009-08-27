/*
 * Copyright 2009, Osoco. All Rights Reserved.
 */
package es.osoco.codenarc.rules

/**
 * Makes sure each class and interface definition is preceeded by javadoc. Enum
 * definitions are not checked, due to strange behavior in the Groovy AST. It
 * seems when an enum is found, it is removed from the original ocntext.
 *
 * @author <a href="mailto:geli.crick@osoco.es">Geli Crick</a>
 */
import org.codenarc.rule.AbstractRule
import org.codenarc.rule.Violation
import org.codenarc.source.SourceCode

class ClassJavadoc extends AbstractRule
{
    String name = 'ClassJavadoc'
    int priority = 3

    /**
     * Apply the rule to the given source, writing violations to the given list.
     * @param sourceCode The source to check
     * @param violations A list of Violations that may be added to. It can be
     * an empty list
     */
    void applyTo(SourceCode sourceCode, List violations)
    {
        def lines = sourceCode.getLines()
        def classes = sourceCode.getAst().getClasses()
        classes.each(){classNode ->
            if (classNode.isPrimaryClassNode()
                && classNode.getSuperClass().getName() != 'java.lang.Enum')
            {
                def index = classNode.getLineNumber() - 1
                
                while (lines[--index].trim().startsWith("*"))
                {/* Do nothing, to simulate an until loop */}

                if (!lines[index].trim().startsWith("/**"))
                {
                    violations.add(
                        createViolation(
                            sourceCode, classNode, "Missing Javadoc"))
                }
            }
        }
    }

}