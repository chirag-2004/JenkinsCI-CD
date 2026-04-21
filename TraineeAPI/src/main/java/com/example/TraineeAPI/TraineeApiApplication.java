package com.example.TraineeAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TraineeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TraineeApiApplication.class, args);
    }
}


//package com.example.TraineeAPI;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Scanner;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import com.example.TraineeAPI.entities.Trainee;
//import com.example.TraineeAPI.services.ITraineeService;
//
//@SpringBootApplication
//public class TraineeApiApplication implements CommandLineRunner {
//
//    @Autowired
//    private ITraineeService service;
//
//    public static void main(String[] args) {
//        SpringApplication.run(TraineeApiApplication.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        Scanner sc = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("\nTrainee Menu");
//            System.out.println("1 Add Trainee");
//            System.out.println("2 View All Trainees");
//            System.out.println("3 View Trainee by ID");
//            System.out.println("4 View Trainee by Name");
//            System.out.println("5 Update Trainee");
//            System.out.println("6 Delete Trainee");
//            System.out.println("7 Exit");
//            System.out.print("Enter choice: ");
//
//            int choice = sc.nextInt();
//            sc.nextLine();
//
//            switch (choice) {
//
//                case 1:
//                    Trainee t = new Trainee();
//
//                    System.out.print("Enter Name: ");
//                    t.setTraineeName(sc.nextLine());
//
//                    System.out.print("Enter Domain: ");
//                    t.setTraineeDomain(sc.nextLine());
//
//                    System.out.print("Enter Location: ");
//                    t.setTraineeLocation(sc.nextLine());
//
//                    service.addTrainee(t);
//                    System.out.println("Trainee added");
//                    break;
//
//                case 2:
//                    List<Trainee> list = service.getAllTrainees();
//                    list.forEach(System.out::println);
//                    break;
//
//                case 3:
//                    System.out.print("Enter ID: ");
//                    int id = sc.nextInt();
//
//                    Optional<Trainee> t1 = service.getTraineeById(id);
//                    if (t1 != null) {
//                        System.out.println(t1);
//                    } else {
//                        System.out.println("Trainee not found");
//                    }
//                    break;
//                case 4:
//                    System.out.print("Enter Name: ");
//                    String name = sc.nextLine();
//
//                    Optional<Trainee> traineeOpt = service.getTraineeByName(name);
//
//                    if (traineeOpt.isPresent()) {
//                        System.out.println(traineeOpt.get());
//                    } else {
//                        System.out.println("Trainee not found");
//                    }
//                    break;
//
//                case 5:
//                    System.out.print("Enter ID to update: ");
//                    int uid = sc.nextInt();
//                    sc.nextLine();
//
//                    Optional<Trainee> existing = service.getTraineeById(uid);
//                    
//                    if (existing.isPresent()) {
//                        System.out.println(existing.get());
//                    } else {
//                        System.out.println("Trainee not found");
//                    }
//                    break;
//
//                case 6:
//                    System.out.print("Enter ID to delete: ");
//                    int did = sc.nextInt();
//
//                    boolean status = service.deleteTrainee(did);
//
//                    if (status) {
//                        System.out.println("Trainee deleted");
//                    } else {
//                        System.out.println("Trainee not found");
//                    }
//                    break;
//
//                case 7:
//                    System.out.println("Exiting application");
//                    sc.close();
//                    System.exit(0);
//
//                default:
//                    System.out.println("Invalid choice");
//            }
//        }
//    }
//}