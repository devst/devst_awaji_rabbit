package features.rabbit;

public class FizzBuzz implements features.FizzBuzz {

	@Override
	public String fizzBuzz(int num) {
		/* if(num <= 0) throw new RuntimeException(); */
		if(num % 3 == 0 && num % 5 == 0) return "FizzBuzz";
		if(num % 5 == 0) return "Buzz";
		if(num % 3 == 0) return "Fizz";
		return String.valueOf(num);
	}
}
