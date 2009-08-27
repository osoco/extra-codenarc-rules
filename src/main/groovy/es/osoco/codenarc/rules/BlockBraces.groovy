package es.osoco.codenarc.rules

import org.codenarc.source.SourceCode
import org.codenarc.rule.AbstractRule
import org.codenarc.rule.Violation

/**
 *
 * @author gcrick
 */
class BlockBraces extends AbstractRule
{
    String name = 'BlockBraces'
    int priority = 3
    String regex = ~/\S+.*[^\$]\{(?!.*->)/


    void applyTo(SourceCode sourceCode, List violations)
    {
        def matcher = sourceCode.getText() =~ regex
        while (matcher.find())
        {
            def match = matcher.group()
            // ==~ tests, if String matches the pattern
//assert "2009" ==~ /\d+/  // returns TRUE
//assert "holla" ==~ /\d+/ // returns FALSE
//
//            if ()
            def lineNumber =
                sourceCode.getLineNumberForCharacterIndex(matcher.start())
            violations.add(new Violation(rule:this, lineNumber:lineNumber,
                sourceLine:matcher.group(),
                message:"Braces that define a block should start on a new " +
                "line"))
            // Don't include if matcher.group() starts with // or * (ignore white space)
        }
    }

}