// ***************************************************************************
// Copyright (c) 2013-2014, Libbun project authors. All rights reserved.
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// *  Redistributions of source code must retain the above copyright notice,
//    this list of conditions and the following disclaimer.
// *  Redistributions in binary form must reproduce the above copyright
//    notice, this list of conditions and the following disclaimer in the
//    documentation and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
// TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
// PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
// CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
// EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
// OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
// WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
// OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
// ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
// **************************************************************************

package libbun.ast.unary;

import libbun.ast.AbstractListNode;
import libbun.ast.BNode;
import libbun.parser.BTypeChecker;
import libbun.parser.BVisitor;
import libbun.type.BFunc;
import libbun.type.BType;
import libbun.util.Var;

public class BunCastNode extends BNode {
	public final static int _Expr = 0;
	public final static int _TypeInfo = 1;

	public BunCastNode(BNode ParentNode, BType CastType, BNode Node) {
		super(ParentNode, null, 2);
		this.Type = CastType;
		if(Node != null) {
			this.SetNode(BunCastNode._Expr, Node);
		}
	}
	@Override public BNode Dup(boolean TypedClone, BNode ParentNode) {
		return this.DupField(TypedClone, new BunCastNode(ParentNode, this.Type, null));
	}

	public final BNode ExprNode() {
		return this.AST[BunCastNode._Expr ];
	}

	public final BType CastType() {
		if(this.AST[BunCastNode._TypeInfo ] != null) {
			this.Type = this.AST[BunCastNode._TypeInfo ].Type;
		}
		return this.Type;
	}

	@Override public void Accept(BVisitor Visitor) {
		Visitor.VisitCastNode(this);
	}

	public final AbstractListNode ToFuncCallNode(BTypeChecker TypeChecker, BFunc ConverterFunc) {
		@Var AbstractListNode FuncNode = TypeChecker.CreateDefinedFuncCallNode(this.ParentNode, this.SourceToken, ConverterFunc);
		FuncNode.Append(this.ExprNode());
		return FuncNode;
	}

}