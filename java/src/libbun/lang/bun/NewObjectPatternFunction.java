package libbun.lang.bun;

import libbun.parser.ZTokenContext;
import libbun.parser.ast.ZNewObjectNode;
import libbun.parser.ast.BNode;
import libbun.util.Var;
import libbun.util.ZMatchFunction;

public class NewObjectPatternFunction extends ZMatchFunction {

	@Override public BNode Invoke(BNode ParentNode, ZTokenContext TokenContext, BNode LeftNode) {
		@Var BNode LiteralNode = new ZNewObjectNode(ParentNode);
		LiteralNode = TokenContext.MatchToken(LiteralNode, "new", ZTokenContext._Required);
		LiteralNode = TokenContext.MatchPattern(LiteralNode, ZNewObjectNode._TypeInfo, "$OpenType$", ZTokenContext._Optional);
		LiteralNode = TokenContext.MatchNtimes(LiteralNode, "(", "$Expression$", ",", ")");
		return LiteralNode;
	}

}
