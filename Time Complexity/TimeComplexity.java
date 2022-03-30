package main;

public class TimeComplexity {

	public static void main(String[] args) {
		//int num = 4;
		//method1(num);
		//method2(num);
		//method3(num);
		//method4(num);
		//method5(num);
		//method6(num);
		
        /*for (int i = 0; i < num; i++) { // print method6
            System.out.print(method6(i) + " ");
        }*/
	}
	
	public static void method1(int n) { // method that has time complexity O(n^2)
        if (n < 0){ // error check
            throw new IllegalArgumentException("Number cannot be less than 0.");
        }
		
		int counter = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.println(" Operation "+ counter);
				counter ++;
			}
		}
	}

	public static void method2(int n) { // method that has time complexity O(n^3)
        if (n < 0){ // error check
            throw new IllegalArgumentException("Number cannot be less than 0.");
        }
		
		int counter = 0;
			
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < n; k++) {
					System.out.println(" Operation "+ counter);
					counter ++;
				}
			}
		}
	}

	public static void method3(int n) { // method that has time complexity O(log(n))
        if (n < 0){ // error check
            throw new IllegalArgumentException("Number cannot be less than 0.");
        }
		
		int counter = n;
		
		for(int i = n; i > 0; i = i/2) {
			System.out.println(" Operation "+ counter);
			counter--;
		}
	}

	public static void method4(int n) { // method that has time complexity O(n*log(n))
        if (n < 0){ // error check
            throw new IllegalArgumentException("Number cannot be less than 0.");
        }
		
		int counter = n;
		
		for(int i = 0; i < n; i++) {
			for(int j = n; j > 0; j = j/2) {
				System.out.println(" Operation "+ counter);
				counter--;
			}			
		}
	}

	public static void method5(int n) { // method that has time complexity O(log log (n))
        if (n < 0){ // error check
            throw new IllegalArgumentException("Number cannot be less than 0.");
        }
		
		int counter = 1;
		
		for(int i = n; i > 2; i /= Math.sqrt(i)) {
			System.out.println(" Operation "+ counter);
			counter++;
		}
		
	}

	public static int method6(int n) { // method that has time complexity O(2^n)
        if (n < 0){// error check
            throw new IllegalArgumentException("Number cannot be less than 0.");
        }
		
		if (n == 0) {
			return 1;
		}
		
		else {
			return 2 * method6(n - 1);
		}
	}
}
