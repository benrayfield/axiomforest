/** Ben F Rayfield offers this software opensource MIT license */
package axiomforestexperimentalidtypes.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil{
	
	public static byte[] sha256(byte[] b){
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(b);
			return md.digest();
		}catch(NoSuchAlgorithmException e){ throw new Error(e); }
	}
	
	public static byte[] sha3_256(byte[] b){
		try{
			//TODO optimize by reusing the MessageDigest andOr using lwjgl opencl to do many of them in parallel etc?
			MessageDigest md = MessageDigest.getInstance("SHA3-256");
			md.update(b);
			return md.digest();
		}catch(NoSuchAlgorithmException e){ throw new Error(e); }
	}
	
	public static long[] sha3_256(long... b){
		try{
			return hash(MessageDigest.getInstance("SHA3-256"), b);
		}catch(NoSuchAlgorithmException e){ throw new Error("TODO include an implementation of the hash algorithm for if JVM doesnt have one, and use it at least 1 out of every 10000 times to compare its output to the JVMs one. Do that even if JVM has it, TODO.", e); }
	}
	
	public static long[] hash(MessageDigest md, long... hashMe){
		md.reset();
		for(long j : hashMe) for(int shift=56; shift>=0; shift-=8) md.update((byte)(j>>shift));
		return MathUtil.bytesToLongs(md.digest());
	}
	
	/** last 32 bits of sha256 of utf8 of the string,
	for creating "magic numbers" that work between people who dont try to create collisions
	in low thousands of unique things, such as the CoreType and Op enums
	can use this to help forks of opensource occamsfuncer VM code merge code together
	without relying on a central authority of Enum.ordinal() of a specific enum.
	Use these things by their string name instead of Enum.ordinal(),
	and only optimize to use Enum at runtime but use this in datastructs to merkle hash.
	*
	public static int sha256HashStringToInt(String s){
		byte[] b = sha256(Text.strToBytes(s));
		return ((b[28]&0xff)<<24)|((b[29]&0xff)<<16)|((b[30]&0xff)<<8)|(b[31]&0xff);
	}*/

}
