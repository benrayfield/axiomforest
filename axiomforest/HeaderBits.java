package axiomforest;

import javax.xml.stream.events.Namespace;

/** 16 bit header, often followed by 240 bits of merkle hash or an object in memory that lazyEvals those 240 bits
and is partial instant deduped, or slower full instant deduped, using a hashtable as in hash-consing.
<br><br>
The first 5 binary forest shapes: u, (u u), (u (u u)), ((u u) u), ((u u)(u u)),
sorted first by height, then to break ties sort by left child recursively, then to break ties sort by right child recursively,
and as an optimization if you can check equality in bigo1 then you only recurse into left or right but never both
since if the 2 left childs equal you recurse into right else you recurse into left, of course after checking equality of parent.
Since axiomforest is technically a 4 way tree if you count the 2 bits of TruthValue as childs,
those 2 bits sort last (TODO choose order, should it be "unknown yes no bull" or "unknown no yes bull" etc?).
<br><br>
u is the leaf aka the universal function.
Can know its this by height==0.
<br><br>
(u u) is the second function, since to start with you only have u.
Can know its this by height==1.
<br><br>
(u (u u)) is 0.
Can know its this by isAll0s_and_is_cbt0_to_cbt124 and is_cbt0_to_cbt124 and height==2.
<br><br>
((u u) u) is 1. todo reorder these?
Can know its this by !isAll0s_and_is_cbt0_to_cbt124 and is_cbt0_to_cbt124 and height==2.
<br><br>
((u u) (u u)). Can know its this by height==2 and !is_cbt0_to_cbt124.
I'm not sure what might use this for, but its the only other height 2 node than 0 and 1,
maybe use it to wrap utf8 bitstrings to make them display as strings? Or should it be typeval
such as (typeval "utf8" "this is a string") where typeval has a semantic that if its first param is a cbt then its a utf8 string,
but you could also have (typeval "double[]" <the bits of the double array>) etc,
but similarly you could use (u "double[]" <the bits of the double array>) where "double[]" is the namespace,
as the namespaces are starting to look like what I wanted typeval to be, though they normally come with
a custom function of (axiomnode,axiomnode)->axiomnode while typeval was just a semantic.
<br><br>
(u anything_of_height_at_least_3) is namespace anything_of_height_at_least_3.
<br><br>
???16 bits of header (todo reorder these)[
	7 bits - height is 0..126, or 127 means height is bigger.
	isAll0s_and_is_cbt0_to_cbt124 - way to efficiently skip sparse ranges such as bitstring padding or 1d sparse array.
	is_cbt0_to_cbt124 aka bitstring up to size 2^124-1 (excluding 1000000... padding to next powOf2).
	no
	yes
	allYes
	allObserve
	allUnknown
	allUnknownBelow
	anyBull
]???
<br><br>
TODO id differs by only 1 bit if its allYes vs allObserve,
and maybe similar (1-2 more bits?) for those vs allUnknown vs allUnknownBelow.
*/
public class HeaderBits{
	private HeaderBits(){}
	
	public final short maskCbt =             0b0000000011111111;
	public final short maskNo =              0b0000000100000000;
	public final short maskYes =             0b0000001000000000;
	public final short maskLeaf =            0b0000010000000000;
	public final short maskAllYes =          0b0000100000000000;
	public final short maskAllObserve =      0b0001000000000000;
	public final short maskAllUnknown =      0b0010000000000000;
	public final short maskAllUnknownBelow = 0b0100000000000000;
	public final short maskAnyBull =  (short)0b1000000000000000;
	
	7 bits - height is 0..126, or 127 means height is bigger.
	isAll0s_and_is_cbt0_to_cbt124 - way to efficiently skip sparse ranges such as bitstring padding or 1d sparse array.
	is_cbt0_to_cbt124 aka bitstring up to size 2^124-1 (excluding 1000000... padding to next powOf2).
	no
	yes
	allYes
	allObserve
	allUnknown
	allUnknownBelow
	anyBull
	
	/*public final short maskCbt =             0b0000000011111111;
	public final short maskNo =              0b0000000100000000;
	public final short maskYes =             0b0000001000000000;
	public final short maskLeaf =            0b0000010000000000;
	public final short maskAllYes =          0b0000100000000000;
	public final short maskAllObserve =      0b0001000000000000;
	public final short maskAllUnknown =      0b0010000000000000;
	public final short maskAllUnknownBelow = 0b0100000000000000;
	public final short maskAnyBull =  (short)0b1000000000000000;
	*/
	
