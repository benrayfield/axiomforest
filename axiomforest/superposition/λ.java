/** Ben F Rayfield offers this software opensource MIT license */
package axiomforest.superposition;

import immutable.util.TruthValue;

/** immuable trinary forest node where all paths lead to leaf. Does not have any truthvalue or color,
as those would be stored in Observedλ which is a layer above this.
<br><br>
OLD... TODO undecided if TruthValue (instead of color) will be a child (part of merkle forest) vs
used in a separate map(s) of trinaryForestNode to TruthValue,
and am undecided if will use TruthValue instead of color
(such as 32 TruthValues in wikibinator HeaderBits long header,
some or all of those being part of a color or a whole color?
but trinary forest nodes are a lower level than wikibinator opcodes).
*/
public interface λ{
	
	/** isLeaf */
	public boolean a();
	
	/** TODO part of merkle childs? instead of color.
	Each axiom is 2 functions of trinary forest node x to 2 trinary forest nodes y and z,
	where if x is true then that implies y is true and z is false,
	and forall b forall c: if b and c are true then <trinaryForestLeaf b c> is true,
	and trinaryForestLeaf is true.
	*
	public TruthValue tv();
	*/
	
	/** vmState, 1 of 3 trinary forest childs. When vmState is leaf, func and param can be anything,
	so to start a lambda call of anything on anything (even the stuff found inside vmState,
	which can see into using λ.v(), λ.l(), and λ.r() recursively, and λ.a() to check when you've reached the leaf,
	and practically there will be any size bitstrings wrapped in cbt (see occamsfuncer and wikibinator docs about cbt)),
	call node(leaf(), anythingA, anythingB) aka anythingA.p(anythingB),
	then to eval it keep looping (OLD... use TruthValue instead of Color as in SetOfAxioms
	having 2 sets of UnaryOperator<λ> one for impliesYes and one for impliesNo, and a BinaryOperator<λ> joiner)
	λ state <- anAxiom.next(ToIntFunction<λ> colors, λ state)
	(OLD...) and updating with new ToIntFunction<λ> (such as VM.setColor(λ,int)) with the Coloredλ returned.
	*/
	public λ v();
	
	/** func, 1 of 3 trinary forest childs. To start, all you have is leaf, so call that on itself, then you have 2 nodes. */
	public λ l();
	
	/** param, 1 of 3 trinary forest childs. To start, all you have is leaf, so call that on itself, then you have 2 nodes. */
	public λ r();
	
	/** Similar to minheap/maxheap indexing, this does multiple calls of v(), l(), and r(),
	without necessarily creating the objects between.
	00 is v(). 10 is l(). 01 is r(), or todo reorder those uint2s?. TODO should it use 11 to end the sequence,
	or 11 means self, or end it with the highest 1 bit?
	*/
	public λ vlr(long sequence);
	
	/** like vlr(long) except only does l() and r() so only needs 1 bit per branch, so can go twice as deep for same long.
	This will be very useful for large bitstrings (cbt).
	*/
	public λ lr(long sequence);
	
	/** by trinary forest shape */
	public boolean equals(Object o);
	
	/** by trinary forest shape. not part of the axioms or datastruct but needed by java maps etc. */
	public int hashCode();
	
	/** Returns the one leaf which all paths lead to.
	This does not depend on which instance λ its called from except they may optimize it different ways.
	*/
	public λ leaf();
	
	/** This could have been a static func, but it seems better to put it here than in (ToIntFunction<λ>)VM.
	This does not depend on which instance λ its called from except they may optimize it different ways.
	All trinary forest nodes are valid, but most of them will be ignored aka you wont find,
	or would be very very hard to find, some Coloredλ returned from next whose λ is that one,
	which is cuz λ.v is "vm state" that only proceeds in certain ways,
	while the other 2 childs (λ.l and λ.r) can be anything when λ.v is leaf,
	which is the way to start a lambda call of l on r.
	*/
	public λ node(λ v, λ l, λ r);
	
	/** lazy call l on r (params of this func) */
	public default λ node(λ l, λ r){
		return node(leaf(), l, r);
	}
	
	/** lazy call this on param */
	public default λ p(λ param){
		return node(this,param);
	}

	/** same as SetOfAxioms.node(SetOfAxioms.leaf(), this, r);
	create or find λ node where node.v().a() aka isLeaf and node.l().equals(this) and node.r().equals(r) *
	public λ p(λ r);
	*/

}