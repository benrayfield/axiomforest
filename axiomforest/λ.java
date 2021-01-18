/** Ben F Rayfield offers this software opensource MIT license */
package axiomforest;

/** UPDATE: 2-way forest instead of 3-way, cuz I thought of a way
to put the bloom filter inside the 6 param wikibinator function,
so any other systems can still go in the same bloomfilter.
Will still sometimes include 3 childs (instead of 4) cuz of including superposition()
which is a cache and superposition().allUnknown() is true. superposition() can be derived from binary forest.
<br><br>
TODO I'm trying to merge the 2 kinds of trinary forest nodes (1 with a bit and 1 without)
into a trinary forest node type that has TruthValue, and it be TruthValue.unknown for without the bit,
and TruthValue.yes vs TruthValue.no for with the bit,
but problems are...
-- Object.hashCode and Object.equals.
-- The one with a bit needs bits in all those reachable thru childs recursively,
and the one without a bit needs none of the childs recursively to have bits,
and I could put in 2 bits to say if they all are those 2 ways or not,
but it might make it harder to verify in the network.
-- The one with a bit (allObserve) has pointer to the one with no bits (allUnknown), so is that another child?
<br><br>
TODO rewrite the below comments cuz merging from 2 kinds of 3-way forest class...
<br><br>
This was axiomforest.observe.Λ ...
<br><br>
A 3 way forest shape where self and all reachable by child pointers
are each observed to be a specific YES/1 vs NO/0.
<br><br>
Renamed this from Observedλ ("Observed" then lowercase lambda) to Λ (capital lambda),
therefore due to case insensitivity of some systems it had to go in a different java package.
<br><br>
same as modelofallpossibleaxioms.λ but a layer above that with an observed TruthValue.yes or TruthValue.no
with each of them and those TruthValues are part of the forest shape (aka affect merkle ids).
Since any trinaryForestNodes x and y, if both are true, imply <trinaryForestLeaf x y> is true,
Or TODO expand that so that <trinaryForestLeaf x y> is true for all x and y regardless of the truth or falseness of x and y???
... This might create a problem for the selfreference fix I'm trying to do where
all possible statements in the merkle forest must be viewable by a halted lambda as idMaker
so (idMaker someLazyEvaledFormOf_aWrongStatement) -> cbt its id, and as long as the wrong statement
is marked as TruthValue.no its a true statement that its TruthValue is no so can be wrapped that way, in theory.
All nodes should have a specific yes or no attached to them the first time they're observed
since SetOfAxioms.joiner.apply(λ,λ)->λ generates the YES statement <trinaryForestLeaf x y>,
and getAnyInSet(SetOfAxioms.impliesYes,Random).apply(<trinaryForestLeaf x y>)->λ generates a YES statement,
and getAnyInSet(SetOfAxioms.impliesNo,Random).apply(<trinaryForestLeaf x y>)->λ generates a NO statement,
and any recursions in the computing of lambdas such as callquad.stack and callquad.cacheKey etc (in trinary form)
will be done in terms of those axioms (or optimizations of them that dont actually call those slow axioms,
would themselves be UnaryOperator<λ> which are axioms, so an optimization of an axiom
(such as by lazycl to lwjgl opencl/gpu optimize matrix multiply, neuralnets, and other number crunching) is an axiom
and can go in the same SetOfAxioms. An optimization of an axiom is an axiom.
<br><br>
This was axiomforest.superposition.λ ...
<br><br>
immuable trinary forest node where all paths lead to leaf. Does not have any truthvalue or color,
as those would be stored in Observedλ which is a layer above this.
<br><br>
OLD... TODO undecided if TruthValue (instead of color) will be a child (part of merkle forest) vs
used in a separate map(s) of trinaryForestNode to TruthValue,
and am undecided if will use TruthValue instead of color
(such as 32 TruthValues in wikibinator HeaderBits long header,
some or all of those being part of a color or a whole color?
but trinary forest nodes are a lower level than wikibinator opcodes).
*/
public interface λ<Node extends λ<Node>>{
	
	/** This is core data. Is [the 3 way forest shape form of this (allUnknown)] a true statement,
	such as a certain lambda called on a certain 3 lambdas x called on y returns z?
	*/
	public TruthValue tv();
	
	/** This is core data. The leaf's TruthValue is TruthValue.yes, and so is <leaf x y> for any x and y regardless of their TruthValues. */
	public boolean isLeaf();
	
	/** This is core data. The +1bit of the 3+1bit childs. TruthValue.yes or TruthValue.no.
	VmState, 1 of 3 trinary forest childs. When vmState is leaf, func and param can be anything,
	so to start a lambda call of anything on anything (even the stuff found inside vmState,
	which can see into using λ.v(), λ.l(), and λ.r() recursively, and λ.a() to check when you've reached the leaf,
	and practically there will be any size bitstrings wrapped in cbt (see occamsfuncer and wikibinator docs about cbt)),
	call node(leaf(), anythingA, anythingB) aka anythingA.p(anythingB),
	then to eval it keep looping (OLD... use TruthValue instead of Color as in SetOfAxioms
	having 2 sets of UnaryOperator<λ> one for impliesYes and one for impliesNo, and a BinaryOperator<λ> joiner)
	λ state <- anAxiom.next(ToIntFunction<λ> colors, λ state)
	(OLD...) and updating with new ToIntFunction<λ> (such as VM.setColor(λ,int)) with the Coloredλ returned.
	*
	public λ v();
	*/
	
