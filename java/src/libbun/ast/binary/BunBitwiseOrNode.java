package libbun.ast.binary;

import libbun.ast.BNode;
import libbun.lang.bun.BunPrecedence;
import libbun.parser.BOperatorVisitor;
import libbun.parser.BVisitor;

public class BunBitwiseOrNode extends BitwiseOperatorNode {
	public BunBitwiseOrNode(BNode ParentNode) {
		super(ParentNode, BunPrecedence._CStyleBITOR);
	}
	@Override public BNode Dup(boolean TypedClone, BNode ParentNode) {
		return this.DupField(TypedClone, new BunBitwiseOrNode(ParentNode));
	}
	@Override public final String GetOperator() {
		return "|";
	}
	@Override public final void Accept(BVisitor Visitor) {
		if(Visitor instanceof BOperatorVisitor) {
			((BOperatorVisitor)Visitor).VisitBitwiseOrNode(this);
		}
		else {
			Visitor.VisitBinaryNode(this);
		}
	}
}
