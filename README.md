# axiomforest
A kind of number completely defined in this sentence, that an axiomforest is an immutable 4-way forest with 2 axiomforest childs and 2 bits which mean Unknown, Yes, No, or Bull, and Bull means simultaneous Yes and No which happens when ORing axiomforests together, and Bull is an error needing forking, and the creation of new axiomforests happens by any chosen function of 2 axiomforests to 1 axiomforest (try all pairs randomly, or strategicly for more efficiency, with each such node node to node function) which is a way to change statements from unknown to yes or no but if bull happens anywhere then whatever caused it you need to back out and dont use any set of statements being yes or no which can possibly lead to bull again as converging gradually toward higher consistency. Its for number crunching, experimenting with various kinds of godel-numbering and pattern calculus functions and universal lambda functions (combinators) such as occamsfuncer, wikibinator, iota, unlambda, andOr urbit, and for AIs and neuralnets and neural-turing-machine-like experiments, and virtual worlds computed on GPUs with trillions of threads and exabits stored and flowing data across the internet at gaming-low-lag all as one big sparse bloom-filter where unknown is 00, yes is 10, no is 01, and bull is 11, in each binary forest node, and if you want to say just 1 binary forest node is yes or no then you use unknown (00) in all nodes below it (theres an optimization for it, caching a few extra bits in node ids derived from below) and this node is yes (10) or no (01) which are the 4 TruthValues. Scaleable. Gaming-low-lag. Immutable. Merkle. Near-godel-quality-self-reference. Number.

```
package axiomforest.test;
import java.util.function.BinaryOperator;
import axiomforest.λ;
public class ConwaysGameOfLifeAxiomsTest{
	public static final BinaryOperator<λ> axiom = (λ a, λ b)->{
		TODO
	};
	public static void main(String... args){
		TODO run ConwaysGameOfLifeAxiomsTest.axiom in many random combos,
		proving YES vs NO various random places,
		and watch (todo put it on screen) conways game of life move,
		while allowing you to draw on it. Will need a huge possible state space
		since every map of [map of 3d point to conwayAliveCell vs conwayDeadCell] to yes/no,
		or something like that, or only within 3x3x2 rectangles or something like that...
		You cant ever change between YES and NO...
		Only UNKNOWN can become YES or UNKNOWN becomes NO,
		per axiomforest node, so whatever possible starting state,
		and updates within it, all that has to be "in a different multiverse branch"
		than things which disagree on which cells are which of alive/dead.
		After wikibinator102 is working in axiomforest, you wont need a new axiom for each kind of game,
		since the wikibinator102 axiom will contain all possible turing complete statements
		and axioms about how to compute them in bigO(1) size steps,
		including conways game of life, chess, go, FPS games, and a variety of other things
		we might build together
		in the shared space, proven or disproven consistent with eachother by axioms.
		Also, (u (typeval "text/plain" "ConwaysGameOfLifeAxiomsTest456457345345") ...params...) namespace,
		aka (u "ConwaysGameOfLifeAxiomsTest456457345345" ...params...) aka (conwayxyz ...params...)
		if locally written after (u "ConwaysGameOfLifeAxiomsTest456457345345")#conwayxyz
		or some similar statement written in a syntax made of <>[](){}... that namespace is meant to be
		a permanent part of the axiomforest, as is every little experiment that anyone creates,
		as long as it doesnt imply anything outside its namespace, it wont interfere with other things
		unless those things choose to interfere with it based on what it proves and disproves
		within its namespace.
		
		The state space of all possible conwaylife games is like the space of all possible minecraft worlds,
		as its 2d + 1d of time, and if it allows you to paint alive/dead cells in that 3d space,
		without having to store any of the data (since every possible multiverse state has a constant id) on a server
		but you store the state you're observing and forks of it and combos of that with other peoples forks...
		Then it seem to need a treemap (trie-like except skipping parts where theres only 1 branch),
		which is the kind of treemap I'm going to use in wikibinator102 but I'll have to hardcode it into
		the ConwaysGameOfLifeAxiomsTest456457345345 axiom since this is an early testcase.
		A treemap generates the same 256 bit id regardless of the order of things put in it, for every possible treemap.
		Axioms would therefore recurse 1 depth at a time in such a treemap (cuz must be bigO(1) steps)
		and forkEdit the treemap to have different contents, such as forking to a new "conway multiverse state".
		That would take alot of steps to run conwaylife, while each step would take a few microseconds,
		but things that change over time and have an endless 3d minecraft-like space normally are slow.
	}
}
```

u is the leaf aka thue universal function, and is height 0.

(u (u u)) is 0, and is height 2.

((u u) u) is 1, and is height 2.

