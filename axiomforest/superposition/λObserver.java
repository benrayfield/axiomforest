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
see the numOfUniqBinaryForestShapesAtHeight(x) = see the numOfUniqBinaryForestShapesAtHeight(x-1)^2+1
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
	
	/* implies all paths from here (param) to/from there (param) are odd,
	and if its unproveable then there there are no paths of even or odd length
	Vaguely might somehow be related to parity in physics andOr linear algebra but I dont want to speculate yet.
	I imagine using just impliesYes and impliesNo but this seems to complete the pattern.
	*/
	public Set<UnaryOperator<λ>> impliesOdd();
	
	/* implies all paths from here (param) to/from there (param) are even,
	and if its unproveable then there there are no paths of even or odd length.
	Vaguely might somehow be related to parity in physics andOr linear algebra but I dont want to speculate yet.
	I imagine using just impliesYes and impliesNo but this seems to complete the pattern.
	*/
	public Set<UnaryOperator<λ>> impliesEven();
	
	/** of trinaryForestNodes: forall x forall y: if x and y are true then <trinaryForestLeaf x y> is true.
	This returns <trinaryForestLeaf x y> for its 2 params x y.
	*/
	public BinaryOperator<λ> joiner();
	
}
