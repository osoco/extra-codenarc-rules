package es.osoco.codenarc.rules

import org.codehaus.groovy.ast.ClassNode
import org.codenarc.rule.AbstractAstVisitor
import org.codenarc.rule.AbstractAstVisitorRule

/**
 *
 * @author gcrick
 */


class GrailsDomainHasToString extends AbstractAstVisitorRule {
    String name = 'GrailsDomainHasToString'
    int priority = 2
    Class astVisitorClass = GrailsDomainHasToStringAstVisitor
    String applyToFilesMatching = /.*grails-app\/domain\/.*/
}

class GrailsDomainHasToStringAstVisitor extends AbstractAstVisitor {

    void visitClass(ClassNode classNode) {
        def methods = classNode.methods
        def toStringMethod = methods.find { m ->
            m.name == 'toString' &&
            m.parameters.size() == 0
        }

        if (!toStringMethod) {
            addViolation(classNode)
        }
        super.visitClass(classNode)
    }
}

