//James Fong
//I pledge my honor that I have abided by the Stevens' Honor System.

package main;

import java.lang.Character;
import java.util.Arrays;


public class BinaryNumber {

	//Data fields
	private int data[];
	private int length;
	
	/**
	 * A constructor BinaryNumber(int length) for creating a binary number of 
	 * length length and consisting only of zeros.
	 * @param length
	 */
	/**
	 * creating a binary number of length length
	 * and consisting only of zeros.
	 * @param length
	 */
	public BinaryNumber(int length) {
		if(length < 0) {
			throw new IllegalArgumentException("The length cannot be nonexistant.");
		}
		
    	this.length = length; //Creates a binary number of length length
    	this.data = new int[length]; 
	}
	
	/**
	 * Creating a binary number given a string.
	 * @param str
	 */
	
	public BinaryNumber(String str) { // Create binary number given a string.
    	
		int[] binaryNumber = new int[str.length()];
    	
		
		this.length = str.length();
    	
		for(int i = 0; i < str.length(); i++) {
			if(Character.getNumericValue(str.charAt(i)) == 0) {
				binaryNumber[i] = 0;
			}else if(Character.getNumericValue(str.charAt(i)) == 1) {
				binaryNumber[i] = 1;
			}else {
				throw new IllegalArgumentException("Str can only be made up of ones and zeros");
			}
		}
		
		data = binaryNumber;
		length = str.length();
	}

	/**
	 * Returns the integer array representing the binary number.
	 * @return
	 */
	public int[] getInnerArray() { // returns integer array representing binary number.
		return this.data;
	}
	
	/**
	 * For determining the length of a binary number.
	 * @return
	 */
	public int getLength() { // determine length of a binary number.
        return this.length;
	}
	
	/**
	 * obtaining a digit of a binary number given an
	 * index. The starting index is 0. If the index is out of bounds, then a message should
	 * be printed on the screen indicating this fact.
	 * @param index
	 * @return
	 */
	public int getDigit(int index) { // Obtain digit of binary number, given index.
        if (index < 0 || index > this.length - 1) {
        	throw new IndexOutOfBoundsException("Index " + index + " is not within bounds.");
        }
        return this.data[index];
	}

