/** Ben F Rayfield offers this software opensource MIT license */
package axiomforest.observe;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/** aka SetOfAxiomsObserved */
public interface ΛObserver{
	
	public Λ leaf();
	
	/** an optimization of an axiom is also an axiom. These UnaryOperator<Observedλ>s are axioms. */
	public Set<UnaryOperator<Λ>> implies();
	
	/** of trinaryForestNodes: forall x forall y: <trinaryForestLeaf x y> is YES, regardless of x and y being YES andOr NO,
	which allows a halted lambda to see any nonhalted part of the system such as to generate a custom kind of id of it
	and to compute statements about all possible statements that can be said in the system but only about 1 at a time.
	This returns <YES trinaryForestLeaf x y> for its 2 params x y.
	*/
	public BinaryOperator<Λ> joiner();
	
}