	/** This is core data. Func, 1 of 3 trinary forest childs. To start, all you have is leaf, so call that on itself, then you have 2 nodes. */
	public Node l();
	
	/** This is core data. Param, 1 of 3 trinary forest childs. To start, all you have is leaf, so call that on itself, then you have 2 nodes. */
	public Node r();
	
	//Whats above are the primary storage of data. Whats below is cache of it or of it in childs recursively,
	//or functions for creating combos of it etc.
	
	/** This is a cache. x.superposition().allUnknown()==true aka only the forest shape. */
	public Node superposition();
	
	
	
	
	
	/** This is a cache or derived. True if this and all those below are TruthValue.yes. This is a subset of allObserve that only needs 256 bit id.
	If all are yes or all unknown, then you only need a 256 bit id instead of 2 of those as a 512 bit id.
	*/
	public boolean allYes();
	
	/** This is a cache or derived. Are all tv() of [this and below in the 3 way forest] yes and no and none are unknown or bull?
	This replaces the axiomforest.observe.Λ class.
	*/
	public boolean allObserve();
	
	/** This is a cache or derived. True if this and all those below are TruthValue.unknown.
	If all are yes or all unknown, then you only need a 256 bit id instead of 2 of those as a 512 bit id.
	This replaces the axiomforest.superposition.λ class.
	This is a subset of allUnknownBelow().
	*/
	public boolean allUnknown();
	
	/** This is a cache or derived. Same as v().allUnknown()&l().allUnknown()&r().allUnknown().
	This can be a key which is allUnknown (just 3 way forest shape) and a TruthValue as its val,
	like a Map.Entry<threeWayForestNodeWithoutTruthValue,TruthValue>.
	This replaces the axiomforest.observe.λtv class.
	*/
	public boolean allUnknownBelow();
	
	/** This is a cache or derived. Are any of [this and below in the 3 way forest] bull? */
	public boolean anyBull();
	
	/** always TruthValue.unknown in λ.
	The +1bit of the 3+1bit childs. TruthValue.yes or TruthValue.no *
	public boolean tv();
	*/
		
	/** This is a cache or derived. Similar to minheap/maxheap indexing, this does multiple calls of v(), l(), and r(),
	without necessarily creating the objects between.
	00 is v(). 10 is l(). 01 is r(), or todo reorder those uint2s?. TODO should it use 11 to end the sequence,
	or 11 means self, or end it with the highest 1 bit?
	*/
	public Node vlr(long sequence);
	
	/** This is a cache or derived. Like vlr(long) except only does l() and r() so only needs 1 bit per branch, so can go twice as deep for same long.
	This will be very useful for large bitstrings (cbt).
	*/
	public Node lr(long sequence);
	
	/** This is a cache or derived. By trinary forest shape and TruthValue at each node.
	allYes, allObserve, allUnknown, and anyBull are cache of TruthValues reachable below,
	but for security normally go in content to merkle hash.
	*/
	public boolean equals(Object o);
	
	/** This is a cache or derived, and nondeterministic but deterministic within a run of the JVM.
	Not part of the axioms or datastruct but needed by java maps etc.
	By trinary forest shape and TruthValue at each node.
	allYes, allObserve, allUnknown, and anyBull are cache of TruthValues reachable below,
	but for security normally go in content to merkle hash.
	*/
	public int hashCode();
	
	/** This is a cache or derived. Returns the one leaf which all paths lead to.
	This does not depend on which instance λ its called from except they may optimize it different ways.
	*/
	public Node leaf();
	
	/** Call pair, independent of this instance, giving the TruthValue and its 2 childs. This could have been a static func.
	<br><br>
	OLD, cuz its 2-way forest now (actually 3 if include superposition() cache which can be derived).
	This is a cache or derived. This could have been a static func, but it seems better to put it here than in (ToIntFunction<λ>)VM.
	This does not depend on which instance λ its called from except they may optimize it different ways.
	All trinary forest nodes are valid, but most of them will be ignored aka you wont find,
	or would be very very hard to find, some Coloredλ returned from next whose λ is that one,
	which is cuz λ.v is "vm state" that only proceeds in certain ways,
	while the other 2 childs (λ.l and λ.r) can be anything when λ.v is leaf,
	which is the way to start a lambda call of l on r.
	*/
	public Node p(TruthValue tv, Node l, Node r);
	//public Node node(λ v, λ l, λ r);
	
	/** call pair with TruthValue.
	<br><br>
	OLD... This is a cache or derived. Lazy call l on r (params of this func) *
	public default λ node(λ l, λ r){
		return node(leaf(), l, r);
	}*/
	public default Node p(TruthValue tv, Node r){
		return p(tv, (Node)this, r);
	}
	
	/** call pair with TruthValue.yes */
	public default Node p(Node r){
		return p(TruthValue.yes, r);
	}
	
	
	/** This is a cache or derived. Lazy call this on param *
	public default λ p(λ param){
		return node(this,param);
	}*/

	/** same as SetOfAxioms.node(SetOfAxioms.leaf(), this, r);
	create or find λ node where node.v().a() aka isLeaf and node.l().equals(this) and node.r().equals(r) *
	public λ p(λ r);
	*/
	
	/** TODO part of merkle childs? instead of color.
	Each axiom is 2 functions of trinary forest node x to 2 trinary forest nodes y and z,
	where if x is true then that implies y is true and z is false,
	and forall b forall c: if b and c are true then <trinaryForestLeaf b c> is true,
	and trinaryForestLeaf is true.
	*
	public TruthValue tv();
	*/

}