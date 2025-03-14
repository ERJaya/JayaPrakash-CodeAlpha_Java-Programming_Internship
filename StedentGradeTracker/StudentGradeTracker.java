package StedentGradeTracker;

import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of Students :");
        int numStudents=sc.nextInt();

        if(numStudents<=0){
            System.out.println("Invalid number of Students : ");
            return;
        }

        int[] grades=new int[numStudents];

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter grade for Student "+(i+1)+":");
            grades[i]=sc.nextInt();
        }

        double average=calculateAverage(grades);
        System.out.println("Average grade: "+average);

        int highest=findHighest(grades);
        System.out.println("Highest grade: "+highest);

        int lowest=findLowest(grades);
        System.out.println("Lowest grade: "+lowest);
    }

        public static double calculateAverage(int[]grades){
            if(grades.length==0){
                return 0;
            }
            int sum=0;
            for (int grade : grades) {
                sum=sum+grade;
            }
            return sum/grades.length;
        }

        

        public static int findHighest(int[]grades){
            if(grades.length==0){
                return Integer.MIN_VALUE;
            }
            int highest=Integer.MIN_VALUE;
            for (int grade : grades) {
                if(grade>highest){
                    highest=grade;
                }
            }
            return highest;

        }


        public static int findLowest(int[]grades){
            if(grades.length==0){
                return Integer.MAX_VALUE;
            }
            int lowest=Integer.MAX_VALUE;
            for (int grade : grades) {
                if(grade<lowest){
                    lowest=grade;
                }
            }
            return lowest;

        }
    }