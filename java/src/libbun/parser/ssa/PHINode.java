package libbun.parser.ssa;

import libbun.parser.ast.BGetNameNode;
import libbun.parser.ast.BNode;
import libbun.parser.ast.ZBlockNode;
import libbun.parser.ast.ZLocalDefinedNode;
import libbun.util.Var;
import libbun.util.ZArray;

public class PHINode extends ZLocalDefinedNode {
	public ZArray<BNode>      Args;
	public ZArray<ZBlockNode> Blocks;
	public Variable VarRef;
	public Variable BackupValue;
	public String VariableName;

	public PHINode(Variable BackupValue, String VariableName) {
		super(null, null, 0);
		this.BackupValue = BackupValue;
		this.VariableName = VariableName;
		this.Args = new ZArray<BNode>(new BNode[0]);
		this.Blocks = new ZArray<ZBlockNode>(new ZBlockNode[0]);
		this.Type = NodeLib.GetType(BackupValue.Node);
	}

	public void AddIncoming(int Index, ZBlockNode block, BNode node) {
		while(Index + 1 > this.Args.size()) {
			this.Args.add(this.BackupValue.Node);
			this.Blocks.add(null);
		}
		ZArray.SetIndex(this.Args, Index, node);
		ZArray.SetIndex(this.Blocks, Index, block);
	}

	public boolean IsSameVariable(Variable Var) {
		return this.VariableName.equals(Var.Name);
	}

	@Override
	public String toString() {
		@Var String Txt = "PHI[";
		@Var int i = 0;
		while(i < this.Args.size()) {
			BNode Node = ZArray.GetIndex(this.Args, i);
			if (i != 0) {
				Txt += ", ";
			}
			Txt += Node.getClass().toString();
			i = i + 1;
		}
		Txt += "]";
		return Txt;
	}

	public int GetVarIndex() {
		return this.VarRef.Index;
	}

	public String GetName() {
		return this.VariableName;
	}

	public BNode GetArgument(int Index) {
		return ZArray.GetIndex(this.Args, Index);
	}

	public boolean EqualsName(BGetNameNode gNode) {
		// TODO Auto-generated method stub
		return false;
	}
}