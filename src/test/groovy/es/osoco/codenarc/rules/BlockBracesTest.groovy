package es.osoco.codenarc.rules

import org.codenarc.source.SourceCode

/**
 *
 * @author gcrick
 */
class BlockBracesTest extends GroovyTestCase
{
    
    protected void setUp()
    {
        super.setUp()
    }


    protected void tearDown()
    {
        super.tearDown()
    }

    public void testRuleForTextBefore()
    {
        def source = [getText:{ """
public class Test {
    def something
                """ }, getLineNumberForCharacterIndex:{i -> i}] as SourceCode

        def violations = []

        BlockBraces rule = new BlockBraces()
        rule.applyTo(source, violations)

        assertEquals(1, violations.size())
        assertEquals(1, violations[0].lineNumber)
    }

//    public void testRuleForTextAfter()
//    {
//        def source = [getText:{ """
//public class Test
//{ // Some text
//    def something
//                """ }, getLineNumberForCharacterIndex:{i -> i}] as SourceCode
//
//        def violations = []
//
//        BlockBraces rule = new BlockBraces()
//        rule.applyTo(source, violations)
//
//        assertEquals(0, violations.size())
//    }

//    public void testRuleForProperties()
//    {
//        def source = [getText:{ """
//    def something = \${prop}
//                """ }, getLineNumberForCharacterIndex:{i -> i}] as SourceCode
//
//        def violations = []
//
//        BlockBraces rule = new BlockBraces()
//        rule.applyTo(source, violations)
//        println violations
//        assertEquals(0, violations.size())
//    }


    /*
     """
    public class Test {
	def something
	def block = {

	}
	def str = "string with ${param} sdfs"

	public class another
	{
		stuff
		def closure = { a -> b}
		def otherClosure = {a ->
			stuff
		}
		return {something}
/* in comments { sdfsd *
// or this kind { sdfs
list.collect( newList ) {
  it.toUpperCase()
}
"""
    */
}

