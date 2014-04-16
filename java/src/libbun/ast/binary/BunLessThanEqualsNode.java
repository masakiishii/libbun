package libbun.ast.binary;

import libbun.ast.BNode;
import libbun.lang.bun.BunPrecedence;
import libbun.parser.BOperatorVisitor;
import libbun.parser.BToken;
import libbun.parser.BVisitor;

public class BunLessThanEqualsNode extends ComparatorNode {

	public BunLessThanEqualsNode(BNode ParentNode) {
		super(ParentNode, BunPrecedence._CStyleCOMPARE);
	}
	@Override public BNode Dup(boolean TypedClone, BNode ParentNode) {
		return this.DupField(TypedClone, new BunLessThanEqualsNode(ParentNode));
	}

	@Override public final String GetOperator() {
		return "<=";
	}
	@Override public final void Accept(BVisitor Visitor) {
		if(Visitor instanceof BOperatorVisitor) {
			((BOperatorVisitor)Visitor).VisitLessThanEqualsNode(this);
		}
		else {
			Visitor.VisitBinaryNode(this);
		}
	}

}
