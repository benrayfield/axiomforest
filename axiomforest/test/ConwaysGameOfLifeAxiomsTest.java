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
		Also, (u (typeval "text/plain" "ConwaysGameOfLifeAxiomsTest456457345345") ...params...)
		namespace is meant to be
		a permanent part of the axiomforest, as is every little experiment that anyone creates,
		as long as it doesnt imply anything outside its namespace, it wont interfere with other things
		unless those things choose to interfere with it based on what it proves and disproves
		within its namespace.
	}

}