	/**
	 * computes the bitwise or of the two numbers. Note that both argument BinaryNumbers must be of
	 * the same length for the input to be considered valid.
	 * @param bn1
	 * @param bn2
	 * @return
	 */
	public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) { // Computes the bitwise or of the two numbers.
        if (bn1.getLength() != bn2.getLength()) {
            throw new UnsupportedOperationException("Inputs must be the same length.");
        }
        
        int[] BWor= new int[bn1.getLength()];
        
        for (int i = 0; i < bn1.getLength(); i++) {
            if (bn1.data[i] == 0 && bn2.data[i] == 0) {
            	BWor[i] = 0;
            }
            else {
            	BWor[i] = 1;
            }
        }
        return BWor;
	}
	
	/**
	 * computes the bitwise and of the two numbers. Note that both argument BinaryNumbers must be of
	 * the same length for the input to be considered valid.
	 * @param bn1
	 * @param bn2
	 * @return
	 */
	public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) { // Computes the bitwises and of the two numbers.
        if (bn1.getLength() != bn2.getLength()) {
            throw new IllegalArgumentException("Inputs must be the same length.");
        }
        
        int[] BWand= new int[bn1.getLength()];
        
        for (int i = 0; i < bn1.getLength(); i++) {
            if (bn1.data[i] == 0 && bn2.data[i] == 0) {
            	BWand[i] = 1;
            }
            else {
            	BWand[i] = 0;
            }
        }
        return BWand;
	}
	
	/**
	 * For shifting all digits in a binary number any number of places to the left or right. The direction parameter indicates
	 * a left shift when the value is -1. When direction is given the value 1, the shift should
	 * be to the right. Any other value for direction should be seen as invalid. The amount
	 * parameter specifies how many digits the BinaryNumber will be shifted, and is only
	 * valid when it is nonnegative.
	 * @param direction
	 * @param amount
	 */
	public void bitShift(int direction, int amount) { // Shift all digits in binary number x number of places left or right.
        //shifting to the right, i.e. 1011 shifted 2 is 10.
		if (direction == 1) {            
            int [] NewArray = new int [this.length - amount];
            
            for(int i = 0; i < this.length - amount; i++){
                NewArray[i] = this.data[i];
            }
            
            for(int i = this.length; i < length - amount; i++){
            	NewArray[i] = 0;
            }
            
            this.data = NewArray;
            this.length = length - amount;
        }
		
		//shifting to the left, i.e. 1011 shifted 2 is 101100.
        else if(direction == -1) {//shift left
            int [] NewArray = new int [this.length + amount];
            
            for(int i = 0; i < this.length; i++){
            	NewArray[i] = this.data[i];
            }
            
            for(int i = this.length; i < length + amount; i++){
            	NewArray[i] = 0;
            }
            
            this.data = NewArray;
            this.length = length + amount;
        }
		
        else {
           	throw new IndexOutOfBoundsException("Index is not within bounds.");	
        }
	}
	
	public void add(BinaryNumber aBinaryNumber) { // addition of two binary numbers.   
        if (aBinaryNumber.getLength() != this.length) {
     
            if (aBinaryNumber.getLength() > this.length) {
            	prepend(aBinaryNumber.getLength() - this.length);
            }
            
            else if (aBinaryNumber.getLength() < this.length) {
            	aBinaryNumber.prepend(this.length - aBinaryNumber.getLength());
            }
            
        }
        
        int carry = 0;
        int[] AddArray = new int[this.length];
        
        for(int i = this.length - 1; i >= 0; i--) {
			if(this.data[i] == 0 && aBinaryNumber.getInnerArray()[i] == 0) {
				if(carry == 1) {
					AddArray[i] = 1;
					carry = 0;
				} else {
					AddArray[i] = 0;
					carry = 0;
				}
			} 
			else if(this.data[i] == 0 && aBinaryNumber.getInnerArray()[i] == 1) {
				if(carry == 1) {
					AddArray[i] = 0;
				} else {
					AddArray[i] = 1;
					carry = 0;
				}
			}
			else if(this.data[i] == 1 && aBinaryNumber.getInnerArray()[i] == 0) {
				if(carry == 1) {
					AddArray[i] = 0;
				} else {
					AddArray[i] = 1;
					carry = 0;
				}
			} else {
				if(carry == 1) {
					AddArray[i] = 1;
					carry = 1;
				} else {
					AddArray[i] = 0;
					carry = 1;	
				}
				
			}
		}

		this.data = AddArray;
		this.length = AddArray.length;
		
		System.out.println("Resulting array:");
		for(int i = 0; i < AddArray.length; i++) {
			System.out.print(AddArray[i]);
		}
		
		System.out.println();
		
		if (carry == 1) {
			
			this.prepend(carry);
			this.data[0] = 1;
		}
	}
	
	/**
	 * If two BinaryNumbers lengths do not coincide, the 
	 * smaller length of two Binary Numbers is prepended with 0's
	 * to prevent errors.
	 * @param amount
	 */
    public void prepend(int amount) {
		int[] newArray = new int[amount + this.data.length];
		
		for(int i = amount; i < newArray.length; i++) {
			newArray[i] += this.getDigit(i - amount);
		}
		
		this.data = newArray;
		this.length = newArray.length;
	}
	

    /**
     * Returns the BinaryNumber as the corresponding encoded string.
     */
	public String toString() { // Return String as corresponding encoded string.
        String DataString = "";
        int[] Array = this.data.clone();
        
        for (int i = 0; i < this.length ; i++){
            DataString += this.data[i];
        }
        
        return DataString;
	}
	
	/**
	 * For transforming a binary number to its decimal notation.
	 * @return
	 */
	public int toDecimal() {
		
		int decimal = 0;
	    int counter = 0;
	    int i = length - 1;
	    
	    while(true){
	      if(i >= 0){
	        break;
	      } else {
	          decimal += this.data[i] * Math.pow(2, counter);
	          counter++;
	          i -= 1;
	       }
	    }
	    
	    return decimal;
	}
	
	public static void main(String[] args) {
		
		System.out.println("Testing number that is not binary:");
		BinaryNumber BN1 = new BinaryNumber(15);
		System.out.println("The binary length of the given number:");
		System.out.println(BN1.getLength());
		System.out.println("Given number in array form:");
		System.out.println(Arrays.toString(BN1.getInnerArray()));
		System.out.println("Given number decimals:");
		System.out.println(BN1.toDecimal());
		BN1.prepend(2);
		System.out.println("Given number in array form after prepended:");
		System.out.println(Arrays.toString(BN1.getInnerArray()));
		System.out.println(BN1.toString());
		System.out.println("Given number decimals in array form:");
		System.out.println(BN1.toDecimal());
		System.out.println();
        

		System.out.println("Testing number that is in the form of a string:");
		BinaryNumber BN2 = new BinaryNumber("0111");
		System.out.println("The binary length of the given number:");
		System.out.println(BN2.getLength());
		System.out.println("Given number in array form:");
		System.out.println(Arrays.toString(BN2.getInnerArray()));
		System.out.println("Given number decimals:");
		System.out.println(BN2.toDecimal());
		BN2.prepend(4);
		System.out.println("Given number in array form after prepended:");
		System.out.println(Arrays.toString(BN2.getInnerArray()));
		System.out.println(BN2.toString());
		System.out.println("Given number decimals in array form:");
		System.out.println(BN2.toDecimal());
		System.out.println();
		
		System.out.println("Bitshift Left Test:");
		BinaryNumber BSN1 = new BinaryNumber("1100");
		System.out.println("Number being tested:");
		System.out.println(BSN1);
		//Operation
		BSN1.bitShift(1, 2);
		System.out.println("Bitshift result:");
		System.out.println(BSN1);
		System.out.println();
		
		System.out.println("Bitshift Left Test:");
		BinaryNumber BSN2 = new BinaryNumber("01111");
		System.out.println("Number being tested:");
		System.out.println(BSN2);
		//Operation
		BSN2.bitShift(-1, 2);
		System.out.println("Bitshift result:");
		System.out.println(BSN2);
		System.out.println();
		
		System.out.println("Add test:");
		BinaryNumber BNAdd1 = new BinaryNumber("10110");
		System.out.println("Number being tested:");
		System.out.println(BNAdd1);
		BinaryNumber BNAdd2 = new BinaryNumber("10");
		System.out.println("Number being added to test number:");
		System.out.println(BNAdd2);
		//Operation
		BNAdd1.add(BNAdd2);
		System.out.println("Add result:");
		System.out.println(Arrays.toString(BNAdd1.getInnerArray()));
		System.out.println();
		
		System.out.println("Testing bitwise or:");
		BinaryNumber BNO1 = new BinaryNumber("01010101");
		System.out.println("First number comparison:");
		System.out.println(BNO1);
		BinaryNumber BNO2 = new BinaryNumber("01001101");
		System.out.println("Second number comparison:");
		System.out.println(BNO2);
		//Operation
		int[] OR = bwor(BNO1, BNO2);
		System.out.println("Bitwise or result:");
		System.out.println(Arrays.toString(OR));
		System.out.println();
		
		System.out.println("Testing bitwise and:");
		BinaryNumber BNA1 = new BinaryNumber("1010011");
		System.out.println("First number comparison:");
		System.out.println(BNA1);
		BinaryNumber BNA2 = new BinaryNumber("0101011");
		System.out.println("Second number comparison:");
		System.out.println(BNA2);
		//Operation
		int[] AND = bwand(BNA1, BNA2);
		System.out.println("Bitwise and result:");
		System.out.println(Arrays.toString(AND));
		System.out.println();
    }
	
}
