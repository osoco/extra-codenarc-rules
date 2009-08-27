package es.osoco.codenarc.rules

import org.codehaus.groovy.ast.ClassNode
import org.codenarc.rule.AbstractAstVisitor
import org.codenarc.rule.AbstractAstVisitorRule

/**
 *
 * @author gcrick
 */


class GrailsDomainHasEquals extends AbstractAstVisitorRule {
    String name = 'GrailsDomainHasEquals'
    int priority = 2
    Class astVisitorClass = GrailsDomainHasEqualsAstVisitor
    String applyToFilesMatching = /.*grails-app\/domain\/.*/
}

class GrailsDomainHasEqualsAstVisitor extends AbstractAstVisitor {

    void visitClass(ClassNode classNode) {
        def methods = classNode.methods
        def equalsMethod = methods.find { m ->
            m.name == 'equals' &&
            m.parameters.size() == 1 &&
            m.parameters[0].type.name in ['Object', 'java.lang.Object']
        }

        if (!equalsMethod) {
            addViolation(classNode)
        }
        super.visitClass(classNode)
    }
}

