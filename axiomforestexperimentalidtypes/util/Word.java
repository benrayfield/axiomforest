/** Ben F Rayfield offers this software opensource MIT license */
package axiomforestexperimentalidtypes.util;
import java.lang.ref.SoftReference;
import java.util.Random;

/** uint256, the default "word size" of this system such as id256,
though you can derive other id sizes and types at runtime
if you want (TODO verify that and fix if its not true).
This is an efficient representation of cbt256.
<br><br>
As a λ this is a cbt256 which may be the id of some other λ.
UPDATE: cant efficiently be a λ cuz that has a mutable long header,
and this doesnt have a long field to update that. Or should this just pay the extra 64 bits?
TODO Just use ArrayCbt<long[]> (copy some code from occamsfuncer) instead,
or something like it but optimized for 4 final long fields???
*/
public final class Word implements Comparable<Word>/*, λ*/{
	
	public final long a, b, c, d;
	
	private int hash;
	
	private SoftReference<String> cacheToString;
	
	private static final int
		saltA = Rand.strongRand.nextInt(),
		saltB = Rand.strongRand.nextInt(),
		saltC = Rand.strongRand.nextInt(),
		saltD = Rand.strongRand.nextInt();
	
	/** usually not a valid wikibinator id, but is a random 256 bits */
	public Word(Random rand){
		this(rand.nextLong(), rand.nextLong(), rand.nextLong(), rand.nextLong());
	}
	
	public Word(String s){
		if(s.length() != 65 || s.charAt(0) != 'λ')
			throw new RuntimeException("This is not what a toString returned");
		//TODO also verify its lowercase hex digits
		a = Long.parseUnsignedLong(s.substring(1,17),16);
		b = Long.parseUnsignedLong(s.substring(17,33),16);
		c = Long.parseUnsignedLong(s.substring(33,49),16);
		d = Long.parseUnsignedLong(s.substring(49,65),16);
	}
	
	/** read a range of 32 bytes */
	public Word(byte[] b, int offset){
		this(
			MathUtil.readLongFromByteArray(b, offset),
			MathUtil.readLongFromByteArray(b, offset+8),
			MathUtil.readLongFromByteArray(b, offset+16),
			MathUtil.readLongFromByteArray(b, offset+24)
		);
	}
	
	/** 32 bytes */
	public Word(byte[] b){
		this(b, 0);
	}
	
	public Word(long a, long b, long c, long d){
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	
	/** Returns id of parent of 2 ids in binary forest, with the val TruthValues all being TruthValue.unknown
	aka ANDing 1 of the longs with HeaderBits.keyMask. If you want those bits, which is nondeterministic
	of whatever the VM might know about that pair, then use P(Id,Id) instead of this p(Id,Id).
	*
	public static Id p(Id l, Id r){
		long retHeader = 0, retB = 0, retC = 0;
		"TODO choose which kind of sha3 to hash 512 bits (including some bits masked by HeaderBits.keyMask).""
		throw new RuntimeException("TODO");
	}
	
	public static Id P(Id l, Id r){
		throw new RuntimeException("TODO");
	}*/

	/** compare as uint256 */
	public int compareTo(Word o){
		if(a != o.a) return Long.compareUnsigned(a, o.a);
		if(b != o.b) return Long.compareUnsigned(b, o.b);
		if(c != o.c) return Long.compareUnsigned(c, o.c);
		return Long.compareUnsigned(c, o.c);
	}
	
	public int hashCode(){
		if(hash == 0){
			long g = saltA*a+saltB*b+saltC*c+saltD*d;
			hash = ((int)(g>>32)^(int)g);
			if(hash == 0) hash = 1;
		}
		return hash;
	}
	
	public boolean equals(Object o){
		if(!(o instanceof Word)) return false;
		Word O = (Word)o;
		return a==O.a & b==O.b & c==O.c & d==O.d; //use & (less branching) instead of &&.
	}
	
	/** cached. TODO use some encoding other than hex, so its shorter,
	such as base256 using 256 printable unicode symbols that fit in 2 bytes in utf8 and fit
	in 1 char of utf16 which java and javascript etc use utf16 in java.lang.String but
	I convert it to utf8 as bytes other places.
	*/
	public String toString(){
		String ret;
		if(cacheToString == null || (ret = cacheToString.get())==null){
			byte[] bytes = new byte[32];
			MathUtil.copyLongIntoByteArray(bytes, 0, a);
			MathUtil.copyLongIntoByteArray(bytes, 8, b);
			MathUtil.copyLongIntoByteArray(bytes, 16, c);
			MathUtil.copyLongIntoByteArray(bytes, 24, d);
			ret = "λ"+Text.bytesToHex(bytes);
			cacheToString = new SoftReference<String>(ret);
		}
		return ret;
	}
	
	public static void lg(String line){
		System.out.println(line);
	}
	
	public static void main(String... args){
		for(int i=0; i<10; i++){
			lg("A word: "+new Word(Rand.strongRand));
		}
	}
	
}
