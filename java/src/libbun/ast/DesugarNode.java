package libbun.ast;

import libbun.encode.AbstractGenerator;
import libbun.parser.BTypeChecker;
import libbun.util.BField;
import libbun.util.Var;

public class DesugarNode extends SyntaxSugarNode {
	//	public final static int _NewNode = 0;
	@BField BNode OriginalNode;

	public DesugarNode(BNode OriginalNode, BNode DesugardNode) {
		super(OriginalNode.ParentNode, null, 1);
		this.OriginalNode = OriginalNode;
		this.SetChild(OriginalNode, BNode._EnforcedParent);
		this.SetNode(0, DesugardNode);
	}

	public DesugarNode(BNode OriginalNode, BNode[] DesugarNodes) {
		super(OriginalNode.ParentNode, null, DesugarNodes.length);
		this.OriginalNode = OriginalNode;
		this.SetChild(OriginalNode, BNode._EnforcedParent);
		@Var int i = 0;
		while(i < DesugarNodes.length) {
			this.SetNode(i, DesugarNodes[i]);
			i = i + 1;
		}
	}

	private DesugarNode(BNode ParentNode, BNode OriginalNode, int Size) {
		super(ParentNode, null, Size);
		this.OriginalNode = OriginalNode;
	}

	@Override public BNode Dup(boolean TypedClone, BNode ParentNode) {
		if(TypedClone) {
			return this.DupField(TypedClone, new DesugarNode(ParentNode, this.OriginalNode.Dup(TypedClone, ParentNode), this.AST.length));
		}
		else {
			return this.OriginalNode.Dup(TypedClone, ParentNode);
		}
	}

	@Override public DesugarNode DeSugar(AbstractGenerator Generator, BTypeChecker TypeChekcer) {
		return this;
	}

}
