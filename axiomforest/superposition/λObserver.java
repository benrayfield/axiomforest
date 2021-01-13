/** Ben F Rayfield offers this software opensource MIT license */
package axiomforest.superposition;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/** aka SetOfAxiomsUnobserved.
impliesNo and impliesYes is a subset of what ΛObserver can do,
but ΛObserver can imply things from false statements depending on the axioms.
λObserver started as planning a 3-way-forest where all nodes you keep are true
and only imply more true nodes, then wanted to also imply that the statement
that for 4 lambdas b c d e: (b c)->d and (b c)->e cant both be true at once,
since every call of lambda on lambda returns at most 1 lambda (I have a 1-to-1 mapping between lambdas and integers,
see the numOfUniqBinaryForestShapesAtHeight(x) = numOfUniqBinaryForestShapesAtHeight(x-1)^2+1
and numOfUniqBinaryForestShapesAtHeight(0)=1, but excluding those which have more than 5 curries
(lambda.l.l.l.l.l is leaf if its 5 curries, lambda is leaf if its 0 curries), so skip those in counting them.
Then I started upgrading to Λ (3-way and a bit) but found
I still need the λ objects (3-way) to match forest shapes efficiently. 
*/
public interface λObserver{
	
	public λ leaf();
	
	/** If param is true then it implies the return is true */
	public Set<UnaryOperator<λ>> impliesYes();
	
	/** If param is true then it implies the return is false */
	public Set<UnaryOperator<λ>> impliesNo();
	
	/* Λ.tv() is opposite my tv() but without having to know either,
	similar to from any corner of a hypercube the corners can be divided into 2 groups those of even vs odd parity,
	but things dont have to be organized like a hypercube its just here in case you want to use those kinds of axioms,
	or view it as all paths from here (UnaryOperator param) to/from there (UnaryOperator return) are odd parity,
	and if its unproveable then there there are no paths of even or odd length
	Vaguely might somehow be related to parity in physics andOr linear algebra but I dont want to speculate yet.
	I imagine using just impliesYes and impliesNo but this seems to complete the pattern.
	<br><br>
	See https://github.com/benrayfield/hypercubewave smooth curves about parity, which is the display of
	about 2^100 voxels of a 100 dimensional hypercube whose corners are each white or black depending
	on the parity of all paths length from any chosen corner and is trivially just log number of copy move invert average. 
	*/
	public Set<UnaryOperator<λ>> odd();
	
	/* Λ.tv() is equal to my tv() but without having to know either,
	similar to from any corner of a hypercube the corners can be divided into 2 groups those of even vs odd parity,
	but things dont have to be organized like a hypercube its just here in case you want to use those kinds of axioms,
	or view it as all paths from here (UnaryOperator param) to/from there (UnaryOperator return) are even parity.
	and if its unproveable then there there are no paths of even or odd length.
	Vaguely might somehow be related to parity in physics andOr linear algebra but I dont want to speculate yet.
	I imagine using just impliesYes and impliesNo but this seems to complete the pattern.
	<br><br>
	See https://github.com/benrayfield/hypercubewave smooth curves about parity, which is the display of
	about 2^100 voxels of a 100 dimensional hypercube whose corners are each white or black depending
	on the parity of all paths length from any chosen corner and is trivially just log number of copy move invert average.
	*/
	public Set<UnaryOperator<λ>> even();
	
	/** of trinaryForestNodes: forall x forall y: <trinaryForestLeaf x y> is YES, regardless of x and y being YES andOr NO,
	which allows a halted lambda to see any nonhalted part of the system such as to generate a custom kind of id of it
	and to compute statements about all possible statements that can be said in the system but only about 1 at a time.
	This returns <trinaryForestLeaf x y> for its 2 params x y.
	*/
	public BinaryOperator<λ> joiner();
	
}
