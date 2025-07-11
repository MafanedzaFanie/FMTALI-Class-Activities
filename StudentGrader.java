import java.util.Scanner;
public class StudentGrader{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		boolean retry = true;
		
		while(retry == "yes" ){
			System.out.println("Enter an exam mark: ");
			int mark = scan.nextInt();
			
			int score = mark + 5;
			if(score < 40)
				score = score - 2;
			System.out.println("Your exam mark is: " + score);
			
			if(score >= 50)
				System.out.println("Pass");
			else
				System.out.println("Fail");
			
			
			String status = (score >= 80) ? "Excellent"
				:(score >= 60) ? "Good"
				:"Needs improvement";
				System.out.println("Your mark status is: " + status); 
				
			int digit = 0;
			digit = score % 10;
			switch(digit){
				case 0: System.out.println("Nice round number");break;
				case 1:
				case 2:
				case 3:
				case 4:
				System.out.println("Low Tail");break;
				case 5: System.out.println("Nice round number");break;
				case 6:
				case 7:
				case 8:
				case 9:
				System.out.println("High Tail");break;
				default: System.out.println("Interesting score");
			}
			scan.nextLine(); //Clear new line
			System.out.println("Do you want to retry the grading process? (Yes or No): ");
			String retryInput = scan.nextLine();
			retry = retryInput.equals("yes");
			
			if(retry){
				//Do-while loop that runs once to confirm exitInput
				String exitInput;
				do{
					System.out.println("Do you want to exit the program? (yes or no): ");
					exitInput = scan.nextLine();
				}while(!exitInput.equals("yes"));
				
			}
		}
		scan.close();
	}
}