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

package libbun.ast;
import libbun.parser.LibBunVisitor;
import libbun.util.BField;

public abstract class BunAnnotationNode extends BNode {
	//@Field public ZenMap<Object> Annotation;
	@BField public BNode AnnotatedNode = null;

	public BunAnnotationNode(BNode ParentNode) {
		super(ParentNode, 0);
	}

	//	@Override public void Append(ZNode Node) {
	//		if(Node instanceof ZAnnotationNode) {
	//			@Var ZAnnotationNode AnnoNode = (ZAnnotationNode)Node;
	//			this.Annotation.AddMap(AnnoNode.Annotation);
	//			Node = AnnoNode.AnnotatedNode;
	//		}
	//		this.AnnotatedNode = this.SetChild(Node);
	//	}

	@Override public void Accept(LibBunVisitor Visitor) {
		this.AnnotatedNode.Accept(Visitor);
	}
}