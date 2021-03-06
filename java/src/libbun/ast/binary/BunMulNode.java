package libbun.ast.binary;

import libbun.ast.BNode;
import libbun.lang.bun.BunPrecedence;
import libbun.parser.BunVisitor;
import libbun.parser.LibBunVisitor;

public class BunMulNode extends ArithmeticOperatorNode {
	public BunMulNode(BNode ParentNode) {
		super(ParentNode, BunPrecedence._CStyleMUL);
	}
	@Override public BNode Dup(boolean TypedClone, BNode ParentNode) {
		return this.DupField(TypedClone, new BunMulNode(ParentNode));
	}

	@Override public final String GetOperator() {
		return "*";
	}
	@Override public final void Accept(LibBunVisitor Visitor) {
		if(Visitor instanceof BunVisitor) {
			((BunVisitor)Visitor).VisitMulNode(this);
		}
		else {
			Visitor.VisitBinaryNode(this);
		}
	}
}