	/*
	TODO choose what the maskCbt bits mean.
	u.
	(u u) is 0 and is a cbt1.
	(u 0) is 1 and is a cbt1.
	(0 0) and (0 1) and (1 0) and (1 1) are all cbt2.
	((1 0)(1 1)) is a cbt4.
	maskAnyBull has 256 possible values. Most of those (or todo slightly less than half?) are specific cbt sizes.
	The other parts need to say that its not a cbt or not one of those specific sizes of cbt.
	If we dont know height then its inefficient to know if its a cbt or not,
	since its a cbt if its 0 or 1 or if its a callpair of 2 cbts of the same height.
	..
	height is 0..126, or 127 means height is bigger.
	is_cbt0_to_cbt126
	..
	cbt0_to_cbt254 else 255 means is anything else. Or should 0 mean anything else?
	..
	Should isLeaf go in the cbt byte as 1 of the 256 values?
	byte0 u
	byte1 0
	byte2 1
	byte3 any cbt2
	byte4 any cbt4
	byte5 any cbt8
	byte254 any cbt<2^252>
	byte255 anything else.
	..
	byte0 any cbt1
	byte1 any cbt2
	byte2 any cbt4
	byte3 any cbt8
	byte254 any cbt<2^254>
	byte255 anything else.
	..
	byte0 anything else
	byte1 any cbt1
	byte2 any cbt2
	byte3 any cbt4
	byte254 any cbt<2^253>
	byte255 any cbt<2^254>
	..
	byte0 0 which is a cbt1
	byte1 1 which is a cbt1
	byte2 u
	byte3 anything else
	byte4 any cbt2 aka cbt<2^1>
	byte5 any cbt4 aka cbt<2^2>
	byte6 any cbt8 aka cbt<2^3>
	byte254 any cbt<2^251>
	byte255 any cbt<2^252>
	..
	height is 0..126, or 127 means height is bigger.
	is_cbt0_to_cbt126 -
		since (u u) is 0 and is height 1,
		and (u (u u)) is 1 and is height 2,
		whether its 0 or 1 can be known by this and height??? No, cuz if its all 0s then its diff height than all 1s.
		Using (u (u u)) vs (u u u) aka ((u u) u) as 0 vs 1 would fix that height problem
		but would complicate the design that first param of u is namespace.
		(u u u) would be in the u namespace, and u past that, and is height 2.
		(u (u u)) would be the (u u) namespace, and is height 2.
		Cbts being the same height for the same cbt size would make comparing bitstrings simpler.
	..
	byte0 anything else.
	byte1..byte5 the first 5 binary forest nodes: u, (u u), (u (u u)), ((u u) u), ((u u)(u u))
	wont fit: height is 0..126, or 127 means height is bigger.
	..
	(u (u u)) and (u u u) are 0 vs 1 (todo reverse those?)???
	sign bit as: is_cbt0_to_cbt<2^124>
	height is 0..126, or 127 means height is bigger.
	//do I want a bit for isAll0s? Could replace the isLeaf bit since if height is 0 its leaf.
	//Do I want ability to know if its the constant 0 vs 1? If its a cbt and height is 2, then isAll0s would tell that.
	..
	remove isLeaf bit, and these are the other 9 bits:
	1 bit - isAll0s_and_is_cbt0_to_cbt124
	1 bit - is_cbt0_to_cbt124
	7 bits - height is 0..126, or 127 means height is bigger.
	..
	(u (u u)) is 0.
	((u u) u) is 1. todo reorder these?
	(u anything_of_height_at_least_3) is namespace anything_of_height_at_least_3.
	???16 bits of header (todo reorder these)[
		7 bits - height is 0..126, or 127 means height is bigger.
		isAll0s_and_is_cbt0_to_cbt124 - way to efficiently skip sparse ranges such as bitstring padding or 1d sparse array.
		is_cbt0_to_cbt124 aka bitstring up to size 2^124-1 (excluding 1000000... padding to next powOf2).
		no
		yes
		allYes
		allObserve
		allUnknown
		allUnknownBelow
		anyBull
	]???
	*/
	
	
	/*	
	TODO id differs by only 1 bit if its allYes vs allObserve,
	and maybe similar (1-2 more bits?) for those vs allUnknown vs allUnknownBelow.
	*/
	
	
	
	/** 0 if this is not a litera cbt stored in an id, else is 1..128 bits
	(or in a bigger id might have 1..256 bits, or in some datastructs might have any large number of bits,
	but the short header defines it as a powOf2 number of bits thats literal if it fits in the id minus header etc).
	*/
	public static final int numLiteralBits(short header){
		
	}
	
	public static final TruthValue isCbt(short header){
		
	}
	
	public static final TruthValue isCbt(short header){
		
	}
	
	/*public static final boolean allYes(short header){
	}*/

}