(u anything_at_least_height_2) is a namespace, such as (u "wikibinator102" ...params...) is where statements about the wikibinator version 1.02 universal function might go, each such statement being unknown, yes, no, or bull, where "wikibinator102" is a complete binary tree (cbt) of 0 and 1 such as (((10)(11))((00)(10))) is the byte 10110010, and from that we can make utf8/unicode text, but only of powOf2 size bitstrings, and view that as the last 1 is just past the end of the bitstring, so 1000000... pads until next powOf2, to define any bitstring. It will support an unlimited number of different kinds of ids, generated by universal functions, or multiple id types at once. For example, you might use the first 30 bytes of sha3_256 of 3 axiomforests, and have 1 byte for its TruthValue, 6 bits for certain caches from below (allYes allUnknown allUnknownBelow allObserve anyBull isLeaf myYesPartOfTruthValue myNoPartOfTruthValue) and have 8 bits for being all 0 if its not a complete binary tree of bits and 254 possible values of its height for bitstrings of sizes 2^0 bits to 2^253 bits (sparse) and if its the byte 255 it means it is still a bitstring but its height doesnt fit in a byte. The third child would be a cache of this same binary forest shape but with all nodes as TruthValue.unknown, which is the normed form of a binary forest shape and can be used to find contradictions or just generally align them. So it would be those 2 bytes then the last 30 bytes of sha3_256 of those 3 axiomforests (the first 2 are childs and the third is a cache of the normed form). You would send 128 bytes at a time, to mean here's the id of an axiomforest and here are its 2 childs and its normed form, or the third is all 0s if its already the normed form of itself, and an id256 can have up to 128 bits of literal data (a complete binary tree of 128 bits) so you can generate the ids of its left and right childs all the way down to leaf without storing them, such as you might store a complex number of 2 float64s in an id256 or 4 float32s or a string of 15 utf8 bytes. Even though an id can only hold 128 bits and with its 2 childs and normed form is 128 bytes to store 16 bytes, the binary storage efficiency will be over 99% since any bitstring tree-hashes to a specific id, given a specific idMaker (which is any halted lambda (which is an axiomforest) that returns a bitstring when its param is an axiomforest [that wraps the actual param axiomforest to make it useable by a halted lambda], and those kind of details vary by namespace.

Anyone can read and write anything, but we onky share immutable merkle forest nodes that tend to OR into eachother as long as we expect that wont later lead to any TruthValue.bull.

Inside (u "wikibinator102") will be an infinite number of possible halted lambdas, which are themself as the call pairs, like you can see with the L and R and IsLeaf opcodes in https://github.com/benrayfield/wikibinator104/ and (older never finished) https://github.com/benrayfield/wikibinator101/ or similar in https://github.com/benrayfield/occamsfuncer or in urbit etc. Any of those, and any system at all could in theory be translated into sparse bloom filter form, but each node can only be written once, as yes or no, except that it can be written an infinite number of times as long as they all write the same value of yes or all no and any of them can write unknown since that doesnt change yes or change no or change bull. Anything thats not halted must be some other data structure (see debugStepInto and debugStepOver both in wikibinator (todo) and occamsfuncer (OcfnUtil.java has that working, using 2 callquads together, one as a state of the stack and one as a cache of func called on param gives return value which the stack state is looking for what happens when call that func on that param), within the binary forest than its literal call pairs, since all axiomforests are halted, and all work comes in bigO(1) constant size pieces that you might put in a java.util.function.BinaryOperator<axiomforest> or in a javascript or python function of 2 objects to 1 object. Its not specific to any of these languages. Its just a bloom filter with 2 bits at each possible binary forest node and a merkle forest for each possible state of a node and possible states of all nodes reachable downward through L and R child pointers down to leaf, each with a specific TruthValue (unknown, yes, no, bull).
  
 (u "mutuallyAssuredDestruction" 0 x y z) -> z is No, or if its 1 instead of 0 as that param then it proves z is Yes (you can prove both, just do both of those separately, therefore proving z is Bull, which expands exponentially as mutuallyAssuredDestruction can use z to prove anything else is Bull, and so on, until the whole p2p network is bull and to avoid that happening people and computers will become very motivated to have exactly 0 bull in the network or to converge quickly to it before the mutuallyAssuredDestruction spreads, or if they dont like this axiom which might be viewed as too fast and too extreme then it can also be proven to be No and therefore unuseable), if x and y are the same forest shape and 1 of x and y is Yes and the other is No aka Bull, which is correct logic since thats a proof that Yes equals No, and using Yes equals No you can prove absolutely anything, which is what the "mutuallyAssuredDestruction" namespace is for, proving everything that its asked to prove using a proof that yes equals no.

OLD[[[
A 3 way forest, in abstract math at least but will scale to exabits and trillions of threads, where all paths lead to leaf where turing complete axioms can be built which are bigO(1) to eval, and a layer of 3-way forest above that with true or false at each node, as an infinite dimensional sparse bloom filter where each node may be true, false, or unknown and never observed especially if unproveable. This will be a layer under wikibinator, which is a universal lambda function (combinator) and pattern calculus function, which forks of wikibinator or other systems could use to share the same 3-way forest and provide third party validation of the claim that wikibinator is just an axiom in the axiom space and is not above or below any other possible axioms. You can read about wikibinator and see the (as of 2021-1-13 incomplete code) at https://github.com/benrayfield/wikibinator
]]]
